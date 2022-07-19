package hello.springmvc.basic.request;

import hello.springmvc.basic.HelloData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {
    //HTML Form 형태, 쿼리파라미터로 요청 파라미터 전달받기
    @RequestMapping("/reqeust-param-v1")
    public void requestParamv1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));
        log.info("username = {}, age = {}", username, age);

        response.getWriter().write("ok"); //void type이어도 response body에 전달이됨
    }

    @ResponseBody
    @RequestMapping("/reqeust-param-v2")
    public String requestParamv2(@RequestParam("username") String memberName, @RequestParam("age") int memberAge) {
        log.info("username = {} , age = {}", memberName, memberAge);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/reqeust-param-v3")
    public String requestParamv3(@RequestParam String username, @RequestParam int age) {
        log.info("username = {} , age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/reqeust-param-v4")
    public String requestParamv4(String username, int age) { //요청파라미터와 변수명이 같다면 @RequestParam 조차 기재할 필요없다.
        log.info("username = {} , age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/reqeust-param-required")
    public String requestParamv5(@RequestParam(required = true) String username, int age) { //username을 필수값으로 지정 - 기본값 : true, 어길시에는 400 에러를 스프링에서 자동으로 응답을 보냄
        log.info("username = {} , age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/reqeust-param-default")
    public String requestParamv6(@RequestParam(required = true) String username, @RequestParam(defaultValue = "2") int age) { //default값을 지정
        log.info("username = {} , age = {}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/reqeust-param-map")
    public String requestParamv7(@RequestParam Map<String, Object> paramMap) { //모든 요청정보를 Map 형태로 받아옴
        log.info("username = {} , age = {}", paramMap.get("username"), paramMap.get("age"));
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v1")
    public String modelAttributeV1(@ModelAttribute HelloData helloData){ //@ModelAttribute는 @RequestParam에서 직접 가져올필요없이 요청파라미터까지 해당 객체에 모두 매핑을 시켜준다. (Getter, Setter등이 정의되어있어야함)
        log.info("username = {}, age = {}",helloData.getUsername(), helloData.getAge());
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/model-attribute-v2")
    public String modelAttributeV2(HelloData helloData){ //@ModelAttribute는 생략이 가능하다.
        /*Annotaion을 생략후 파라미터를 인자로 받아올때 스프링은 다음과 같은 절차를 따른다.
        * 1.String, Integer, int와 같은 단순 타입은 @RequestParam을 적용하여 가져온다.
        * 2. 나머지는 @ModelAttribute를 통해 인자를가져온다.
        * 3. argument resolver로 지정해둔 객체 타입은 @ModelAttribute로 가져오는 대상에서 제외된다. (Ex. HTTPRequest)
        */
        log.info("username = {}, age = {}",helloData.getUsername(), helloData.getAge());
        return "ok";
    }
}
