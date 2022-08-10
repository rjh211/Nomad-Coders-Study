import { Injectable } from '@nestjs/common';

@Injectable() //DI가 가능해짐
export class CatsService {
  hiCatSErviceProduct() {
    return 'hello Cat';
  }
}
