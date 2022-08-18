import { HttpExceptionFilter } from './common/exceptions/http-exception.filter';
import { NestFactory } from '@nestjs/core';
import { AppModule } from './app.module';

async function bootstrap() {
  const app = await NestFactory.create(AppModule);
  const port = process.env.PORT;
  app.useGlobalFilters(new HttpExceptionFilter()); //global로 exception filter 적용
  await app.listen(port);
}
bootstrap();
