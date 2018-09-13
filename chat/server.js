var express = require("express");
var app = express();
var server = app.listen(3999);
var io = require("socket.io").listen(server);


//chating
var roomidx = "";
io.on("connection", function(socket){
    
    var name = "";

    socket.emit("connection", {type : "connected"});

    socket.on("connection", function(data){
        roomidx = data.room;
        name = data.name;
        socket.join("roomidx" + roomidx);
        io.emit("change name");
    });

    socket.on("disconnect", function(user){       
        io.emit("change name");
    });

    

    socket.on("send message", function(name, text, room){
        var msg = name + " : " + text;
        socket.in("roomidx" + room).emit("receive message", msg);
    })
});


