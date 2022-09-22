package hello.itemservice;

import hello.itemservice.web.validation.itemValidator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.validation.Validator;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ItemServiceApplication /*implements WebMvcConfigurer */{

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}
/*
 * Global로 모든 Controller에 Validator적용하는 방법 (@InitBinder를 제거해도된다.)
	@Override
	public Validator getValidator() {
		return new itemValidator();
	}*/
}
