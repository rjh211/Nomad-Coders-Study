import { Injectable } from '@nestjs/common';

@Injectable()
export class CatsService {
  hiCatSErviceProduct() {
    return 'hello Cat';
  }
}
