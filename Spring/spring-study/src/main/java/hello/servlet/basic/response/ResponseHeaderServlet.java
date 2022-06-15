package hello.servlet.basic.response;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "responseHeaderServlet", urlPatterns = "/response-header")
public class ResponseHeaderServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //[Status Line]
        response.setStatus(HttpServletResponse.SC_OK);//HTTP Status입력(200)

        //[Response Header]
        response.setHeader("Content-Type", "text/plain");
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");//캐시무효화
        response.setHeader("Pragma", "no-cache");
        response.setHeader("my-header", "hello");

        //[Header의 편의 메서드]
        setContent(response);
        setCookie(response);

        //[message Body]
        PrintWriter writer = response.getWriter();
        writer.println("OK");
    }

    private void setContent(HttpServletResponse response){
        //response.setHeader("Content-Type", "text/plain;charset=utf-8"); 과 동일
        response.setContentType("text/plain");
        response.setCharacterEncoding("utf-8");
    }

    private void setCookie(HttpServletResponse response){
        //response.setHeader("Set-Cookie", "myCookie=;good Max-Age=600;"); 과 동일일
       Cookie cookie = new Cookie("myCookie", "good");
        cookie.setMaxAge(600);//600초 설정
        response.addCookie(cookie);
    }

    private void setRedirect(HttpServletResponse response) throws IOException {
        //response.setStatus(HttpServletResponse.SC_FOUND); //302
        //response.setHeader("Location", "/basic/hello-form.html"); 과 동일
        response.sendRedirect("/basic/hello-form.html");
    }
}
