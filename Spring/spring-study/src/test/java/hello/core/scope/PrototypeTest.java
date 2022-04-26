package hello.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class PrototypeTest {

    @Test
    void prototypeBeanFind(){
        //AnnotationConfigApplicationContext의 class 타입 객체는 @ComponentScan 어노테이션을 붙이지 않더라도 스캔 대상으로 지정이됨
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(PrototypeBean.class);

        //getBean을 호출하며 생성자 메서드가 호출됨을 확인
        System.out.println("Create ProtoType1");
        PrototypeBean prototypeBean = ac.getBean(PrototypeBean.class);
        System.out.println("Create ProtoType2");
        PrototypeBean prototypeBean2 = ac.getBean(PrototypeBean.class);

        //다른값 출력
        System.out.println("prototypeBean = " + prototypeBean);
        System.out.println("prototypeBean2 = " + prototypeBean2);
        Assertions.assertThat(prototypeBean).isNotSameAs(prototypeBean2);

        //PreDestroy 메서드 호출이 불가능함
        ac.close();

        //destroy를 위해서는 다음과같은 객체의 메서드를 직접 호출해야한다.
        //PreDestory 작동 확인
        prototypeBean.destroy();
        prototypeBean2.destroy();
    }

    @Scope("prototype")
    static class PrototypeBean{

        @PostConstruct
        public void init(){
            System.out.println("prototypeBean.init");
        }

        @PreDestroy
        public void destroy(){
            System.out.println("prototypeBean.destroy");
        }
    }
}
