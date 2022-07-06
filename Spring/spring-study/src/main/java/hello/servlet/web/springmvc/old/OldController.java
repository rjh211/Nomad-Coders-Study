package hello.servlet.web.springmvc.old;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("/springmvc/old-controller")//스프링 빈의 이름등록
public class OldController implements Controller {//SpringMVC Annotation 이전에 사용하던 방법
    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("OldController.handleRequest");
        //application properties의 spring.mvc.view 항목으로 물리명 생성
        ModelAndView mv = new ModelAndView();
        mv.setViewName("WEB-INF/views/new-form.jsp");
        return mv;    //WEB-INF의 new-form 으로 전달(논리명 -> 믈리명)
    }
}
