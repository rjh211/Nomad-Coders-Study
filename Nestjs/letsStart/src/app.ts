import * as express from "express";
import { Cat, CatType } from "./app.model";

const app: express.Express = express();
const port: number = 8000;

app.get("/", (req: express.Request, res: express.Response) => {
  console.log(req);
  res.send({ cats: Cat }); //key 와 value가 같은경우 Json 전송시에는 key:value형태로 보내지 않아도된다.
});

app.listen(port, () => {
  console.log("server is on ...");
});
