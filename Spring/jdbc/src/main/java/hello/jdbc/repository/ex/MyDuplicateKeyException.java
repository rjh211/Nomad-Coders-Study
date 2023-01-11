package hello.jdbc.repository.ex;

//Intert시 데이터 Unique 제약조건에 위반되는 경우 전달될 런타임예외
public class MyDuplicateKeyException extends  MyDbException{
    public MyDuplicateKeyException() {
    }

    public MyDuplicateKeyException(String message) {
        super(message);
    }

    public MyDuplicateKeyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MyDuplicateKeyException(Throwable cause) {
        super(cause);
    }
}
