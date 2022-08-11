import { Injectable, Logger, NestMiddleware } from '@nestjs/common';
import { NextFunction, Request, Response } from 'express';

@Injectable()
export class LoggerMiddleware implements NestMiddleware {
  private logger = new Logger('HTTP'); //Nestjs에서 주로 사용하는 로거
  use(req: Request, res: Response, next: NextFunction) {
    this.logger.log(req.ip, req.originalUrl);
    res.on('finish', () => {
      //해당 메서드가 완료되었을때 로깅
      this.logger.log(res.statusCode);
    });
    next();
  }
}
