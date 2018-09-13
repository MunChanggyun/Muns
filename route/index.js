var express = require("express");
var router = express.Router();
var chat = require("./chat/chatIndex");
var login = require("./login/loginIndex");
var room = require("./room/roomIndex");

// router.get("/chat",function(req, res){
//      res.render(__dirname + "/view/chat.ejs" , {user : "user"});
//  });

router.use("/", login);
router.use("/chatting", chat);
router.use("/chatroom", room);

module.exports = router;