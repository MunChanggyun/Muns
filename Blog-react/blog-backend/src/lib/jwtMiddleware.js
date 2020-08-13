import jwt from 'jsonwebtoken';
import User from '../models/users';

// 토큰 검증
/**
 * 토큰검증은 router 미들웨어 적용하기 전에 호출되어야 함
 */

const jwtMiddleware = async (ctx, next) => {
    const token = ctx.cookies.get('access_token');

    if (!token) {
        return next();
    }

    try {
        const decoded = jwt.verify(token, process.env.JWT_SECRET);
        ctx.state.user = {
            _id: decoded._id,
            username: decoded.username,
        };


        // 토근의 남은 유효기간이 3.5 일 미만이면 재발급
        const now = Math.floor(Date.now() / 1000);

        if (decoded.exp - now < 60 * 60 * 24 * 3.5) {
            const user = await User.findById(decoded._id);
            const token = user.generateToken();

            ctx.cookies.set('access_token', token, {
                maxAge: 1000 * 60 * 60 * 24 * 7, // 7일간 보관
                httpOnly: true,     // XSS 공격을 막기위한 설정
            });
        }
        
        return next();
    } catch (e) {
        return next();
    }
}

export default jwtMiddleware;