HTTP
 - 서버와 클라이언트는 모두 HTTP 프로토콜을 기반으로 전송을 한다.
웹 서버
 - HTTP를 기반으로 동작하는 서버
 - 정적리소스 제공 및 기타 부가기능 수행
 - Ex. NGNX, APACHE
웹 어플리케이션 서버 (WAS)
 - HTTP 기반으로 동작
 - 웹서버 기능 포함
 - 프로그램 코드를 실행하여 애플리케이션 실행 가능
 - Ex. 동적 HTML , HTTP API등 실행, 스프링 MVC등 실행
 - EX. Tomcat, Jetty...

WAS vs webserver
 - WAS는 애플리케이션 코드를 실행하는데 특화가 되어있다.

단일 WAS
 - 너무 많은 역할들을 담당하게 되므로 과부하가 걸리게됨

웹 시스템 구성
 - Client > Web Server > WAS > DB
 - WAS는 애플리케이션 로직에 관한 항목만 처리를 할 수있고, Web Server는 정적인 데이터만 관리하여 업무를 분산할 수있다.
 - 효율적인 리소스 관리도 가능하다.
   - 정적인 리소스 많이 사용되는경우 Web Server 증설, Application 리소스가 많이 사용되는경우 WAS증설..
 - Web Server에 비해 WAS와 DB가 더 부하가 많기 떄문에 장애가 더 많이일어난다. 이럴경우에 Web Server에서 WAS or DB의 장애 여부를 Client에게 전달가능

서블릿
 - 의미있는 비지니스 로직만 사용자가 개발을 하고, HTTP 전처리/후처리를 대신 수행해줌.
 - 사용법 : HttpServlet 상속후 service만 오버라이딩 하면됨
 - Parameter : HttpserverletRequset / Response (전처리 후처리 담당 객체)
실행 순서
 1. client에서 요청이 들어옴
 2. 요청을 바탕으로 request / response 객체를 생성
 3. 객체를 서블릿컨테이너에 전달 후 서블릿 객체 호출
 4. 응답메시지를 설정하여 웹브라우저에 전달

서블릿 컨테이너
 - 서블릿 사용시 서블릿을 사용자가 직접 생성하지 않고, 상속만 받으면됨(서블릿 객체를 자동으로 생성 및 호출)
 - WAS On/Off시 서블릿의 생명주기도 관리함
 - 톰캣과 같이 `서블릿을 지원하는 WAS`를 서블릿 컨테이너라고 한다.
 - 서블릿 객체는 싱글톤으로 관리함
 - JSP도 서블릿으로 변환되어서 하용한다.
 - 멀티 쓰레드 처리 지원(수많은 클라이언트의 요청 처리)

멀티 스레드
 - 클라이언트 요청시 서버에서는 TCP/IP 커넥션 연결이 되고, 서블릿을 호출해줌(호출하는 주체는 쓰레드임)
 - 요청이 올때마다 스레드를 생성하고 종료시 소멸시킴
 - 스레드 풀을 생성시켜두고, 스레드풀로 스레드를 관리한다.(스레드풀 이상의 요청이오면 해당 클라이언트는 대기를 해야한다.)

스레드 풀의 적정숫자?
 - 로직의 복잡도, CPU, 메모리, IO리소스 상황에 ㄷ라 모두 다르
 - 성능 테스트가 필요함(아파치 ab / nGrinder등 툴사용)

HTTP API 
 - 응답결과로 HTML을 전달하는 것이 아닌 데이터를 전달함(주로 JSON사용)

SSR(server side rendering)
 - 서버에서 최종 HTML을 생성해서 클라이언트에게 전달

CSR(Client side rendering)
 - HTML 결과를 자바스크립트를 이용하여 브라우저에서 동적으로 생성

자바 백엔드 웹기술 역사

start.spring.io 프로젝트 생성시 주의사항
 - War버전을 선택해야 JSP를 사용할 수 있다.

HTTP 서블릿 Request
 - HTTP 요청을 개발자가 직접 파싱하지 않고, 서블릿이 대신 파싱을 해준뒤 HttpServletRequest 객체에 담아서 객체를 제공을한다.

HTTP Requset
1. Start Line
 - HTTP 메소드
 - URL
 - 쿼리스트링
 - 스키마, 프로토콜
2. 헤더
 - 헤더 조회
3. 바디
 - form 파라미터 형식 조회
 - message body 데이터 직접 조회

 - 따라서 서블릿 request를 자세히 사용하기 위해서는 HTTP 스펙에 대한 이해도가 필요하다.

HTTP 요청 방식
1. Get - 쿼리 파라미터
2. Post - HTML Form
 - 특징으로 `application/x-www-form-urlencoded` 컨텐츠 타입으로 요청을 보내게된다.
3. Http message body - HTTP API 이용

About Backend
백엔드 - 서버 사이드 렌더링 기술
 - JSP, 타임리프
 - 화면이 정적이고, 복잡하지 않을 때 사용
 - 백엔드 개발자는 서버 사이드 렌더링 기술 학습 필수
프론트엔드 - 클라이언트 사이드 렌더링 기술
 - React , Vuejs
 - 복잡하고 동적인 UI사용
 - 웹 프론트 엔드 개발자의 전문분야

