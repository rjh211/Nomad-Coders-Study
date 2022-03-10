- 정적 컨텐츠 : 파일 그대로를 웹브라우저에 전달
- MVC와 템플릿 엔진 : JSP, PHP와 같은 템플릿 엔진(HTML을 그냥 주는게 아니라 SERVER에서 프로그래밍해서 HTML을 동적으로 바꿔줌)
- API : DATA FORMAT(JSON, XML등)으로 CLIENT에게 전달(CLIENT가 DATA를 사용하여 알아서 화면을 그림)


- 정적컨텐츠
  1. localhost:8080/hello-static.html 입력
  2. 내장 톰캣 서버에서 Spring에 위 URL이 도착했다고 알림
  3. Spring이 Controller의 집합에서 hello-static이라는 컨트롤러가 있는지 Searching
  4. 없다면 static으로 와서 hello-static.html이라는 파일이 있는지 확인(컨트롤러가 스태틱보다 우선순위가 높음을 알 수 잇음)
  5. hello-static.html 반환
  
- MVC와 템플릿 엔진
  - 모델 1 방식 : jsp(View)에서 모든걸 해결함
  - MVC : Model, View, Controller
  - View : 화면을 그리는데 집중을 함
  - Controller, Model : 비지니스, 내부적인 처리에 집중
  - @getMapping(/temp)사용시 url로 temp입력시 Controller를 찾아오게됨
  - @RequestParam은 url에서 주는 인자를 받아와서 사용함
   => localhost:8080/hello-mvc?name=Spring
  1. url 호출을 하면 내장 톰켓 서버가 Spring에 던짐
  2. url 뒷단의 경로에 맞는 내용의 Controller가 있는지 확인
  3. 매핑이 되어있는 경우, url정보(변수)를 가지고 메서드 실행
  4. 메서드 리턴값으로는 static의 html파일 명에 해당하는 String값을 전달
  5. View Resolver가 동작 하여 view를 찾아감
  6. HTML에서 Timeleaf가 동작하여 HTML변환후 브라우저에 전달
  
-API
 - @ResponseBody : `<body></body>`테그 내에 return String을 포함하여 반환 Ex.`<body>hello</body>`
 - xml 방식은 무겁기도 하고 태그 열고닫고를 반복해야 하기 때문에 비효율적이다.
 - json은 key:value 쌍으로 심플하다.
 - ResponseBody 태그에서도 default로 json형식으로 api를 리턴하게된다.
 - getter, setter를 통한 캡슐화 : java bean 표준화 방식 or property 접근 방식
   1. url 호출을 하면 내장 톰켓 서버가 Spring에 던짐
   2. url 뒷단의 경로에 맞는 내용의 Controller가 있는지 확인
   3. @RespnseBody 어노테이션 확인 -> View Resolver가 아닌 http body에 바로 넘기려고 한다.
   4. return type이 Object type인걸 확인 후 json 형태의 key : value 쌍으로 변환하여 return(설정을통해 json이 아닌 다른방식으로도 전달가능)
   5. httpMessageConverter가 작동하여 return type 확인 후 String Converter(StringHttpMessageConverter) or JsonConverter(MappingJackson2HttpMessageConverter -> 설정을 통해 gson을 사용할 수 있음)을 작동시킨후 브라우저에 전달 (String, Object가 아닌 byte등 여러 객체에 대한 처리도 가능(Spring의 HttpMessageConverter 확인))
    -> 클라이언트의 HTTP Accept Header와 서버의 클라이언트 반환 타입 정보 툴을 조합해서 `httpMessageConverter`가 선택된다.
   6. 