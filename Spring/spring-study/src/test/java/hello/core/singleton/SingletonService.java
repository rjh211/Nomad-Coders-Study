package hello.core.singleton;

public class SingletonService {
    private static final SingletonService instance = new SingletonService();

    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService() {}
}

class test {
    public static void main(String[] args) {
        //SingletonService s = new SingletonService(); 에러발생
        SingletonService s = SingletonService.getInstance();
    }
}