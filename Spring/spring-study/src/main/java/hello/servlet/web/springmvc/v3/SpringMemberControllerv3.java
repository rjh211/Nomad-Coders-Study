package hello.servlet.web.springmvc.v3;

import hello.servlet.domain.member.Member;
import hello.servlet.domain.member.MemberRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


@Controller
@RequestMapping("springmvc/v3/members")
public class SpringMemberControllerv3 {
    private MemberRepository memberRepository = MemberRepository.getInstance();

    @RequestMapping(value = "/new-form", method = RequestMethod.GET) //클래스 레벨의 RequestMapping이 prefix가 됨, method작성 안할경우, 모든 HTTP Method방식에 대해 오픈이됨
    public String newForm(){
        return "new-form"; //Annotation기반은 ModelAndView or String 어떤형태로든 반환이 가능하다.
    }

    //@RequestMapping(method = RequestMethod.GET)//members는 뒤에 붙을 Mapping name이 없음
    @GetMapping //위 주석된 RequestMapping Annotation과 동일한 기능이됨
    public String save(Model model) {
        List<Member> members = memberRepository.findAll();
        model.addAttribute("members", members);
        return "members";
    }

    //@RequestMapping(value = "/save", method = RequestMethod.POST)
    @PostMapping("/save") //위 주석된 RequestMapping Annotation과 동일한 기능이됨
    public String members(@RequestParam("username") String username, @RequestParam("age") int age, Model model) { //paramter를 지정하여 가져올 수 있음
        Member member = new Member(username, age);
        memberRepository.save(member);

        model.addAttribute("member", member);
        return "save-result";
    }
}
