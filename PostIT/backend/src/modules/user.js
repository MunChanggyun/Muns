import mongoose, { Schema } from 'mongoose';
import bcrypt from 'bcrypt';
import jwt from 'jsonwebtoken';

const UserSchema = new Schema({
    userId: String,
    hashedPassword: String,
});

UserSchema.statics.findByUserId = async function(userId) {
    return this.findOne({ userId });
}

UserSchema.methods.setPassword = async function (password) {
    const hash = await bcrypt.hash(password, 10);
    this.hashedPassword = hash;
}

UserSchema.methods.checkPassword = async function (password) {
    const result = await bcrypt.compare(password, this.hashedPassword);
    return result;
};

UserSchema.methods.serialize = function() {
    const data = this.toJSON();
    delete data.hashedPassword;
    return data;
}

UserSchema.methods.generateToken = function () {
    const token = jwt.sign(
        {
            _id: this.id,
            userId: this.userId
        },
        process.env.JWT_SECRET,
        {
            expiresIn: '7d' // 7일동안 유효 하도록 설정
        }
    );
    return token;
}

const User = mongoose.model('User', UserSchema);

export default User;