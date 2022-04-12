package hello.core;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

@Configuration
@ComponentScan(excludeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = Configuration.class)) //Configuration Annotation이 붙은 빈을 제외하고 모두 수집함(기존 AppConfig와의 충돌 방지)
public class AutoAppConfig {

}
