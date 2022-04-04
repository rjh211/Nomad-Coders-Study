등록된 빈 확인
 - test코드로 진행

#bean의 역할
Role Application
  - 개발자가 app개발을 위해 등록한 bean이나 외부 라이브러리들
Role Infrastructure
  - 스프링 내부에서 사용하는 빈


Spring bean 조회방법
1. getbean(beanName, type)
2. getbean(type)
3. 조회 대상이 없는경우 NoSuchBeanDefinitionException 발생
4. 