선택과 집중
 - 백엔드 개발자의 웹프로트엔드 기술 학습은 옵션
 - 백엔드 개발자는 서버, DB 인프라 등등 수마 많은 백엔드 기술을 공부해야함.
 
JSP의단점
 - 서블릿 / JSP만으로 비지니스 로직과 뷰 렌더링 까지 모두 처리하게 된다면, 너무많은 역할을 하게 되고, 결과적으로 유지보수가 어려워짐
 - JSP와 같은 뷰 템플릿은 화면 렌더링에 최적화 되어있기 때문에, 해당 업무만을 담당하는 것이 가장 효율적이다.

MVC 컨트롤러
 - 비지니스 / 뷰를 컨트롤러와 뷰로 나누어 역할을 맡김
 - 비지니스 로직과 뷰 로직 모두 모델의 데이터를 참조하여 로직을 구성하면된다.
   - 비지니스 : 데이터 전달
   - 뷰 : 데이터 참조
 - 모델은 HttpServletRequest 객체의 내부에 저장소를 사용한다.
 - WEB-INF 하위의 자원들은 url Path로 직접 호출할 수 없다.(항상 컨트롤러를 거쳐 jsp를 호출해야한다.)

한계
1. viewPath 
 - 경로및 파일명이 바뀔경우 모두 찾아가서 바꾸어줘야한다.
2. 포워드가 중복된다.
 - Dispatcher 반복 호출
3. HttpServletRequest, Response
 - 위 객체를 테스트에서 사용할수 없게된다.
 - 테스트가 어려워짐
4. 공통처리가 어렵다.ㅍ

해결 방안
 - 프론트 컨트롤러 적용

프론트 컨트롤러 패턴
 - 기존의 공통로직들을 Front Controller에 모아놓고 관리를 함(서블릿 하나에 공통 로직을 모아둠, 해당 컨트롤러가 모든 유저의 요청을 모두 받아냄)
 - 프론트 컨트롤러가 나머지 컨트롤러를 연결해줌(굳이 jsp를 쓰지않아도됨)
 - Dispatcher Servlet 방식으로 생성됨

어댑터 패턴
 - 한번에 한가지방식의 컨트롤러 인터페이스만 사용이 가능한 한계를 극복(다양한 인터페이스를 호환하게함)
 - 어댑터가 컨트롤러를 대신 호출함

MVC 프레임워크 만들기 내용정리
v1. Front Controller 도입
 - 기본적인 구조를 유지하며 프론트 컨트롤러 도입
v2. View 분리
 - 반복되는 View 로직 분리
v3. Model 추가
 - 서블릿 종속제거
 - 뷰 이름 중복 제거(View Resolver)
v4. 단순하고 실용적인 컨트롤러
 - ModelView를 직접 사용하지 않아도됨
v5. 유연한 컨트롤러
 - 어댑터 도입

 - 스프링 MVC
   - 스프링 MVC의 핵심 구조를 파악하기 위해 필요한 부분을 모두 직접 구현함
   - 위 v1 ~ v5까지에 대한 사항들은 모두 Spring MVC에 적용이 되어있다.(@RequestMapping)
   - Annotation 형태를 추가하려면 새로운 컨트롤러를 추가하여 어댑터에 등록만 하면됨(확장성)

스프링 MVC vs 직접만든 프레임워크
1. frontController -> DispatcherServlet
2. handlerMappingMap -> HandlerMapping
3. MyHandlerAdapter -> HandlerAdapter
4. ModelView -> ModelAndView
5. viewResolver -> ViewResolver
6. MYView -> view

동작 수행
1. 핸들러 조회 : 핸들러 매핑을 통해 요청 URL에 매핑된 핸들러를 조회
2. 핸들러 어뎁터조회 : 핸들러를 실행할 수 있는 핸들러 어댑터를 조회한다.
3. 핸들러 어댑터 실행 : 핸들러 어댑터를 실행한다.
4. 핸들러 실행 : 핸들러 어댑터가 실제 핸들러를 실행한다.
5. ModelAndView 반환 : 핸들러 어댑터는 핸들러가 반환하는 정보를 ModelAndView로 변환해서 반환한다.
6. viewResolver 호출 : 뷰리졸버를 찾고 실행한한다.
7. View 반환 : 뷰 리졸버는 뷰의 논리명을 물리명으로 바꾸고 랜더링 역할을 담당하는 뷰 객체를 반환한다.
8. 뷰렌더링 : 뷰를 렌더링한다.

핸들러 매핑
 - 핸들러매핑에서 해당 컨트롤러를 찾는다.
1. Annotation 기반의 컨트롤러인 @RequestMapping 에서 RequestMappingHandlerMapping 사용
2. @RequestMapping이 없다면 BeanNameUrlHandlerMapping에서 스프링 빈의 이름으로 핸들러를 찾는다.


핸들어 어댑터
 - 핸들러 매핑을통해 찾은 컨트롤러를 어댑터가 실행
1. RequestMappinghandlerAdapter : @ReqeustMapping에서 사용
2. HttpRequesthandlerAdapter : HttpRequesthandler 처리
3. SimpleControllerHandlerAdapter : Controller 인터페이스 처리

뷰 리졸버
 - 스프링부트는 InternalResourceViewResolver(뷰 리졸버)를 자동으로 등록함
 - 이때 application.properties에서 등록한 spring.mvc.view의 물리명 정보를 사용해서 등록을함