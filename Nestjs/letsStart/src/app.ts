import * as express from "express";
import { Cat, CatType } from "./app.model";

import * as bodyParser from "body-parser";

const app: express.Express = express();
const port: number = 8000;

// logging middleware
app.use((req, res, next) => {
  //미들웨어 함수 제작, 요청 -> 미들웨어 -> 라우터 -> 서버 순으로 전달이됨(모든 경로에 대해 사용가능)
  console.log("middle ware");
  next();
});

//Express에서 Json을 읽을수 있도록(req.body) middleware 추가
app.use(express.json());
// app.use(bodyParser.json());
// app.use(bodyParser.urlencoded({ extended: false }));

//Read 고양이 전체 데이터 조회
app.get("/cats", (req, res) => {
  try {
    const cats = Cat;
    // throw new Error("db connect error"); //error 발생
    res.status(200).send({
      success: true,
      data: {
        cats,
      },
    });
  } catch (err: any) {
    res.status(400).send({
      success: false,
      error: err.message,
    });
  }
});

//Read 특정 고양이 데이터 조회(동적 라우팅)
app.get("/cats/:id", (req, res) => {
  try {
    const params = req.params;
    const cat = Cat.find((cat) => {
      return cat.id === params.id;
    });
    res.status(200).send({
      success: true,
      data: {
        cat,
      },
    });
  } catch (err: any) {
    res.status(400).send({
      success: false,
      error: err.message,
    });
  }
});

//CREATE 새로운 고양이 데이터 추가 API
app.post("/cats", (req, res) => {
  try {
    const data = req.body;
    Cat.push(data);
    console.log(Cat);
    res.status(200).send({
      success: true,
      data: { data },
    });
  } catch (err: any) {
    res.status(400).send({
      success: false,
      error: err.message,
    });
  }
});

//404 middleware
app.use((req, res, next) => {
  //아무것도 찾지 못한 후, 마지막 미들웨어를 만나게 하여 에러 처리
  res.send({ error: "404 not found error" });
});

app.listen(port, () => {
  console.log("server is on ...");
});
