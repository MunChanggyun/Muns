
import Koa  from 'koa'
import Router  from 'koa-router'
import api  from './api'
import bodyParser  from 'koa-bodyparser'
import mongoose  from 'mongoose'
import jwtMiddleware from './lib/jwtMiddleware';
import serve from 'koa-static';
import path from 'path';
import send from 'koa-send';

// require = require('esm')(module);
// module.exports = requrir("./main.js");

// const Koa = require("koa");
// const Router = require("koa-router");
// const api = require("./api");
// const bodyParser = require("koa-bodyparser");
// const mongoose = require('mongoose');
require('dotenv').config();

// process.env 내부 값에 대한 레퍼런스 만들기
const { PORT, MONGO_URI } = process.env;

mongoose
.connect(MONGO_URI, { useNewUrlParser: true, useFindAndModify: false})
    .then(() => {
        console.log('connect mongoDB');
    })
    .catch(e => {
        console.log(e)
    });

const app = new Koa();
const router = new Router();

// 라우터 설정
router.use("/api", api.routes());

// request body 에 JSON형식으로 데이터를 넣어주면, 이를 파싱하여 서버에서 사용할 수 있다.s
app.use(bodyParser());
app.use(jwtMiddleware);

app.use(router.routes()).use(router.allowedMethods());

// 서버를 통해서 front-end 의 build 파일을 사용할 수 있도록 koa-static을 사용하여 정적 파일 제공 기능을 구현
// yarn add koa-static
const buildDirectory = path.resolve(__dirname, '../../blog-frontend/build');
app.use(serve(buildDirectory));
app.use(async ctx => {
    if (ctx.ststus == 404 || ctx.path.indexOf('/api') !== 0) {
        await send (ctx, 'index.html', { root: buildDirectory })
    }
});

const port = PORT || 4000;
app.listen(port, () => {
    console.log("Listening to port 4000");
});