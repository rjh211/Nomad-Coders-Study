package hello.exception.api;

import hello.exception.exception.UserException;
import hello.exception.exhandler.ErrorResult;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class ApiExceptionV2Controller {

    //해당 메서드가 호출될경우 HttpStatus 지정
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public ErrorResult illegeExHandler(IllegalArgumentException e){
        //컨트롤러에서 ExceptionHandler 파라미터에 대한 에러 발생 시 본 메서드가 실행된다.
        //ExceptionHandlerExceptionResolver에서 자신이 해결할 수 잇는 Exception이 해당 컨트롤러에 존재하는지 찾은 후 있다면 실행
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("BAD", e.getMessage());
    }

    @ExceptionHandler/*(UserException.class)생략가능 메서드의 파리미터로 Exception을 넣을 경우*/
    public ResponseEntity<ErrorResult> userExHandler(UserException e){
        log.error("[exceptionHandler] ex", e);
        ErrorResult errorResult = new ErrorResult("USER-EX", e.getMessage());
        return new ResponseEntity(errorResult, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler
    public ErrorResult exHandler(Exception e){
        log.error("[exceptionHandler] ex", e);
        return new ErrorResult("EX", "@ExceptionHandler로 잡지 않은 나머지 예외 일괄처리(최상위 Exception 이기 떄문)");
    }

    @GetMapping("/api2/members/{id}")
    public MemberDto getMember(@PathVariable("id") String id){
        if(id.equals("ex")){
            throw new RuntimeException("잘못된 사용자");
        }
        if(id.equals("bad")){
            throw new IllegalArgumentException("잘못된 입력 값");
        }
        if(id.equals("user-ex")){
            throw new UserException("사용자 오류");
        }

        return new MemberDto(id, "hello "+ id);

    }

    @Data
    class MemberDto{
        public String message;
        public String id;
        public MemberDto(String id, String message){
            this.id = id;
            this.message = message;
        }
    }
}
