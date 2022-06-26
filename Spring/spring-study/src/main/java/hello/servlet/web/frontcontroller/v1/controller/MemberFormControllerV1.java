package hello.servlet.web.frontcontroller.v1.controller;

import hello.servlet.web.frontcontroller.v1.ControllerV1;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberFormControllerV1 implements ControllerV1 {
    @Override
    public void process(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException {
        String viewPath = "/WEB-INF/views/new-form.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(viewPath);//Controller => view 로 이동하기 위한 메서드(해당경로로 이동시켜줌)
        requestDispatcher.forward(request, response);//서버 내부에서 내부의 jsp or servlet를 다시 호출(redirect 처럼 client를 들러서 가지 않는다.)
    }
}
