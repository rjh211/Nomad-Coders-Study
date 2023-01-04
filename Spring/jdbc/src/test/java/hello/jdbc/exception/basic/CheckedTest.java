package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class CheckedTest {

    /*
    * Exception을 상속받은 예외는 체크 예외가 된다.
    * */
    static class MyCheckedException extends Exception {
        public MyCheckedException(String message) {
            super(message);
        }
    }

    static class Service{
        Repository repository = new Repository();
        /*
        * 예외 처리 코드
        */
        public void callCatch(){
            //repository.call의 예외 발생시 throw or 처리를 해야한다.
            //예외를 받아 처리를 하는 서비스
            try {
                repository.call();
            } catch (MyCheckedException e) {
                log.info("예외처리, message={}", e.getMessage(), e);
            }
        }

        public void callthrow() throws MyCheckedException {
            //예외를 잡지않고 밖으로 던지는 서비스
            repository.call();
        }
    }

    static class Repository{
        public void call() throws MyCheckedException {
            //chekced exception은 반드시 throws로 던지게 컴파일러가 체크를 해준다.
            throw new MyCheckedException("ex");
        }
    }

    @Test
    void chekced_catch(){
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void checked_throw(){
        Service service = new Service();
        Assertions.assertThatThrownBy(()-> service.callthrow()).isInstanceOf(MyCheckedException.class);
    }
}
