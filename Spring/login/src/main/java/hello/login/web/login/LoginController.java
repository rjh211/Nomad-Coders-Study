package hello.login.web.login;

import hello.login.domain.login.LoginService;
import hello.login.web.member.Member;
import hello.login.web.session.SessionConst;
import hello.login.web.session.SessionManager;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.*;
import javax.validation.Valid;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LoginController {
    private final LoginService loginService;
    private final SessionManager sessionManager;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form){
        return "login/loginForm";
    }

//    @PostMapping("/login")
    public String login(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if(loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");//글로벌 오류 발생
            return "login/loginForm";
        }

        //성공 처리(쿠키 생성후 브라우저에 전송)
        Cookie idCookie = new Cookie("memberId", String.valueOf(loginMember.getId()));
        response.addCookie(idCookie);

        return "redirect:/";
    }
//    @PostMapping("/login")
    public String loginV2(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletResponse response){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if(loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");//글로벌 오류 발생
            return "login/loginForm";
        }

        //session Manager을 통해 세션생성 및 회원 데이터 보관
        //성공 처리(쿠키 생성후 브라우저에 전송)
        sessionManager.createSession(loginMember, response);


        return "redirect:/";
    }
//    @PostMapping("/login")
    public String loginV3(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if(loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");//글로벌 오류 발생
            return "login/loginForm";
        }

        //session Manager을 통해 세션생성 및 회원 데이터 보관
        //성공 처리(쿠키 생성후 브라우저에 전송)
        //세션이 없다면 신규로 생성해서 반환까지 해줌
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);

        return "redirect:/";
    }
    @PostMapping("/login")
    public String loginV4(@Valid @ModelAttribute LoginForm form, BindingResult bindingResult, @RequestParam(defaultValue = "/") String redirectURL, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "login/loginForm";
        }
        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if(loginMember == null){
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");//글로벌 오류 발생
            return "login/loginForm";
        }

        //session Manager을 통해 세션생성 및 회원 데이터 보관
        //성공 처리(쿠키 생성후 브라우저에 전송)
        //세션이 없다면 신규로 생성해서 반환까지 해줌
        HttpSession session = request.getSession();
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);
        log.info("requestURL = {}", redirectURL);
        return "redirect:" + redirectURL;
    }

//    @PostMapping("/logout")
public String logout(HttpServletResponse response){
    //쿠키를 사용하는 것은 임의로 변경이 가능하기 때문에 보안상 큰위험이 따른다.(네트워크 전송마다 Client -> Server용 이기때문, 쿠키에 보관된 정보는 훔쳐갈수가 있다.)
    //서버에서는 일정시간 주기적으로 토큰을 강제로 제거해야함
    expireCookie(response, "memberId");
    return "redirect:/";
}
//    @PostMapping("/logout")
    public String logoutV2(HttpServletRequest request){
        sessionManager.expire(request);
        return "redirect:/";
    }
    @PostMapping("/logout")
    public String logoutV3(HttpServletRequest request){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();//세션데이터를 날림
        }
        return "redirect:/";
    }

    private void expireCookie(HttpServletResponse response, String cookieName){
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setMaxAge(0);
        response.addCookie(cookie);
    }
}
