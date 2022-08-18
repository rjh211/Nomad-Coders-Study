import { Cat } from './cats.schema';
import { Injectable, UnauthorizedException } from '@nestjs/common';
import { InjectModel } from '@nestjs/mongoose';
import { Model } from 'mongoose';
import { CatRequestDto } from './dto/cats.request.dto';
import * as bcrypt from 'bcrypt';

@Injectable() //DI가 가능해짐
export class CatsService {
  constructor(@InjectModel(Cat.name) private readonly catModel: Model<Cat>) {}
  hiCatSErviceProduct() {
    return 'hello Cat';
  }
  async signUp(body: CatRequestDto) {
    const { email, name, password } = body;
    const isCatExist = await this.catModel.exists({ email }); //모델내에 존재하는 중복체크 로직(MongoDB)
    if (isCatExist) {
      throw new UnauthorizedException('해당 고양이는 이미 존재합니다.'); //403 에러를 자동으로 발생시켜주는 Exception
    }

    const hashedPassword = await bcrypt.hash(password, 10);
    const cat = await this.catModel.create({
      email,
      name,
      password: hashedPassword,
    });
    return cat.readOnlyData; //password정보를 감춘 데이터(스키마에서 구현)
  }
}
