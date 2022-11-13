package hello.exception.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "error.boad")
public class BadRequestException extends RuntimeException {
    //원래 RuntimeException은 500에러가 발생한다.
    //@ResponseStatus 어노테이션을 사용하여 상태코드 및 메세지 설정이 가능
    //reason에는 상수를 박을 수도 있고, Config에 설정된 메세지를 출력할 수도 있다.(messages.properties)
}
