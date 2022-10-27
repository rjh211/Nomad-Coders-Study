package hello.login.web.filter;

import hello.login.web.session.SessionConst;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.PatternMatchUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Slf4j
public class LoginCheckFilter implements Filter {

    private static final String[] whitelist = {"/", "/members/add", "/login", "/logout", "/css/*"};//로그인 안된 사용자들에게도 오픈해줄 whiteList URL작성

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        //init과 destroy는 상위객체(Filter)에서 default로 구현되었기 때문에 굳이 재정의를 하지 않아도 된다.
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String requestURI = httpRequest.getRequestURI();

        HttpServletResponse httpResponse = (HttpServletResponse) response;

        try{
            log.info("인증 체크 필터 시작 = {}", requestURI);
            if(isLoginCheckPath(requestURI)){
                log.info("인증 체크 로직 실행 {}", requestURI);
                HttpSession session = httpRequest.getSession(false);
                if(session == null || session.getAttribute(SessionConst.LOGIN_MEMBER) == null){
                    log.info("미인증 사용자 요청 {}", requestURI);

                    //login으로 redirect
                    httpResponse.sendRedirect("/login?redirectURL=" + requestURI);
                    return; //이후 서블릿 호출을 막음
                }
            }
            chain.doFilter(request, response); //다른 request, response객체를 삽입할 수 있다..
        } catch (Exception e){
            throw e; //에러는 WAS단에서 처리하도록 throw해줌, 여기서 처리를 하면 에러를 먹어버려서 에러처리 기능을 해당 클래스에서 구현해야함
        } finally {
            log.info("인증 체크 필터 종료 {}", requestURI);
        }
    }

    private boolean isLoginCheckPath(String requestURI){
        return !PatternMatchUtils.simpleMatch(whitelist, requestURI); //whiteList에 requestURI가 포함되어있는지 체크
    }
}
