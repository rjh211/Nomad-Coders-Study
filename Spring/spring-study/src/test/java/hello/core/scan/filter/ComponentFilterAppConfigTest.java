package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class ComponentFilterAppConfigTest {
    @Test
    void filterScan(){
        //ComponentFilterAppConfig 설정정보에서 MyIncludeComponent Class의 객체들은 include하고, MyExcludeComponent Class의 객체들은 Exclude하기 때문에 beanA만 등록될 것임
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        assertThrows(   //NoSuchBeanDefinitionException이 나야 테스트 성공
                NoSuchBeanDefinitionException.class,
                ()->ac.getBean("beanB", BeanB.class));
    }

    @Configuration
    @ComponentScan(
            includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = MyIncludeComponent.class),
            excludeFilters = @ComponentScan.Filter(classes = MyExcludeComponent.class)//type의 Default는 Annotation이라 생략해도 무방함
    )
    static class ComponentFilterAppConfig{

    }
}
