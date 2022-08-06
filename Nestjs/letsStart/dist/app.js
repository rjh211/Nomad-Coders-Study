"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var express = require("express");
var app_model_1 = require("./app.model");
var app = express();
var port = 8000;
app.get("/", function (req, res) {
    console.log(req);
    res.send({ cats: app_model_1.Cat });
});
app.listen(port, function () {
    console.log("server is on ...");
});
//# sourceMappingURL=app.js.map