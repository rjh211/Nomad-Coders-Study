package hello.exception.exhandler.advice;

import hello.exception.api.ApiExceptionV2Controller;
import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice/*(*//*annotations = {RestController.class} Annotation에 적용*//**//*hello.exception.exhandler 특정 패키지에 적용*//**//*assignableTypes={ControllerInterface.class} 클래스를 직접 지정(부모 클래스 지정시 자식 컨트롤러에도 적용)*//*)*/
public class ExControllerAdvice {
    //예외처리부분과 기능 부분을 나눠줌
    //@RestControllerAdvice는 대상으로 지정한 여러 컨트롤러에 @Exceptionhandler / @InitBinder 기능을 부여해주는 역할이다.
    //대상지정을 안할 경우 모든 컨트롤러에 글로벌하게 적용이된다.

    //해당 메서드가 호출될경우 HttpStatus 지정
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegeExHandler(IllegalArgumentException e) {
        //컨트롤러에서 ExceptionHandler 파라미터에 대한 에러 발생 시 본 메서드가 실행된다.
        //ExceptionHandlerExceptionResolver에서 자신이 해결할 수 잇는 Exception이 해당 컨트롤러에 존재하는지 찾은 후 있다면 실행
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler/*(UserException.class)생략가능 메서드의 파리미터로 Exception을 넣을 경우*/
    public ResponseEntity<ErrorResult> userExHandler(UserException e) {
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e) {
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("EX", "@ExceptionHandler로 잡지 않은 나머지 예외 일괄처리(최상위 Exception 이기 떄문)");
    }

}
