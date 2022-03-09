package hello.springstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HellogController {
    @GetMapping("/hello")   //url에서 localhost:8080 이후 "/hello"가 들어올경우 매핑시켜줌
    public String hello(Model model){
        model.addAttribute("data", "hello!!");//model로 key, value를 넘겨줌 , key : data, value : hello!
        return "hello"; //resources/template 의 파일명을 찾아서 렌더링 여기서는 hello.html(model attribute와 함께 넘어감)
    }

}
