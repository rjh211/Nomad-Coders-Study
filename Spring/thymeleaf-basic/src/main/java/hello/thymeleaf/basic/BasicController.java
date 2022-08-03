package hello.thymeleaf.basic;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/basic")
public class BasicController {

    @GetMapping("text-basic")
    public String textBasic(Model model) {
        //ESCAPE : HTML에서 사용하는 특수 문자(ex. <, > 등)를 HTML 엔터티로 변경하는것
        // '&lt;' -> '<' 로 변환
        // thymeleaf에서 th:text 는 ESCAPE를 지원하고, th:utext는 UNESCAPE를 지원한다.
        model.addAttribute("data", "Hello Spring");
        return "basic/text-basic";
    }

    @GetMapping("text-unescaped")
    public String textBasicUnEscape(Model model) {
        //ESCAPE : HTML에서 사용하는 특수 문자(ex. <, > 등)를 HTML 엔터티로 변경하는것
        // '&lt;' -> '<' 로 변환
        // thymeleaf에서 th:text 는 ESCAPE를 지원하고, th:utext는 UNESCAPE를 지원한다.
        model.addAttribute("data", "<b>Hello Spring</b>");
        return "basic/text-unescaped";
    }
}
