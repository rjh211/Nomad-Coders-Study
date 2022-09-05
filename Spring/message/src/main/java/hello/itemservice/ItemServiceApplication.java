package hello.itemservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;

@SpringBootApplication
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

//	@Bean
//	public MessageSource messageSource(){ //Spring에서 디폴트로 지원하는 메세지 설정(자동으로 빈으로 등록해줌)
//		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
//		messageSource.setBasenames("message", "errors");
//		messageSource.setDefaultEncoding("utf-8");
//		return messageSource;
//	}
}
