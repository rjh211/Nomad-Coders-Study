package hello.servlet.basic.response;

import com.fasterxml.jackson.databind.ObjectMapper;
import hello.servlet.basic.HelloData;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

@WebServlet(name="responseJsonServlet", urlPatterns = "/response-json")
public class ResponseJsonServlet extends HttpServlet {
    private ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
//        response.setCharacterEncoding("utf-8");//application/json 형식에는 utf-8이 고정으로 등록이되어있어서, 따로 추가할 필요가 없다.(추가시 의미없는데이터가 추가된것뿐임)

        HelloData helloData = new HelloData();
        helloData.setUsername("rjh211");
        helloData.setAge(30);

        String string = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(helloData);
        response.getWriter().write(string);
    }
}
