import { Module } from '@nestjs/common';
import { CatsController } from './cats.controller';
import { CatsService } from './cats.service';

@Module({
  controllers: [CatsController],
  providers: [CatsService],
  exports: [CatsService], // 매번 service에 추가를 하는 것은 유지보수에 안좋은 영향을 미치고, 단일책임원칙에 위배된다. 따라서 exports를 사용하여 다른 모듈에서 접근이 가능하도록 등록한다.
})
export class CatsModule {}
