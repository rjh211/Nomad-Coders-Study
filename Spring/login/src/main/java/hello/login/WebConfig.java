package hello.login;

import hello.login.web.filter.LogFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;

@Configuration
public class WebConfig {
//Filter 등록 Config 파일

//@WebFilter Annotation으로 필터등록을 해도 되지만, 순서를 지정할수 없기 때문에 WebConfig를 사용하는 방식이 더 낫다.
    @Bean
    public FilterRegistrationBean logFilter(){
        //Spring BOOT가 WAS를 띄울때 필터를 넣어줌
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());  //내가 만든 필터 등록
        filterRegistrationBean.setOrder(1); //필터 순서
        filterRegistrationBean.addUrlPatterns("/*"); //모든 url에 적용

        return filterRegistrationBean;

    }
}
