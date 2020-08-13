
import Koa  from 'koa'
import Router  from 'koa-router'
import api  from './api'
import bodyParser  from 'koa-bodyparser'
import mongoose  from 'mongoose'

require = require('esm')(module);
module.exports = requrir("./main.js");

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

// request body 에 JSON형식으로 데이터를 넣어주면, 이를 파싱하여 서버에서 사용할 수 있다.
app.use(bodyParser());

app.use(router.routes()).use(router.allowedMethods());

// app.use( async (ctx, next) => {
//     console.log(ctx.url);
//     console.log(1);

//     if (ctx.query.autorized != "1") {
//         ctx.state = 401;
//         return;
//     }

//     // next 함수를 호출하면 Promise를 반환한다. next 함수가 반환하는 Promise는 다음에 처리해야 할 미들웨어가 끝나야 완료된다.
//     // next().then(() => {
//     //     console.log("end");
//     // });

//     await next();
//     console.log("end");
// });

// app.use((ctx, next) => {
//     console.log(2);
//     next(); // 다음 미들웨어 호출
// });

// app.use(ctx => {
//     ctx.body = 'hello node';
// });

const port = PORT || 4000;
app.listen(port, () => {
    console.log("Listening to port 4000");
});