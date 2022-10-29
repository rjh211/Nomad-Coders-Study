package hello.login;

import hello.login.web.filter.LogFilter;
import hello.login.web.filter.LoginCheckFilter;
import hello.login.web.iterceptor.LoginCheckInterceptor;
import hello.login.web.iterceptor.LoginInterceptor;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.Filter;
import javax.servlet.FilterRegistration;

@Configuration
public class WebConfig implements WebMvcConfigurer {
//Filter 등록 Config 파일

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).order(1).addPathPatterns("/**").excludePathPatterns("/css/**", "/*.ico", "/error");//Path Pattern이 서블릿과 다름, **을 붙여야함
        registry.addInterceptor(new LoginCheckInterceptor()).order(2).addPathPatterns("/**").excludePathPatterns("/", "/members/add", "/login", "/logout", "/css/**", "/*.ico", "/error");
    }

    //@WebFilter Annotation으로 필터등록을 해도 되지만, 순서를 지정할수 없기 때문에 WebConfig를 사용하는 방식이 더 낫다.
//    @Bean log를 Interceptor로 쓰기위해 빈 제거
    public FilterRegistrationBean logFilter(){
        //Spring BOOT가 WAS를 띄울때 필터를 넣어줌
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LogFilter());  //내가 만든 필터 등록
        filterRegistrationBean.setOrder(1); //필터 순서
        filterRegistrationBean.addUrlPatterns("/*"); //모든 url에 적용

        return filterRegistrationBean;

    }

    @Bean
    public FilterRegistrationBean loginCheckFilter(){
        //Spring BOOT가 WAS를 띄울때 필터를 넣어줌
        FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(new LoginCheckFilter());  //내가 만든 필터 등록
        filterRegistrationBean.setOrder(2); //필터 순서
        filterRegistrationBean.addUrlPatterns("/*"); //모든 url에 적용

        return filterRegistrationBean;

    }
}
