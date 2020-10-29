import mongoose from 'mongoose';

const { Schema } = mongoose;

const PostSchema = new Schema({
    title: {
        type: String,
        default: ''
    },
    content: {
        type: String,
        default: ''
    },
    top: {
        type: Number,
        default: 30
    },
    left: {
        type: Number,
        default: 30
    },
    color: {
        type: String,
        default: ''
    },
    publishedDate: {
        type: Date, 
        default: Date.now
    },
    user: {
        _id: mongoose.Types.ObjectId,
        userId: String
    }
});

const Post = mongoose.model('Post', PostSchema);

export default Post;