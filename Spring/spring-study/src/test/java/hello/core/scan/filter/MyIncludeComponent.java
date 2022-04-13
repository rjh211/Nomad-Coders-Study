package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE) //클래스레벨에 붙는 어노테이션임을 알림
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyIncludeComponent {


}
