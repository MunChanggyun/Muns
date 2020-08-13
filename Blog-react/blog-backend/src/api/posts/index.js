// const Router = require("koa-router");
// const postsCtrl = require("./posts.ctrl");
// const checkLoggedIn = require('../../lib/checkLoggedin');
import Router from "koa-router";
import * as postsCtrl from "./posts.ctrl";
import checkLoggedIn from '../../lib/checkLoggedin';


const posts = new Router();

posts.get("/", postsCtrl.list);
posts.post("/", checkLoggedIn, postsCtrl.write);

// posts.get("/:id", postsCtrl.checkObjectId, postsCtrl.read);
// posts.delete("/:id", postsCtrl.checkObjectId, postsCtrl.remove);
// posts.put("/:id", postsCtrl.checkObjectId, postsCtrl.replace);
// posts.patch("/:id", postsCtrl.checkObjectId, postsCtrl.update);

const post = new Router();

post.get("/", postsCtrl.read);
post.delete("/", postsCtrl.checkOwnPost, postsCtrl.remove);
post.put("/", postsCtrl.replace);
post.patch("/", postsCtrl.checkOwnPost, postsCtrl.update);

posts.use("/:id", postsCtrl.getPostById, post.routes());

// module.exports = posts;
export default posts;
