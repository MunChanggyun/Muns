import Koa from 'koa'
import Router from 'koa-router';
import bodyParser from 'koa-bodyparser';
import mongoose from 'mongoose';
import jwtMiddleware from './lib/jwtMiddleware';
import api from './api';
require('dotenv').config();

const app = new Koa();
const router = new Router();

const { PORT, MONGO_URL } = process.env;

mongoose.connect(MONGO_URL, { useNewUrlParser: true, useFindAndModify: false })
    .then(() => {
        console.log("connect to mongo DB");
    })
    .catch(e => {
        console.log(e);
    })

router.use('/postit/api', api.routes());

app.use(bodyParser());

app.use(jwtMiddleware);

app.use(router.routes()).use(router.allowedMethods());

app.listen(PORT, () => {
    console.log(PORT + " port listening");
});