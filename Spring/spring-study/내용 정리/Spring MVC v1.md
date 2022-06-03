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
 - 