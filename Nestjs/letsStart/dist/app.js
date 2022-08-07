"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var express = require("express");
var cats_route_1 = require("./cats/cats.route");
var app = express();
var port = 8000;
app.use(function (req, res, next) {
    console.log("middle ware");
    next();
});
app.use(express.json());
app.use(cats_route_1.default);
app.use(function (req, res, next) {
    res.send({ error: "404 not found error" });
});
app.listen(port, function () {
    console.log("server is on ...");
});
//# sourceMappingURL=app.js.map