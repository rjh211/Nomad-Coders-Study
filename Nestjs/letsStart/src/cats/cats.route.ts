//route 관리를 위한 ts
import { Cat, CatType } from "../cats/cats.model";
import { Router } from "express";
import {
  readAllcat,
  readCat,
  creatCat,
  updateCat,
  updateAllCat,
  deleteCat,
} from "./cats.service";

const router = Router();

//Read 고양이 전체 데이터 조회
router.get("/cats", readAllcat);

//Read 특정 고양이 데이터 조회(동적 라우팅)
router.get("/cats/:id", readCat);

//CREATE 새로운 고양이 데이터 추가 API
router.post("/cats", creatCat);

//UPDATE 고양이 데이터 업데이트 -> PUT
router.put("/cats/:id", updateAllCat);

//UPDATE 고양이 데이터 일부 업데이트 -> PATCH
router.patch("/cats/:id", updateCat);

//DELETE 고양이 데이터 삭제 -> DELETE
router.delete("/cats/:id", deleteCat);

export default router; //라우터들을 export
