import Router from 'koa-router';
import * as postCtrl from './PostCtrl/postCtrl';

const post = new Router();

console.log("post router");

post.post('/list', postCtrl.postList);
post.post('/update/:_id', postCtrl.updatePost);
post.post('/insert', postCtrl.insertPost);
post.post('/remove/:_id', postCtrl.removePost);
post.post('/position/:id', postCtrl.setPosition);
post.post('/color/:_id', postCtrl.changeColor);

export default post;