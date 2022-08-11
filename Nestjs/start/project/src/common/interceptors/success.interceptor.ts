import {
  CallHandler,
  ExecutionContext,
  Injectable,
  NestInterceptor,
} from '@nestjs/common';
import { Observable } from 'rxjs';
import { tap, map } from 'rxjs/operators';

//MiddleWare 실행 이후에 인터셉터가 실행된다.

@Injectable()
export class SuccessInterceptor implements NestInterceptor {
  //성공시 시간측정 인터셉터 모듈화
  intercept(context: ExecutionContext, next: CallHandler): Observable<any> {
    console.log('Before...');

    const now = Date.now();
    return next.handle().pipe(
      map((data) => ({
        success: true,
        data,
        time: Date.now() - now,
      })),
    );
  }
}
