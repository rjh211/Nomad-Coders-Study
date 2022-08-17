import { PositiveIntPipe } from './../pipes/positiveint.pip';
import { HttpExceptionFilter } from '../common/exceptions/http-exception.filter';
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
  async signUp() {
    return 'signup';
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
