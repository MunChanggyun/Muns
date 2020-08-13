import Post from '../../models/post'
import mongoose from 'mongoose';
import Joi from "Joi";
import sanitizeHtml from 'sanitize-html';

// Joi 라이브러리를 통해서 Request body 의 값을 검증할 수 있다.

/**
 * sanitize-html
 * html을 작성하고 보여주어야 하는 서비스에서 매우 유용함.
 * HTML을 제거하거나 특정 HTML만 허용하는기능등으로 글쓰기 API에서 사용하면
 * 악성 스크립트 삽입을 방지할수 있다.
 */


const sanitizeOption = {
    allowedTags: [
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
        'img',
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
        ctx.status = 404;
        return;
    }
    return next();
}

export const getPostById = async (ctx, next) => {
    const { id } = ctx.params;

    if (!ObjectId.isValid(id)) {
        ctx.status = 404;
        return;
    }

    try {
        const post = await Post.findById(id);

        if (!post) {
            ctx.status = 404; 
            return;
        }

        ctx.state.post = post;
        return next();

    } catch (e) {
        ctx.throw(500, e);
    }
    return next();
}

// 본인이 작성한 게시물인지 확인
export const checkOwnPost = (ctx, next) => {
    const { user, post } = ctx.state;

    console.log(user, post);
    console.log(post.user._id, user._id);

    if (post.user._id.toString() != user._id) {
        ctx.status = 403;
        return;
    }

    return next();
}

/**
 * 포스트 작성
 * POST /api/posts
 * { title, body }
 */
export const write = async ctx => {   // ES Module 형태
// exports.write = ctx => { 기존 문법
    // REST API 의 Request Body 는 ctx.request.body에서 조회할 수 있다.
    // const {title, body} = ctx.request.body;
    // postId += 1;
    // const post = { id: postId, title, body};
    // posts.push(post);
    // ctx.body = post;
    const schema = Joi.object().keys({
        // 객체가 다음 필드를 가지고 있을 경우를 검증
        title: Joi.string().required(), // required()를 통해서 필수 값인경우 검증
        body: Joi.string().required(),
        tags: Joi.array().items(Joi.string()).required()
    });

    const result = Joi.validate(ctx.request.body, schema);

    if (result.error) {
        console.log(result.error);
        ctx.status = 400;   // Bad request
        ctx.body = result.error;
        return;
    }

    const { title, body, tags} = ctx.request.body;
    const post = new Post({
        title,
        body: sanitizeHtml(body, sanitizeOption),
        tags,
        user: ctx.state.user
    });

    try {
        await post.save();
        ctx.body = post;
    } catch (e) {
        ctx.throw(500, e)
    }
    
};

/**
 * html을 없애고 글자 200 자로 제한
 */
const removeHtmlAndShorten = body => {
    const filtered = sanitizeHtml(body, {
        allowedTags: []
    });

    return filtered.length < 200 ? filtered: `{filtered.slice(0,200)}...`;
}

/**
 * 포스트 목록 조회
 * GET /api/posts
 */
export const list = async ctx => {
// exports.list = ctx => {
    // ctx.body = posts;

    // 페이징 기능을 위한 추가
    const page = parseInt(ctx.query.page || '1', 10);   // 기본값 1, query는 문자열로서 숫자로 변환해야함 

    if (page < 1) {
        ctx.status = 400;
        return;
    }

    const { tag, username } = ctx.query;

    const query = {
        ...(username ? {'user.username': username} : {'user.username': { $ne: null }}), // RDBMS 에서 not null과 같은 역할을 한다.
        ...(tag ? {tags: tag} : [])
    }

    try {
        console.log("query ::: ",query);

        const posts = await Post.find(query)
        .sort({ _id: -1})   // 1 로 설정하면 오름차순, -1 으로 설정하면 내림차순으로 정렬
        
        .limit(10)  // 갯수 제한
        .skip((page - 1) * 10)  // 페이징 기능
        .lean() // json 형태로 조회
        .exec();

        const postCount = await Post.countDocuments(query).exec();
        ctx.set('Last-Page', Math.ceil(postCount / 10));
        ctx.body = posts
            .map(post => ({
                ...post,
                body: removeHtmlAndShorten(post.body)
            }));
    } catch (e)  {
        ctx.throw(500, e);
    }
    
}

/**
 * 특정 포스트 조회
 * GET /api/posts/:id
 */
export const read = async ctx => {
// exports.read = ctx => {
    try {
        ctx.body = ctx.state.post;
    } catch (e) {
        ctx.throw(500, e);
    }
    
}

/**
 * 특정 포스트 제거
 * DELETE /api/posts/:id
 */
export const remove = async ctx => {
// exports.remove = ctx => {
    const { id } = ctx.params;

    // 해당 id를 가지 post가 몇번쨰인지 확인한다.
    // const index = posts.findIndex(p => p.id.toString() === id);

    // if (index === -1) {
    //     ctx.status = 404;
    //     ctx.body = {
    //         message: '포스트가 존재하지 않습니다.'
    //     }
    //     return;
    // }

    // posts.splice(index, 1);
    // ctx.status = 204;

    try {
        await Post.findByIdAndRemove(id).exec();
        ctx.status = 204;
    } catch (e) {
        ctx.throw(500, e);
    }
}

/**
 * 포스트 수정(교체)
 * PUT /api/posts/:id
 */
export const replace = async ctx => {
// exports.replace = ctx => {
    // PUT 메서드는 전체 포스트 정보를 입력하여 데이터를 통째로 교체할 때 사용한다.
    const { id } = ctx.params;
    
    // const index = posts.findIndex(p => p.id.toString() === id);

    // if (index === -1) {
    //     ctx.status = 404;
    //     ctx.body = {
    //         message: "포스트가 존재하지 않습니다."
    //     }

    //     return;
    // }

    // posts[index] = {
    //     id,
    //     ...ctx.request.body
    // };
    // ctx.body = posts[index];

    try {
        const post = Post.findByIdAndUpdate(id, ctx.request.body, {
            new: true // 이값을 설정하면 업데이트 된 데이터를 반환
        }).exec();

        if (!post) {
            ctx.status = 404;
            return;
        }

        ctx.body = post;
    } catch (e) {
        ctx.throw(500, e);
    }
}

/**
 * 포스트 수정 (특정 필드 변경)
 * PATCH /api/posts/:id
 * {title, body}
 */
export const update = async ctx => {
// exports.update = ctx => {
    const { id } = ctx.params;

    //const index = posts.findIndex(p => p.id.toString() === id);

    // if (index === -1) {
    //     ctx.status = 404;
    //     ctx.body = {
    //         message: '포스트가 존재하지 않습니다.'
    //     }

    //     return;
    // }

    // posts[index] = {
    //     ...posts[index],
    //     ...ctx.request.body
    // };

    // ctx.body = posts[index]
    const nextData = {...ctx.request.body};

    if (nextData.body) {
        nextData.body = sanitizeHtml(nextData.body);
    }


    const schema = Joi.object().keys({
        title: Joi.string(),
        body: Joi.string(),
        tags: Joi.array().items(Joi.string())
    });

    const result = Joi.validate(ctx.request.body, schema);

    if (result.error) {
        ctx.status = 400;   // Bad request
        ctx.body = result.error;
        return;
    }

    try {
        const post = await Post.findByIdAndUpdate(id, nextData, {
            new: true,  // 이 값을 설정하면 업데이트된 데이터를 반환합니다.
        }).exec();

        if (!post) {
            ctx.status = 404;
            return;
        }

        ctx.body = post;
    } catch (e) {
        ctx.throw(500, e);
    }

}