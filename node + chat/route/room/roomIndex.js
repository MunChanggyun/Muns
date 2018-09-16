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


router.post("/", function(req, res){
    var user = req.body.user_id;
    res.render("room.ejs",{"user": user});
});

//채팅방 리스트
router.post("/chatrooms", function(req, res){
    var rtndata = null;
    var str = "select"; 
        str +=  " cr.room_name as 'room_name',"; 
        str +=  " cr.room_id as 'room_id',"; 
        str +=  " group_concat(cu.id order by cu.id desc separator ', ') as 'room_users' ";
        str +=  " from user_rooms ur" ;
        str +=      " join chat_rooms cr" ;
        str +=       " on ur.room_id = cr.room_id" ;
        str +=       " join chat_user cu" ;
        str +=       " on ur.idx = cu.idx" ;
        str +=          " group by cr.room_name";
    var query = connection.query(str, function(err, rows){
        if(err) {
            rtndata = {"state" : "fail", "msg" : "방을 가져오는 동안 오류 발생 ."};
        }
        
        if(rows.length > 0){
            rtndata = {"state" : "success", "rows" : rows};
        }else{
            rtndata = {"state" : "success", "msg" : "참여할 수 있는 대화방이 없습니다."};
        }
        res.json(rtndata);
    });
});

//채팅방 만들기
router.post("/makeRoom", function(req, res){
    var user = req.body.user;
    var roomName = req.body.roomName;
    var rtndata = null;
    var rowId = "";

    var rQuery = connection.query("insert into chat_rooms(room_name, use_yn, create_date) values('" + roomName + "', 'Y', date_format(now(), '&Y-&m-&d'));", function(err, rows){
        if(err){
            throw err;
            rtndata = {"state" : "fail", "msg" : "방을 생성하는 동안 오류 발생 ."};
        }
        rowId = rows.insertId;
        if(rows.affectedRows > 0){
            var ucQuery = connection.query("insert into user_rooms(room_id, idx) values('" + rows.insertId + "', (select idx from chat_user where id = '" + user + "'))", function(err, rows){
                if(err){
                    throw err;

                    rtndata = {"state" : "fail", "msg" : "방을 생성하는 동안 오류 발생 ."};
                    return;
                }
                
                if(rows.affectedRows > 0){
                    rtndata = {"state" : "success", "msg" : "생성 완료", "roomIdx" : rowId};
                }else{
                    rtndata = {"state" : "success", "msg" : "새로운 방 생성에 실패했습니다."};
                }
                res.json(rtndata);
                return;
            });
        }else{
            rtndata = {"state" : "success", "msg" : "새로운 방 생성에 실패했습니다."};
            res.json(rtndata);
        }
        
    });
});

//채팅방 입장하기
router.post("/chat", function(req, res){
    var rtndata = null;
    var sQuery = connection.query("select count(idx) as cnt from user_rooms where idx = (select idx from chat_user where id = '" + req.body.user_id + "');", function(err, rows){
        if(err) throw err;
        if(rows[0].cnt == 0){
            var query = connection.query("insert into user_rooms(room_id, idx) value('" + req.body.room + "' , (select idx from chat_user where id = '" + req.body.user_id + "'));", function(err, rows){
                if(err) throw err;
                if(rows.affectedRows > 0){
                    rtndata = {"state" : "success", "result" : rows.affectedRows};
                }
        
                res.json(rtndata);
            });
        }else{
            rtndata = {"state" : "success", "result" : rows.affectedRows};
            res.json(rtndata);
        }
    });
});

module.exports = router;