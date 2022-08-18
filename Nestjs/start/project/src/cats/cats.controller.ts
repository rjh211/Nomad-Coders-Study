import { CatRequestDto } from './dto/cats.request.dto';
import { PositiveIntPipe } from './../pipes/positiveint.pip';
import { HttpExceptionFilter } from '../common/exceptions/http-exception.filter';
import { CatsService } from './cats.service';
import {
  Body,
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
  UseInterceptors,
} from '@nestjs/common';
import { SuccessInterceptor } from 'src/common/interceptors/success.interceptor';

@Controller('cats')
@UseInterceptors(SuccessInterceptor) //인터셉터 의존성주입
export class CatsController {
  constructor(private readonly CatsService: CatsService) {}
  @Get()
  getCurrentCat() {
    return 'current cat';
  }

  @Post()
  async signUp(@Body() body: CatRequestDto) {
    //validation 적용
    return await this.CatsService.signUp(body);
  }

  @Post('login')
  login() {
    return 'login';
  }

  @Post('logout')
  logOut() {
    return 'logout';
  }

  @Post('upload/cats')
  uploadCatImg() {
    return 'uploadImg';
  }
}
