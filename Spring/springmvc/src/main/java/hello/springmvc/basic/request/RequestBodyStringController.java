package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.Writer;
import java.nio.charset.StandardCharsets;

@Slf4j
@Controller
public class RequestBodyStringController {

    @PostMapping("/reqeust-body-string-v1")
    public void requestBodyString(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletInputStream inputStream = request.getInputStream();
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        response.getWriter().write("ok");
    }

    @PostMapping("/reqeust-body-string-v2")
    public void requestBodyStringv2(InputStream inputStream, Writer responseWriter) throws IOException { //HttpServlet* 대신 사용가능
        String messageBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8);

        log.info("messageBody={}", messageBody);
        responseWriter.write("ok");
    }

    @PostMapping("/reqeust-body-string-v3")
    public HttpEntity<String> requestBodyStringv3(HttpEntity<String> httpEntity) throws IOException { //HttpMessageConverter 사용

        String messageBody = httpEntity.getBody();//HTtpContents의 body를 꺼내온다.
        log.info("messageBody={}", messageBody);
        return new HttpEntity<>("ok"); //httpentity를 반환하여 body 전달
    }

    @PostMapping("/reqeust-body-string-v4")
    public HttpEntity<String> requestBodyStringv4(@RequestBody String messageBody) throws IOException { //response의 body를 인자로 불러온다, 헤더정보가 필요하다면 HttpEntity를 상요하거나 @RequestHeader인자를 하나 추가한다.

        log.info("messageBody={}", messageBody);
        return new HttpEntity<>("ok"); //httpentity를 반환하여 body 전달
    }
}
