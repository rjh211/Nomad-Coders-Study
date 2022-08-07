import * as express from "express";
import catsRouter from "./cats/cats.route";

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

app.use(catsRouter); //cats.route.ts의  router를 미들웨어 형태로 사용

//404 middleware
app.use((req, res, next) => {
  //아무것도 찾지 못한 후, 마지막 미들웨어를 만나게 하여 에러 처리
  res.send({ error: "404 not found error" });
});

app.listen(port, () => {
  console.log("server is on ...");
});
