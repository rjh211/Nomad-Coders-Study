import { HttpExceptionFilter } from './../http-exception.filter';
import { CatsService } from './cats.service';
import {
  Controller,
  Delete,
  Get,
  HttpException,
  Param,
  ParseIntPipe,
  Patch,
  Post,
  Put,
  UseFilters,
} from '@nestjs/common';

@Controller('cats')
export class CatsController {
  constructor(private readonly CatsService: CatsService) {}
  @Get()
  @UseFilters(HttpExceptionFilter) //하위 메서드에서 발생한 Exception이 내부에서 필터링됨(filter.ts의 @Catch(HTTPExceptionFilter)와 매칭), 해당 메서드에서만 적용 하는 방법
  getAllCat() {
    throw new HttpException('api is broken', 401); //Nestjs에서의 Exception 처리방법
    return 'all cat';
  }

  @Get(':id') //동적라우팅
  getCat(@Param('id', ParseIntPipe) param: number) {
    //pipe를 통한 자동형변환 (id가 number type 일경우 자동변환)
    //숫자가 아닌형태로 들어오는경우 유효성검사를 통해 오류를 발생시켜준다.(Exception Filter에서 오류 출력)
    console.log(param);
    return 'one cat';
  }

  @Post()
  creatCat() {
    return 'create cat';
  }

  @Put(':id')
  updateCat() {
    return 'update cat';
  }

  @Patch(':id')
  updatePartialCat() {
    return 'update';
  }

  @Delete(':id')
  deleteCat() {
    return 'delete cat';
  }
}
