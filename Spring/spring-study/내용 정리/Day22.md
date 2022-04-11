@Configration & Singleton
- @Configration은 싱글톤을위해 존재한다고보면됨.

@Configuration과 바이트 코드 조작
 - 스프링 컨테이너는 싱글톤 레지스트리이다. 따라서 스프링빈이 싱글톤이 되도록 보장해주어야한다.
 - 순수한 클래스의 getClass()를 출력해보면 `hello.core.AppConfig`와 같이 나와야 하지만 스프링 컨테이너의 객체를 getClass 해보면 `hello.core.AppConfig.$$EnhancerBySpringCGLIB$$100c220d`와 같은 형태로 출력된다.
 - 사용자가 만든 클래스가 아닌 스프링이 자체적으로 CGLIB라는 바이트코드 조작 라이브러리를 사용하여 클래스를 상속받아서 다른임의의 클래스를 만든다. 그이후 임의의 클래스를 스프링 빈으로 등록함

@Configuration 없이 @Bean 등록
 - 해당 메서드가 @Bean으로 등록되긴함.
 - 그러나 컨테니어의 CGLIB가 작동하지 않으면서 싱글톤을 보장하지 않고, 사용자가 정의한 코드대로 실행을 하게된다.(SpringContainer의 관리를 받지 못한다.)
 - 테스트 실행결과 모든 memberRepository의 객체가 다름을 알 수 있다.

@Autowired
 - 등록된 Bean을 해당 객체에 자동으로 의존관계 주입을함