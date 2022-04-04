package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력")
    void findAllBean() {    //jUnit 5 부터는 public을 생략해도됨
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) { //단축키 iter + tab
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("bean = " + beanDefinitionName + "Object = " + bean);//soutv => 변수명찍어주는 단축어

            //결과
//            bean = org.springframework.context.annotation.internalConfigurationAnnotationProcessorObject = org.springframework.context.annotation.ConfigurationClassPostProcessor@7ce97ee5            //스프링 내부적으로 자체 확장을 위해 등록하는 빈
//            bean = org.springframework.context.annotation.internalAutowiredAnnotationProcessorObject = org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor@32c8e539         //스프링 내부적으로 자체 확장을 위해 등록하는 빈
//            bean = org.springframework.context.annotation.internalCommonAnnotationProcessorObject = org.springframework.context.annotation.CommonAnnotationBeanPostProcessor@73dce0e6         //스프링 내부적으로 자체 확장을 위해 등록하는 빈
//            bean = org.springframework.context.annotation.internalPersistenceAnnotationProcessorObject = org.springframework.orm.jpa.support.PersistenceAnnotationBeanPostProcessor@5a85c92           //스프링 내부적으로 자체 확장을 위해 등록하는 빈
//            bean = org.springframework.context.event.internalEventListenerProcessorObject = org.springframework.context.event.EventListenerMethodProcessor@32811494           //스프링 내부적으로 자체 확장을 위해 등록하는 빈
//            bean = org.springframework.context.event.internalEventListenerFactoryObject = org.springframework.context.event.DefaultEventListenerFactory@4795ded0          //스프링 내부적으로 자체 확장을 위해 등록하는 빈
//            bean = appConfigObject = hello.core.AppConfig$$EnhancerBySpringCGLIB$$b1356f23@67c2e933           //Appconfig 역시 빈으로 등록이됨
//            bean = memberServiceObject = hello.core.member.MemberServiceImpl@260e86a1                     //직접 등록한 빈
//            bean = getMemberRepositoryObject = hello.core.member.MemoryMemberRepository@4fce136b                      //직접 등록한 빈
//            bean = orderServiceObject = hello.core.order.OrderServiceImpl@70ab2d48                        //직접 등록한 빈
//            bean = getPolicyObject = hello.core.discount.FixDiscountPolicy@3300f4fd                       //직접 등록한 빈

        }
    }


    @Test
    @DisplayName("모든 애플리케이션 빈 출력")
    void findApplicationBean() {    //jUnit 5 부터는 public을 생략해도됨
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) { //단축키 iter + tab
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);//bean에 대한 메타데이터 정보 ; 변수 명 자동완성 ctrl+ alt + v

            if(beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION){
                //개발자가 app개발을 위해 등록한 bean이나 외부 라이브러리들
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("bean = " + beanDefinitionName + "Object = " + bean);//soutv => 변수명찍어주는 단축어
            }
        }
    }
}