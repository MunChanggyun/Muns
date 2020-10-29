import Router from 'koa-router';
import auth from './Auth/auth';
import post from './Post/post';

const api = new Router();

api.use('/auth', auth.routes());
api.use('/post', post.routes());

export default api;