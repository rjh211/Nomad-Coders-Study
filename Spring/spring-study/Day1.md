1. 템플릿 엔진 : thymeleaf
2. 웹 : Spring Web

Intellij - 프로젝트 구조
1. .idea : Intellij가 사용하는 설정파일
2. gradle - wrapper : 그래이들 관련해서 그래으들쓰는 폴더
3. src
 - 최근 Maven 이든 Gradle이든 모두 main / test 폴더로 나누어져있음
 - main에는 하위에 java / resourse , test에는 java 폴더가존재함
 - 테스트 코드의 중요성 강조
 - resourse에는 java파일을 제외한 xml이나 설정파일들로 구성 (java 제외 파일들)
4. build.gradle
 - plugins : spring boot  생성시 추가한 모듈 & 버전
 - sourseCompatibility : 자바 버전
 - repositories : mavenCentral이라는 사이트에서 라이브러리를 받으라(필요할경우 특정 url 입력 가능)
 - dependencies : testImplementation -> 테스트 라이브러리 (자동추가 되어있음)
5. gitigore : 형상관리시 소스코드 관리 
6. gradlew, gradlew.bat : 그래이들 빌드시 사용
7. setting.gradle : 강의하며 설명

src/main/java/hello.springstudy/SpringStudyApplication.java
 - 기본 자바와 같이 public static void main 메서드를 사용하여 메인 메서드 실행
 - 메인메서드 실행시 SpringApplication의 run 메서드 (param : class type, String[])의 인자로 메인 메서드가 속한 HelloSpringApplication.class를 받아서 메인 메서드 실행
 - 메인메서드 실행시 내장된 톰캣 웹서버를 자체적으로 띄우면서 스프링부트가 실행 됨
 
Intellij를 사용할경우 gradle을 통해서 java파일이 실행되는 경우가 있는데, 해당 설정은 Ctrl + Alt + s 버튼으로 setting에 들어가서 gradle 검색후 'Build and run usings / Run test usings' 모두 Gradle -> Intellij 로 변경
 - gradle을 통하지 않고 intellij에서 바로 실행하기 때문에 약간 빨라짐


 @SpringBootApplication
  - 스프링 부트의 자동설정을 위한 어노테이션, 스프링 Bean읽기와 생성 모두 자동 설정(org.springframework.boot.autoconfigure 패키지에 위치)
  - 위 어노테이션이 있는 위치부터 설정을 읽어감(프로젝트 최상단)
  - SpringApplication.run으로 내장 WAS를 실행(언제 어디서나 같으 환경에서 스프링 부트 배포하기 때문에 Spring boot에서는 내장 WAS 사용 권고)
    => 톰캘 설치 필요없이 스프링부트로 만들어진 JAR파일로 실행