import { Controller, Get, Body, Req, Param } from '@nestjs/common';
import { Request } from 'express';
import { AppService } from './app.service';
import { CatsService } from './cats/cats.service';

@Controller()
export class AppController {
  constructor(
    private readonly appService: AppService,
    private readonly catsService: CatsService, // private readonly catsService: CatsService, //추가 서비스에 대한 의존성 주입(의존성 주입을 위해서는 app.module내에서 provider가 되어야함)
  ) {}

  @Get()
  getHello(): string {
    return this.appService.getHello();
  }

  @Get('/test')
  getTest(@Req() req: Request, @Body() Body, @Param() Param): string {
    //@Req() : request, @Body : body, @Param: param
    console.log(req);
    return this.catsService.hiCatSErviceProduct();
    // return 'test';
    // return this.appService.getTest(Body, Param); //서비스에 바디와 파람을 넘겨서 서비스에서 요청처리
  }

  @Get('/test/:id/:name')
  getTestPram(
    @Req() req: Request,
    @Body() Body,
    @Param() Param: { id: string; name: string },
  ): string {
    //@Req() : request, @Body : body, @Param: param
    console.log(Param);
    return 'test';
    // return this.appService.getTest(Body, Param); //서비스에 바디와 파람을 넘겨서 서비스에서 요청처리
  }
}
