var express = require("express");
var router = express.Router();
var mysql = require("mysql");
var connection = mysql.createConnection({
    host : 'localhost',
    port : 3306,
    user : "root",
    password : "ckd764589",
    database : 'node_study'
});

connection.connect();

router.get("/", function(req, res){
    res.render("login.ejs");
})

router.post("/login", function(req, res){
    var user_id = req.body.user_id;
    var password = req.body.password;
    var query = connection.query("select id from chat_user where id='" + user_id + "'", function(err, rows){
        if(err){
            return;
        } 
        
        if(rows.length > 0){
            return "이미 등록된 아이디 입니다.";
        }else{
            
            var insertUser= connection.query("insert into chat_user(id, password) values('" + user_id + "','" + password + "')", function(err, rows){
                if(err){
                    return err;
                } else{
                    // res.redirect(307 , "../chatting?user=" +user_id);
                    res.redirect(307 , "../chatroom");
                }
               
            });
        }
    })
});
module.exports = router;