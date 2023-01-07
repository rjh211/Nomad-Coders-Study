package hello.jdbc.exception.basic;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

@Slf4j
public class UncheckedTest {
    static class MyUncheckedException extends RuntimeException {
        public MyUncheckedException(String message) {
            super(message);
        }
    }
    /*
     * Unchecked 예외는 예외를 잡거나 던지지 않아도 된다.
     * 자동으로 밖으로 던져진다.
     */
    static class Service{
        /**
         * 필요한 경우 예외를 잡아서 처리하면된다.
         */
        Repository repository = new Repository();
        public void callCatch(){
            try{
                repository.call();
            } catch(MyUncheckedException e){
                log.info("예외 처리 , message={}", e.getMessage(), e);
            }
        }

        public void callThrow(){
            repository.call();
        }
    }

    static class Repository{
        public void call(){
            throw new MyUncheckedException("ex");
        }
    }

    @Test
    void unchecked_catch(){
        Service service = new Service();
        service.callCatch();
    }

    @Test
    void unchecked_throw(){
        Service service = new Service();
        Assertions.assertThatThrownBy(()->service.callThrow()).isInstanceOf(MyUncheckedException.class);
    }
}
