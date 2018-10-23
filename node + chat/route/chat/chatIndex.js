var express = require("express");
var router = express.Router();
var app = express();
var http = require('http').Server(app);
var mysql = require("mysql");
var connection = mysql.createConnection({
    host : 'localhost',
    port : 3306,
    user : "root",
    password : "password",
    database : 'database'
});

router.post("/", function(req, res){
    var user = req.body.user_id;
    var room = req.body.room;
    res.render("chat.ejs", {"user" : user, "room" : room});


});

router.post("/members", function(req, res){
    var roomIdx = req.body.roomIdx;
    var rtnData = null;
    var query = connection.query("select id from chat_user where idx in (select idx from user_rooms where room_id = '" + roomIdx + "');", function(err, rows){
        if(err) throw err;

        if(rows.length > 0){
            rtnData = {result : "success", rows : rows};
            res.send(rtnData);
        }else{
            rtnData = {result : "success", msg : "잘못된 방으로 들오셨습니다. 다시 접속해 주세요"};
            res.send(rtnData);
        }
    });
});


module.exports = router;
