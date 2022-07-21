package hello.springmvc.basic.response;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ResponseViewController {
    //view 호출을 위한 3가지 방법
    @RequestMapping("/response-view-v1")
    public ModelAndView responseViewv1(){
        ModelAndView mav = new ModelAndView("response/hello").addObject("data", "hello!");
        return mav;
    }

    @RequestMapping("/response-view-v2")
    public String responseViewv2(Model model){
        model.addAttribute("data", "hello!");
        return "response/hello";
    }

    @RequestMapping("/response/hello") //Controller의 경로와 같다면 자동으로 View를 찾아 전송해준다.
    public void responseViewv3(Model model){
        model.addAttribute("data", "hello!");
    }
}
