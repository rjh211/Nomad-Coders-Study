package hello.core.autoWired;

import hello.core.AppConfig;
import hello.core.member.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.lang.Nullable;

import java.util.Optional;

public class AutowiredTest {
    @Test
    void AutowiredOption(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    static class TestBean{
        @Autowired(required = false)    //default는 true
        public void setNoBean1(Member member){ //스프링 컨테이너와 상관없는 인자
            System.out.println("Never Called = " + member);
        }

        @Autowired
        public void setNoBean2(@Nullable Member member){ //스프링 컨테이너와 상관없는 인자
            System.out.println("NULL = " + member);
        }

        @Autowired
        public void setNoBean3(Optional<Member> member){ //스프링 컨테이너와 상관없는 인자
            System.out.println("Optional = " + member);
        }

    }
}
