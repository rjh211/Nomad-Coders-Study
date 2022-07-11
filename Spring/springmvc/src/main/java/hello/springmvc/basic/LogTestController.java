package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j //자동으로 LoggerFactory.getLogger를 해당 클래스로 입력을 해준다.
@RestController //@Controller와 달리 ModelAndView 반환을 하지 않아도 되고, String을 반환하면 화면에 String이 보여지게 된다.(Http Body에 바로 String을 박아둔다.)
public class LogTestController {

//    private final Logger log = LoggerFactory.getLogger(getClass());//나의 클래스를 등록하여 로그를 남긴다.

    @RequestMapping("/log-test")
    public String logTest(){
        String name = "Spring";

        //log.error("log error" + name)과 같은 형태를 사용할 수 있긴하지만, log level에 따라서 출력을 안할경우 String append 연산을 생략하기위해 이와같은 형태는 사용을 지양한다.
        log.trace("log trace = {}", name);
        log.debug("log debug = {}", name);
        log.info("log info = {}", name);        //상태에 따른 에러 로그 출력가능
        log.warn("log warn = {}", name);
        log.error("log error = {}", name);
        return "ok";
    }
}
