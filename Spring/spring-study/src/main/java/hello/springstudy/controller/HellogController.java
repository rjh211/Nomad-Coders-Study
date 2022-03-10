package hello.springstudy.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HellogController {

    @GetMapping("/hello")   //url에서 localhost:8080 이후 "/hello"가 들어올경우 매핑시켜줌
    public String hello(Model model){
        model.addAttribute("data", "hello!!");//model로 key, value를 넘겨줌 , key : data, value : hello!
        return "hello"; //resources/template 의 파일명을 찾아서 렌더링 여기서는 hello.html(model attribute와 함께 넘어감)
    }

    @GetMapping("hello-mvc")
    public String helloMove(@RequestParam(value = "name", required = false)String name, Model model){
        model.addAttribute("name", name);
        return "hello-template";
    }

    @GetMapping("hello-string")
    @ResponseBody //html의 <body>부에 직접 넣겠다는 의미
    public String hellostring(@RequestParam("name") String name){
        return "hello" + name;
    }

    @GetMapping("hello-api")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello; //객체전달 (api 방식) => 화면에 json 형태로 전달이됨
    }

    static class Hello {    //부모 class내에서 자식 class 생성 외부에서 사용시 HelloController.Hello 와 같이 사용가능
        private String name;

        public String getName(){
            return name;
        }

        public void setName(String name){
            this.name = name;
        }
    }
}
