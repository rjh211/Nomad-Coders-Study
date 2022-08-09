import { Controller, Get, Body, Req, Param } from '@nestjs/common';
import { Request } from 'express';
import { AppService } from './app.service';

@Controller()
export class AppController {
  constructor(private readonly appService: AppService) {}

  @Get()
  getHello(): string {
    return this.appService.getHello();
  }

  @Get('/test')
  getTest(@Req() req: Request, @Body() Body, @Param() Param): string {
    //@Req() : request, @Body : body, @Param: param
    console.log(req);
    return 'test';
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
