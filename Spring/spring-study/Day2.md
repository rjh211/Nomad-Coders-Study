현재 가지고 있는 라이브러리 : 타임리프(HTML만들어주는 템플릿 엔진), 웹, 테스트 + External Library(톰캣, junit, spring-boot관련 ...)

- maven, gradle과 같은 빌드 툴들은 의존관계를 관리해줌
 => spring boot starter web 라이브러리를 땡겨오면 톰캣, 스플이 mvc등 의존관계를 갖고있는 라이브러리를 자동으로 땡겨옴(Dependancies에서 확인 가능)

- spring boot starter web
 - 톰캣(웹서버),  webmvc등 제공

- spring boot starter(공통)
 - spring boot
   - spring-core
 - logging
  - spring boot starter logging 내의 log4j, slf4j, logback이 존재함(의존성을 가지고 땡겨옴)

- test lib
 - java에서는 보통 junit을 사용함 (assertj, mocito등도 있음)

기본 View 생성
 1. WelCome Page (WAS 실행시 resourse/static/index.html을 찾아서 기본페이지로 설정함)
  - spring.io 참고
 2. Controller.java 생성 후 @Controller 어노테이션을 붙여줘야함
 3. Controller에서 리턴값으로 String을 반환하면 View Resolver가 화면을 찾아서 처리함
  - spring boot template engine 기본 View Name 매핑
    => 'resource:template/' + {ViewName} + '.html'

프로젝트 빌드
1. root 폴더에서 './gradlew build' 입력
2. 하위의 build - libs 폴더로 이동  (jar파일이 떨어지는 경로)
3. jar 실행 : java -jar spring-study-0.0.1-SNAPSHOT.jar
주의 사항 : local의 Web Server를 띄운상태라면 8080port를 두군데서 사용할 수 없기 때문에 둘중 하나만 사용 or port 변경 후 사용하기
 - ./gradlew clean -> build 폴더를 삭제시킴
 - ./gradlew clean build -> build 폴더 삭제 후 build