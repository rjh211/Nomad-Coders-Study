package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor//생성자에 Autowired 자동주입이됨
public class LogDemoController {
    private final LogDemoService logDemoService;

    //Controller에서 의존관계를 찾기위한 Provider
    //고객의 요청이 들어왓을경우 의존관계를 주입받아 메서드를 실행시킨다.
    //스프링 컨테이너 생성시점에 의존관계주입 x
    private final MyLogger myLogger;

    //Scope존재하는 시기가 고객의 요청 ~ close 까지인데, 스프링빈이 생성되자마자 의존관계 주입을 하려해서 에러가 발생하게됨
    //Provider로 해결(의존관계 탐색)

    @RequestMapping("log-demo")
    @ResponseBody//화면없지 콘솔로만 받기위해 사용 (문자를 그대로 전달)
    public String logDemo(HttpServletRequest request){
        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);

        //내가만든 클래스가 아닌 스프링이 만들어준(CGLIB) 클래스가 찍히게됨(Provider처럼 동작함)
        System.out.println("myLogger = " + myLogger.getClass());

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
