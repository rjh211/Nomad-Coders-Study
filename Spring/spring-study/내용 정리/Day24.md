Component Scan 탐색 시작위치 설정
 - basePackage = {xxx, yyy}
 - 모든 자바코드 및 라이브러리까지 다 뒤지는 것을 방지
 - 원하는 패키지의 컴포넌트만 스캔
 - basePackageClasses = AutoAppConfig.class => AutoAppConfig가 속한 패키지 하위 컴포넌트만 스캔
 - Default는 해당 클래스가 속한 패키지 내부 모두 Search
 - 따라서 권장 방법은 Config 파일을 패키지 최상단에 위치하여 하위 모든 컴포넌트를 스캔하게 하는 것이다.
 - SpringBootApplication내부에 ComponentScan이 붙어있음 -> @ComponentScan을 안써도 @Component들이 모두 수집된다.

ComponentScan 수집대상
 - @Component, @Controller, @Service, @Repository, @Configuration
 
IncludeFilter and ExcludeFilter
 - IncludeFilter : 포함시킬 객체
 - ExcludeFilter : 제외할 객체

중복등록과 충돌
1. 자동으로 등록된 빈들의 이름이 같아서 충돌이 나는경우
 - `ConflactingBeanDefinitionException`발생
2. 자동으로 등록된 빈과 수동으로 등록된 빈이 충돌을 일으키는 경우
 - 수동으로 등록된 빈이 우선순위를 가짐(Overriding됨)
 - 이러한 경우 서로 다른 담당자들끼리 Config가 꼬이는 버그가 상당수 발생하기 때문에 최근 스프링버전에는 그냥 에러를 출력하도록 변경됨(application.properties에서 spring.main.allow-bean-definition-overriding = false로 변경)
3. 