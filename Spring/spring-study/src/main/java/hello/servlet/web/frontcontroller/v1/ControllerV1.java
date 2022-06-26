package hello.servlet.web.frontcontroller.v1;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface ControllerV1 {
    //프론트 컨트롤러는 해당 인터페이스를 호출해서 구현과 관계없이 로직의 일관성을 가져갈 수 있다.
    void process(HttpServletResponse response, HttpServletRequest request) throws ServletException, IOException;
}
