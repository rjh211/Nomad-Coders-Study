package hello.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {
    @Test
    void statefuleServiceSingleton(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        statefulService statefulService1 = ac.getBean(statefulService.class);
        statefulService statefulService2 = ac.getBean(statefulService.class);

        //Thread1 A사용자 만원 주문
        statefulService1.order("userA", 10000);
        //Thread2 B사용자 2만원 주문
        statefulService2.order("userB", 20000);
        
        //ThreadA : 사용자A 주문금액 조회
        int price = statefulService1.getPrice();
        System.out.println("price = " + price);

        Assertions.assertThat(statefulService1.getPrice()).isEqualTo(20000);
    }

    static class TestConfig{

        @Bean
        public statefulService statefulService(){
            return new statefulService();
        }
    }
}
