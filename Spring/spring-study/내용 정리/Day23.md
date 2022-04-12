컴포넌트 스캔과 의존관계 자동주입
 - 기존방식은 AppConfig나 xml에 @Bean으로 설정정보에 빈정보를 나열했음
 - 실무의 수백개 빈을 등록하기는 너무 귀찮음
 - 스프링은 설정정보가 없어도 자동으로 스프링 빈을 등록할 수 있도록 컴포넌트 스캔이라는 기능을 제공함.
 - 의존관계 자동주입을 위한 @Autowired도 제공
* @ComponentScan -> @Component가 붙은 클래스를 찾아 자동으로 스프링 빈으로 등록함
 - @Component를 사용한 클래스들의 의존관계 주입은 사용자가 수동으로 조작을 할 수 없기 때문에 @Autowired(자동 의존관계 주입)를 사용한다.

//실행결과
//ClassPathBeanDefinitionScanner가 컴포넌트 스캔 후보를 식별함
context.annotation.ClassPathBeanDefinitionScanner - Identified candidate component class: file [C:\Users\rjh21\OneDrive\바탕 화면\Study\Spring\spring-study\out\production\classes\hello\core\discount\RateDiscountPolicy.class]
context.annotation.ClassPathBeanDefinitionScanner - Identified candidate component class: file [C:\Users\rjh21\OneDrive\바탕 화면\Study\Spring\spring-study\out\production\classes\hello\core\member\MemberServiceImpl.class]
context.annotation.ClassPathBeanDefinitionScanner - Identified candidate component class: file [C:\Users\rjh21\OneDrive\바탕 화면\Study\Spring\spring-study\out\production\classes\hello\core\member\MemoryMemberRepository.class]
context.annotation.ClassPathBeanDefinitionScanner - Identified candidate component class: file [C:\Users\rjh21\OneDrive\바탕 화면\Study\Spring\spring-study\out\production\classes\hello\core\order\OrderServiceImpl.class]

//singletonbean으로 생성됨
Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerProcessor'
Creating shared instance of singleton bean 'org.springframework.context.event.internalEventListenerFactory'
Creating shared instance of singleton bean 'org.springframework.context.annotation.internalAutowiredAnnotationProcessor'
Creating shared instance of singleton bean 'org.springframework.context.annotation.internalCommonAnnotationProcessor'
Creating shared instance of singleton bean 'org.springframework.context.annotation.internalPersistenceAnnotationProcessor'
Creating shared instance of singleton bean 'autoAppConfig'
Creating shared instance of singleton bean 'rateDiscountPolicy'
Creating shared instance of singleton bean 'memberServiceImpl'
Creating shared instance of singleton bean 'memoryMemberRepository'

//Autowired정보
Autowiring by type from bean name 'memberServiceImpl' via constructor to bean named 'memoryMemberRepository'
Creating shared instance of singleton bean 'orderServiceImpl'
Autowiring by type from bean name 'orderServiceImpl' via constructor to bean named 'memoryMemberRepository'
Autowiring by type from bean name 'orderServiceImpl