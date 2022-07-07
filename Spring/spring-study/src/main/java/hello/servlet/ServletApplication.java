package hello.servlet;

import hello.servlet.web.springmvc.v1.SpringMemberFormControllerv1;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

@ServletComponentScan//서블릿이 하위의 패키지를 모두 찾아서 자동으로 등록을해준다.
@SpringBootApplication
public class ServletApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServletApplication.class, args);
    }
}
