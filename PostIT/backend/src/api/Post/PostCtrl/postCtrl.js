import Post from '../../../modules/post';
import mongoose from 'mongoose';
import Joi from 'Joi';
import sanitizeHtml from 'sanitize-html';

const sanitizeOption = {
    alloweTags: [
        'h1',
        'h2',
        'b',
        'i',
        'u',
        's',
        'p',
        'ul',
        'ol',
        'li',
        'blockquote',
        'a',
        'img' 
    ],
    allowedAttrebutes: {
        a: ['href', 'name', 'target'],
        img: ['src'],
        li: ['class']
    },
    allowedSchmas: ['data', 'http']
};

const { ObjectId } = mongoose.Types;

export const checkObjectId = (ctx, next) => {
    const { id } = ctx.params;

    if (!ObjectId.isValid(id)) {
        ctx.status = 401;
        return;
    }

    return next();
}

const removeHtmlAndSorten = body => {
    const filterd = sanitizeHtml(body, {
        allowedTags: []
    });

    return filterd;
}

export const postList = async ctx => {
    const { userId } = ctx.query;

    const query = {
        ...(userId ? {'user.userId': userId} : {'duser.userI': {$ne: null}})
    }

    try {
        const posts = await Post.find({_id:{$ne: null}})
            .sort({_id: -1})
            .limit(100)
            .lean()
            .exec();

            ctx.body = posts.map(post => ({
                ...post,
                body: removeHtmlAndSorten(post.body)
            }));
    } catch (e) {
        ctx.throw(500, e);
    }
}

// 포스트 업데이트
export const updatePost = async ctx => {
    const {_id} = ctx.params;
    const { title, content, top, left } = ctx.request.body;

    try {
        const post = await Post.findByIdAndUpdate(_id, ctx.request.body, {upsert: true}).exec();

        if (!post) {
            //post = new Post(ctx.request.body);
            ctx.throw(400, '포스트 수정에 실패했습니다.');
            return;
        }

        ctx.body = post;
        
    } catch (e) {
        ctx.throw(500, e);
    }
    
}

// 포스트 생성
export const insertPost = async ctx => {
    const {top, left, color} = ctx.request.body;


    console.log("create");

    try {
        const post = await new Post({user: ctx.state.user, top: top, left: left, color: color});

        if (!post) {
            ctx.throw(400, '포스트 생성에 실패했습니다.');
            return;
        }

        await post.save();
        ctx.status = 200;
        ctx.body = post;

    } catch (e) {
        ctx.throw(500, e);
    }
}

// 포스트 제거
export const removePost = async ctx => {
    const { _id } = ctx.params;

    try {
        await Post.findByIdAndRemove(_id).exec();

        ctx.status = 200;
    } catch (e) {
        ctx.throw(500, e);
    }
}

// 포스트 이동
export const setPosition = async ctx => {
    const { id } = ctx.params;
    const { top, left } = ctx.request.body;

    try {
        const post = await Post.findByIdAndUpdate(id, { top, left}, {upsert: true}).exec();

        if (!post) {
            ctx.throw(400, '포스트 이동에 실패했습니다.');
            return;
        }

        ctx.status = 200;
        ctx.body = post;
    } catch (e) {
        ctx.throw(500, e);
    }
}

// 포스트 색상 변경
export const changeColor = async ctx => {
    const { _id } = ctx.params;
    const { color } = ctx.request.body;

    try {
        const post = await Post.findByIdAndUpdate(_id, {color}, {upsert: true}).exec();

        if (!post) {
            ctx.throw(400, '색상 변경에 실패했습니다.');
            return;
        }

        ctx.status = 200;
        ctx.body = post;

    } catch (e) {
        ctx.throw(500, e);
    }

}
