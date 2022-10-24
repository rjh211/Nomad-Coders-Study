package hello.login.web.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.UUID;

@Slf4j
public class LogFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("log filter init");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //사용자 요청 -> doFilter
        log.info("log filter doFilter");

        HttpServletRequest httpReuest = (HttpServletRequest) request; //자식클래스로 다운캐스팅
        String requestURI = httpReuest.getRequestURI();

        String uuid = UUID.randomUUID().toString();

        try{
            log.info("REQUEST [{}][{}]", uuid, requestURI);
            chain.doFilter(request, response); //다음 필터가 있다면 계속 진행, 없다면 servlet이 호출(해당 메서드를 넣지 않으면 try/finally만 호출하고 controller 호출이 안되서 먹통이됨)
        }catch (Exception e){
            throw e;
        }finally {
            log.info("RESPONSE [{}][{}]", uuid, requestURI);
        }


    }

    @Override
    public void destroy() {
        log.info("log filter destroy");
    }
}
