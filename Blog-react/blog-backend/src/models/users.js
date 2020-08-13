import mongoose, { Schema } from 'mongoose';
import bcrypt from 'bcrypt';
import jwt from 'jsonwebtoken';


/**
 * 토큰 인증을 통한 로그인 
 * 
 * bcrypt 설치 > yarn add bcrypt
 * 
 * 1. User 스키마/모델만들기
 * 2. 회원 인증 API 만들기
 * 3. 토큰 발급 및 검증하기         >> 토큰(JWT) 발급을 위해서 jsonwebtoken 모듈을 설치  >> yarn add jsonwebtoken
 * 4. posts API 에 회원 인증 시스템 도입
 * 5. username/tage로 포스트 필터링하기
 */

// 스케마 생성
const UserSchema = new Schema({
    username: String,
    hashedPassword: String
});

UserSchema.methods.setPassword = async function(password) {
    const hash = await bcrypt.hash(password, 10);
    this.hashedPassword = hash;
}

// 인스턴스 함수
UserSchema.methods.checkPassword = async function (password) {
    const result = await bcrypt.compare(password, this.hashedPassword);
    return result;
}

// 스테틱 함수
UserSchema.statics.findByusername = function(username) {
    return this.findOne({username});    // this = model
}

UserSchema.methods.serialize = function() {
    const data = this.toJSON();
    delete data.hashedPassword;
    return data;
}

UserSchema.methods.generateToken = function () {
    const token = jwt.sign(
        {
            _id: this.id,
            username: this.username
        },
        process.env.JWT_SECRET,
        {
            expiresIn: '7d' // 7일동안 유효 하도록 설정
        }
    );
    return token;
}

// 모델생성
const User = mongoose.model('User', UserSchema);

export default User; 
