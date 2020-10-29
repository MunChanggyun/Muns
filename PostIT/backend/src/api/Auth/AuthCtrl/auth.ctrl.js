import Joi from 'joi';
import User from '../../../modules/user';

// 로그인
export const login = async ctx => {
    const schema = Joi.object().keys({
        userId: Joi.string().required(),
        password: Joi.string().required()
    });

    // const result = schema.validate(ctx.request.body);

    // if (result.error) {
    //     ctx.status = 400;
    //     ctx.body = result.error;
    //     return;
    // }

    const { userId, password } = ctx.request.body;

    if (!userId || !password) {
        ctx.status = 401;
        ctx.body = "회원정보를 확인해 주세요";
        return;
    }

    try {
        const user = await User.findByUserId(userId);

        if (!user) {
            ctx.status = 401;
            ctx.body = '존재하지 않는 아이디 입니다.'
            return;
        }

        const vaild = await user.checkPassword(password);

        if (!vaild) {
            ctx.status = 401;
            ctx.body = '비밀번호가 일치히지 않습니다.'
            return;
        }

        ctx.body = user.serialize();

        const token = user.generateToken();

        ctx.cookies.set('access_token', token, {
            maxAge: 1000 * 60 * 60 * 24 * 7, // 7일간 보관
            httpOnly: true,     // XSS 공격을 막기위한 설정
        });
    } catch (e) {
        ctx.throw(500, e);
    }
    
}

// 회원가입
export const register = async ctx => {
    const schema = Joi.object().keys({
        userId: Joi.string().alphanum()     //  a-z, A-Z, and 0-9
        .min(3)
        .max(20)
        .required(),
        password: Joi.string().required()
    });

    const result = schema.validate(ctx.request.body);

    if (result.error) {
        ctx.status = 401;
        ctx.body = result.error;
        return
    }

    const { userId, password } = ctx.request.body;

    if (!userId || !password) {
        ctx.status = 401;
        return;
    }

    try {
        const checkUser = await User.findByUserId(userId);

        if (checkUser) {
            ctx.status = 401;
            ctx.body = "이미 가입된 아이디 입니다.";
            return;
        }

        const user = new User({
            userId
        });

        await user.setPassword(password);
        await user.save();

        ctx.body = user.serialize();

        const token = user.generateToken();

        ctx.cookies.set('access_token', token, {
            maxAge: 1000 * 60 * 60 * 24 * 7, // 7일간 보관
            httpOnly: true,     // XSS 공격을 막기위한 설정
        });
    } catch (e) {
        ctx.throw(500, e);
    }
    
}

// 로그인 상태 확인
export const loginCheck = async ctx => {
    const { user } = ctx.state;

    console.log("check user", user);

    if (!user) {
        ctx.status = 401;
        return
    }

    ctx.body = user;
}

// 로그아웃
export const logout = async ctx => {
    console.log("logout???");

    // 로그아웃
    ctx.cookies.set('access_token');
    ctx.status = 204;
}