package hello.core.lifecycle;

//시작시 네트워크 연결 / 종료시 네트워크 연결 해제를 위한 객체

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class NetworkClient implements InitializingBean, DisposableBean {

    private String url;
    public NetworkClient(){
        System.out.println("생성자 호출, url = " + url);
    }

    public void setUrl(String url){
        this.url = url;
    }

    //서비스 시작시 호출
    public void connect(){
        System.out.println("connect : " + url);
    }

    public void call(String message){
        System.out.println("call : "  + url + "message = " + message);
    }

    //서비스 종료시 호출
    public void disconnect(){
        System.out.println("disconnect " + url);
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("NetworkClient.afterPropertiesSet");
        //Property 설정이 끝나면(의존관계 주입이 끝나면)실행되는 메서드
        connect();
        call("초기화 연결 메세지");
    }

    @Override
    public void destroy() throws Exception {
        //싱글톤 빈들을 Distory하며 실행되는 메서드
        System.out.println("NetworkClient.destroy");
        disconnect();
    }
}
