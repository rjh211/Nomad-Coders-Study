//route 관리를 위한 ts
import { Cat, CatType } from "../cats/cats.model";
import { Router } from "express";

const router = Router();

//Read 고양이 전체 데이터 조회
router.get("/cats", (req, res) => {
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
router.get("/cats/:id", (req, res) => {
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
router.post("/cats", (req, res) => {
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

//UPDATE 고양이 데이터 업데이트 -> PUT
router.put("/cats/:id", (req, res) => {
  try {
    const params = req.params;
    const body = req.body;
    let result;

    Cat.forEach((cat) => {
      if (cat.id === params.id) {
        cat = body;
        result = cat;
      }
    });

    res.status(200).send({
      success: true,
      data: {
        success: true,
        data: { result },
      },
    });
  } catch (err: any) {
    res.status(400).send({
      success: false,
      error: err.message,
    });
  }
});

//UPDATE 고양이 데이터 일부 업데이트 -> PATCH
router.patch("/cats/:id", (req, res) => {
  try {
    const params = req.params;
    const body = req.body;
    let result;

    Cat.forEach((cat) => {
      if (cat.id === params.id) {
        //중복된 key에 대한 value값을 바꾸도록 구조분해할당 사용
        cat = { ...cat, ...body }; //구조분해할당 : 배열이나 객체의 속성을 해체하여 그 값을 개별 변수에 담을 수 있게하는 JavaScript 표현식
        result = cat;
      }
    });

    res.status(200).send({
      success: true,
      data: {
        success: true,
        data: { result },
      },
    });
  } catch (err: any) {
    res.status(400).send({
      success: false,
      error: err.message,
    });
  }
});

//DELETE 고양이 데이터 삭제 -> DELETE
router.delete("/cats/:id", (req, res) => {
  try {
    const params = req.params;
    const newCat = Cat.filter((cat) => cat.id !== params.id);
    res.status(200).send({
      success: true,
      data: { newCat },
    });
  } catch (err: any) {
    res.status(400).send({
      success: false,
      error: err.message,
    });
  }
});

export default router; //라우터들을 export
