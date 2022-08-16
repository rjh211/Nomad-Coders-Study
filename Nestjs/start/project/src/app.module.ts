import { LoggerMiddleware } from './common/middlewares/logger.middleware';
import { MiddlewareConsumer, Module, NestModule } from '@nestjs/common';
import { AppController } from './app.controller';
import { AppService } from './app.service';
import { CatsModule } from './cats/cats.module';
import { MongooseModule } from '@nestjs/mongoose';
import { ConfigModule } from '@nestjs/config';

@Module({
  imports: [
    CatsModule,
    ConfigModule.forRoot(),
    MongooseModule.forRoot(process.env.MONGODB_URI, {
      useNewUrlParser: true,
      useUnifiedTopology: true,
      // useCreateIndex: true, mongoose 6.0이상 버전에서는 디폴트로 지원해주기 떄문에 값을 지워야 실행이된다.,
      // useFindAndModify: false,
    }),
  ], // cats.module에서  export하여 자동으로 app.module의 impmort에 추가가된다.(provider에 등록하지 않아도됨)
  controllers: [AppController],
  providers: [AppService],
})
export class AppModule implements NestModule {
  //middleware을 적용;
  configure(consumer: MiddlewareConsumer) {
    consumer.apply(LoggerMiddleware).forRoutes('cats'); //cats 라우터를 타겟으로 미들웨어 생성 (*하면 모든 엔드포인트를 타겟으로 지정함)
  }
}
