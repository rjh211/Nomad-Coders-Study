import { Module } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { CatsModule } from './cats/cats.module';

@Module({
  imports: [CatsModule], // cats.module에서  export하여 자동으로 app.module의 impmort에 추가가된다.(provider에 등록하지 않아도됨)
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule {}
