package hello.core.beanfind;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ApplicationContextBasicFindTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("빈이름으로 조회")
    void findBeanByName() {    //jUnit 5 부터는 public을 생략해도됨
        MemberService memberService = ac.getBean("memberService", MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass = " + memberService.getClass());

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        //memberService가 memberServiceImpl과 같다면 correct
    }
    @Test
    @DisplayName("빈타입으로 조회")
    void findBeanByType() {    //jUnit 5 부터는 public을 생략해도됨
        MemberService memberService = ac.getBean(MemberService.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass = " + memberService.getClass());

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        //Interface로 조회시 Interface의 Instance가 대상이됨
    }

    @Test
    @DisplayName("Instance Type으로 조회")
    void findBeanByInstanceType() {    //jUnit 5 부터는 public을 생략해도됨
        MemberService memberService = ac.getBean(MemberServiceImpl.class);
        System.out.println("memberService = " + memberService);
        System.out.println("memberService.getClass = " + memberService.getClass());

        assertThat(memberService).isInstanceOf(MemberServiceImpl.class);
        //Interface로 조회시 Interface의 Instance가 대상이됨
    }

    @Test
    @DisplayName("빈 이름으로 조회 실패")
    void findBeanByNameX() {    //jUnit 5 부터는 public을 생략해도됨
//        ac.getBean("xxxx",MemberService.class);
        //NoSuchBeanDefinitionException 발생
        assertThrows(NoSuchBeanDefinitionException.class,()
                -> ac.getBean("xxxx", MemberService.class));//반드시 해당 예외가 터져야 테스트가 성공
    }
}
