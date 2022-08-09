import { CatsService } from './cats.service';
import { Controller, Delete, Get, Patch, Post, Put } from '@nestjs/common';

@Controller('cats')
export class CatsController {
  constructor(private readonly CatsService: CatsService) {}
  @Get()
  getAllCat() {
    return 'all cat';
  }

  @Get(':id') //동적라우팅
  getCat() {
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
