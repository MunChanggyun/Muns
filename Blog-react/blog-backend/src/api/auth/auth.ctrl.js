import Joi from 'joi';
import User from '../../models/users';

export const register = async ctx => {
    // 회원가입
    const schema = Joi.object().keys({
        username: Joi.string()
            .alphanum()     //  a-z, A-Z, and 0-9
            .min(3)
            .max(20)
            .required(),
        password: Joi.string().required()
    });

    const result = Joi.validate(ctx.request.body, schema);

    if (result.error) {
        ctx.status = 400;
        ctx.body = result.error;
        return;
    }

    const { username, password } = ctx.request.body;

    try {
        const exists = await User.findByusername(username);

        // 중복확인
        if (exists) {
            ctx.status = 409;
            return;
        }

        const user = new User({
            username
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

export const login = async ctx => {
    // 로그인
    const { username, password }  = ctx.request.body;

    if (!username || !password) {
        ctx.status = 401;
        return;
    }

    try {
        const user = await User.findByusername(username);

        if (!user) {
            ctx.status = 401;
            return;
        }

        const vaild = await user.checkPassword(password);

        if (!vaild) {
            ctx.status = 401;
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

export const check = async ctx => {
    // 로그인 상태 확인
    console.log(ctx.state);
    const { user } = ctx.state;

    if (!user) {
        // 로그인중이 아닌경우 (= 쿠키에 정보가 없는경우)
        ctx.status = 401;
        return;
    }

    ctx.body = user;
}

export const logout = async ctx => {
    // 로그아웃
    ctx.cookies.set('access_token');
    ctx.status = 204;
}
