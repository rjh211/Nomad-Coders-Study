package hello.core.web;

import hello.core.common.MyLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor//생성자에 Autowired 자동주입이됨
public class LogDemoController {
    private final LogDemoService logDemoService;
    private final MyLogger myLogger;
    //Scope존재하는 시기가 고객의 요청 ~ close 까지인데, 스프링빈이 생성되자마자 의존관계 주입을 하려해서 에러가 발생하게됨
    //Provider로 해결

    @RequestMapping("log-demo")
    @ResponseBody//화면없지 콘솔로만 받기위해 사용 (문자를 그대로 전달)
    public String logDemo(HttpServletRequest request){
        String requestURL = request.getRequestURL().toString();
        myLogger.setRequestURL(requestURL);

        myLogger.log("controller test");
        logDemoService.logic("testId");
        return "OK";
    }
}
