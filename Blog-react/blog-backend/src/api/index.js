import Router from 'koa-router';
import posts from './posts';
import auth from './auth';

// const Router = require("koa-router");
// const posts = require("./posts");

const api = new Router();

api.use("/posts", posts.routes());
api.use("/auth", auth.routes());

// 라우터를 내보내기
//module.exports = api;
export default api;