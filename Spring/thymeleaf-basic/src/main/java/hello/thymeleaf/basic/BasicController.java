package hello.thymeleaf.basic;

import lombok.Data;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @GetMapping("/variable")
    public String variable(Model model){
        User userA = new User("userA", 10);
        User userB = new User("userB", 20);

        List<User> list = new ArrayList<>();
        list.add(userA);
        list.add(userB);

        Map<String, User> map = new HashMap<>();
        map.put("userA", userA);
        map.put("userB", userB);

        model.addAttribute("user", userA);
        model.addAttribute("users", list);
        model.addAttribute("userMap", map);

        return "basic/variable";
    }

    @GetMapping("/basic-objects")
    public String basicObjects(HttpSession session){
        session.setAttribute("sessionData", "Hello Session Data");
        return "basic/basic-objects";
    }

    @Component("helloBean")
    static class HelloBean{
        public String hello(String data){
            return "Hello" + data;
        }
    }

    @GetMapping("/date")
    public String date(Model model){
        model.addAttribute("localDateTime", LocalDateTime.now());
        return "basic/date";
    }

    @GetMapping("link")
    public String link(Model model){
        model.addAttribute("param1", "data1");
        model.addAttribute("param2", "data2");
        return "basic/link";

    }

    @GetMapping("/literal")
    public String literal(Model model){
        model.addAttribute("data", "Spring!");

        return "basic/literal";
    }

    @GetMapping("/operation")
    public String operation(Model model){
        model.addAttribute("nullData", null);
        model.addAttribute("data", "Spring");

        return "basic/operation";
    }

    @GetMapping("/condition")
    private String condition(Model model){
        addUsers(model);
        return "basic/condition";
    }

    @GetMapping("/attribute")
    public String attribute(Model model){
        return "basic/attribute";
    }

    @GetMapping("/each")
    public String each(Model model){
        addUsers(model);
        return "basic/each";
    }

    private void addUsers(Model model){
        List<User> list = new ArrayList<>();
        list.add(new User("UserA", 10));
        list.add(new User("UserB", 20));
        list.add(new User("UserC", 30));

        model.addAttribute("users", list);
    }
    @Data
    static class User {
        private String username;
        private int age;

        public User(String username, int age){
            this.username = username;
            this.age = age;
        }

    }
}
