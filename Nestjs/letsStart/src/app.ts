import * as express from "express";
const app: express.Express = express();
const port: number = 8000;

app.get("/", (req: express.Request, res: express.Response) => {
  res.send({ hello: "world" }); //JSON 객체도 전달가능
});

app.listen(port, () => {
  console.log(`Example app listening at http://localhost:${port}`);
});
