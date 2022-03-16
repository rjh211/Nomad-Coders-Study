package hello.springstudy;

import hello.springstudy.repository.MemberRepository;
import hello.springstudy.repository.MemoryMemberRepository;
import hello.springstudy.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    //스프링이 올라오며 Configuration 어노테이션을 읽고, @Bean어노테이션을 확인하여 스프링에 빈을 등록한다.
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());   //리포지토리와 의존관계 형성
    }

    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
