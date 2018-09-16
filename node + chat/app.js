var express = require("express");
var app = express();
var parser = require("body-parser");
var http = require('http').Server(app);
var io = require('socket.io')(http);
var route = require("./route/index");
app.use(parser.json()); 
app.use(parser.urlencoded({extended:true})); 

http.listen(3000, function(){
    console.log("chat start!!!");
});

app.use(route);
app.use(parser.json());
app.use(parser.urlencoded({extended:true}));


