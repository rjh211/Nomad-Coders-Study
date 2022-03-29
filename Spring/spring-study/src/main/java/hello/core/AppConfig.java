package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration은 스프링의 설정정보라는 뜻임(하위의 @Bean들을 스프링 컨테이너에 등록함)
@Configuration
//MemberServiceImpl의 생성자를 통해 어떤 구현객체를 주입할지는 AppConfig에서 결정이됨
//생성(Appconfig)과 실행(Service)을 분리하게 되었음
public class AppConfig {//각 역할이 잘 드러나도록 리펙토링(메서드명을 보고 직관적으로 확인할 수 있음)

    @Bean
    public MemberService memberService(){   //멤버 서비스의 역할
        return new MemberServiceImpl(getMemberRepository());
    }
    @Bean
    public MemberRepository getMemberRepository() {    //멤버 리파지토리의 역할
        return new MemoryMemberRepository();
    }
    @Bean
    public OrderService orderService(){ //오더 서비스의 역할
        return new OrderServiceImpl(getMemberRepository(), getPolicy());
    }
    @Bean
    public DiscountPolicy getPolicy() {
//        return new RateDiscountPolicy();
        return new FixDiscountPolicy();
    }
}
