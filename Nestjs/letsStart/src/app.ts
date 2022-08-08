import * as express from "express";
import catsRouter from "./cats/cats.route";

class Server {
  public app: express.Application;
  private port: number = 8000;

  constructor() {
    const app: express.Application = express();
    this.app = app;
  }

  private setRoute() {
    //라우터 세팅
    this.app.use(catsRouter); //cats.route.ts의  router를 미들웨어 형태로 사용
  }

  private setMiddleware() {
    //미들웨어 세팅
    // logging middleware
    this.app.use((req, res, next) => {
      //미들웨어 함수 제작, 요청 -> 미들웨어 -> 라우터 -> 서버 순으로 전달이됨(모든 경로에 대해 사용가능)
      console.log("middle ware");
      next();
    });

    //Express에서 Json을 읽을수 있도록(req.body) middleware 추가
    this.app.use(express.json());

    this.setRoute();

    //404 middleware
    this.app.use((req, res, next) => {
      //아무것도 찾지 못한 후, 마지막 미들웨어를 만나게 하여 에러 처리
      res.send({ error: "404 not found error" });
    });
  }
  public listen() {
    this.app.listen(this.port, () => {
      console.log("server is on ...");
    });
  }
}

function init() {
  const server = new Server();
  server.listen();
}

init();
