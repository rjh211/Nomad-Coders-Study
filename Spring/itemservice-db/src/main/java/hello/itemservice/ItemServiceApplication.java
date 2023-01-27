package hello.itemservice;

import hello.itemservice.config.*;
import hello.itemservice.repository.ItemRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;


//@Import(JdbcTemplateV2Config.class) //설정 java 파일 지정(JDBCTemplate Config)
//@Import(MemoryConfig.class) //설정 java 파일 지정
@Import(JdbcTemplateV3Config.class)//SimpleInsert
@SpringBootApplication(scanBasePackages = "hello.itemservice.web") //@ComponentScan범위 지정
public class ItemServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ItemServiceApplication.class, args);
	}

	//EventListener등록을 위한 Bean등록
	@Bean
	@Profile("local")	//local이라는 Profile이 사용되는 경우에만 해당 메서드가 빈으로 등록이 됨(Bean등록 조건)
	public TestDataInit testDataInit(ItemRepository itemRepository) {
		return new TestDataInit(itemRepository);
	}

}
