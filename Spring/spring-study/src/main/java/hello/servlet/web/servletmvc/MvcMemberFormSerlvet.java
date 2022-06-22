package hello.servlet.web.servletmvc;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "mvcMemberFormSerlvet", urlPatterns = "/servlet-mvc/members/new-form")
public class MvcMemberFormSerlvet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);//Controller => view 로 이동하기 위한 메서드(해당경로로 이동시켜줌)
        requestDispatcher.forward(request, response);//서버 내부에서 내부의 jsp or servlet를 다시 호출(redirect 처럼 client를 들러서 가지 않는다.)
    }
}
