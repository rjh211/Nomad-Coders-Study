package hello.springstudy;

import hello.springstudy.repository.*;
import hello.springstudy.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    private DataSource dataSource;
    private EntityManager em;

    @Autowired
    public SpringConfig(EntityManager em) {
        this.em = em;
    }


//    @Autowired
//    public SpringConfig(DataSource dataSource){
//        //@Configuration도 스프링Bean으로 관리하기 때문에 컨터네이너의 dataSource bean을 주입해준다.
//        this.dataSource = dataSource;
//    }

    //스프링이 올라오며 Configuration 어노테이션을 읽고, @Bean어노테이션을 확인하여 스프링에 빈을 등록한다.
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());   //리포지토리와 의존관계 형성
    }

    @Bean
    public MemberRepository memberRepository(){
        //return new MemoryMemberRepository();
        //return new JdbcMemberRepository(dataSource);
//        return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
