웹 스코프
 - 웹환경에서만 동착
 - 종료시점까지 스프링 컨테이너가 관리를 해줌 ->종료메서드 호출 
 - 싱글톤과 달리 요청별로 각각의 객체를 전달해줌
 - 프로토 타입과 달리 HTTP Request life cycle동안은 스프링 컨테이너가 종료시점 까지 관리를함

request : Http요청 하나가 들어왔다가 나갈때까지 유지되는 스코프, HTTP요청 별로 빈 인스턴스가 생성됨
session : HTTP Session과 동일한 별도의 빈 인스턴스가 생성됨
application : 서블릿 컨텍스트와 동일한 생명주기를 가짐
webSocket : 웹 소켓과 동일한 생명주기를 가지는 스코프

Request 스코프
 - build.gradle에 implementation추가(spring boot에서 시작부터 추가를 해도됨)
 - 동시에 여러 Http요청이 오면 정확히 어떤 요청이 남긴 로그인지 구분을 할 수 있음
 - [고객UUID][REQUEST URL][MESSAGE]

스코프와 프록시
 - proxyMode: TARGET_CLASS로 지정하면 해당 클래스는 프록시로 지정됨
   - 타겟이 Class면 ScopedProxyMode.TARGET_CLASS, 인터페이스면 ScopedProxyMode.INTERFACE 선택
   - 가짜 프록시 클래스를 만들어서 빈을 주입시킴
   - CGLIB이 만든 클래스를 실제로 등록하여 사용한다.
   - 가짜 프록시 객체는 Client에게 요청이 오면 그때 내부에서 진짜 빈을 요청하는 위임 로직이 있음.(가짜 프록시 빈은 내부의 진짜 myLogger를 찾을 수 있다.)
 - 다형성과 DI 컨테이너를 사용하여 애너테이션 설정만으로 프록시를 원본객체로 대체할 수 있다.
 - 주의 사항
   - 싱글톤처럼 사용하는것 같지만 다르게 동작하기 때문에 조심해야함
     - 싱글톤과 달리 프록시가 각각 따로 생성됨
   - 테스트 및 유지보수가 어려워지기 때문에 꼭 필요한 환경에서만 사용 권장