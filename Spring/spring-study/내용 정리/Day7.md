자바 코드로 직접 스프링 빈 등록하기 (Not Use Annotation) (XML로 생성하는 방식이 있긴하지만, 최근에는 잘 쓰이지 않는 추세이다.)
1. Config파일을 통한 빈등록
   1. Config 파일을 만든다(JAVA 확장자)
   2. Class상단에 @Configuration 어노테이션을 작성한다.
   3. 클래스 내부에 메서드를 선언하며 @Bean 어노테이션을 달아준다.
   4. @Configuration , @Bean Annotation을 확인하여 스프링 컨테이너에 등록을 한다.
   5. Service와 Repository의 의존관계가 있지만, 일단 두 메서드의 빈을 일단 컨테이너에 등록을 하고, Service 생성시 Repository의 객체를 넣어주는 순서로 진행된다. (선 객체 생성 -> 후 의존성 주입(관계형성)
   



DI(Dependency Injection)
 1. DI에는 필드주입, setter주입, 생성자주입 3가지 방식이 있다.(현재까지의 실습 repository-service 의존관계 형성은 생성자주입방식)
    1. 필드주입 : @Autowired private MemberService memberSerevice;
       - 생성자에 @Autowired 어노테이션을 붙이지 않고, 변수 선언시 바로 어노테이션을 붙이는 방식
       - 필드주입은 스프링이 올라갈때 의존성주입이 끝나면 이후로는 변경이 불가능 하여, 다른 객체를 사용할 수 없다.
    2. Setter주입 : 변수의 Setter를 생성하고, Setter 메서드 위에 @Autowired 작성
       - Setter 호출시 의존성 주입이되는 방식
       - Setter가 public 형태로 노출이 되어야 하므로, 객체를 잘못 바꾸는 경우가 생기게된다.
    3. 생성자 주입 : 클래스의 생성자 생성후, 상단에 @Autowired 어노테이션 작성 (가장 선호하는 방법)
        - 어플리케이션이 조립(스프링 컨테이너에 올라가고 설정을 하는 단계)될 때 객체가 한번 주입되고, 이후에는 변경이 되지않음.
        - 어플리케이션 실행도중 의존관계가 동적으로 변하는 경우는 거의 없으므로 가장 권장되는 방식이다.

 - 실무에서는 주로 정형화된(컨트롤러, 서비스, 리포지토리) 코드는 컴포넌트 스캔을 사용한다.
 - 정형화되지 않거나, 상황에 따라 구현 클래스를 변경해야 할 경우에 설정파일을 통해 스프링 Bean으로 등록함.(Ex. MemoryMemberRepository -> DBMemberRepositort 교체등..)
 - @Autowired를 통한 DI는 스프링에서 관리하는 객체에서만 동작한다.(스프링 빈에 등록이 되어있어야함.)


회원 관리 예제 구현
- Welcome Page의 우선순위
 1. GetMapping("/") 으로 컨테이너에 등록된 컨트롤러가 있다면, index.html은 무시되고 매핑된 컨트롤러가 우선순위를 갖게된다.
 2. '회원가입'링크 클릭시 get 방식으로 /members/new 매핑이됨.
 3. createForm은 members/createMemberForm으로 이동만 시켜줌
 4. 해당 페이지에서는 form을 이용하여 post방식으로 /member/new로 이동시킨다.
 - postMapping : 데이터를 묶어서 전달시 주로사용
 - GetMapping : 주로 조회용에 사용
 5. 버튼 클릭시 MemberForm의 getter, setter를 확인하고 key값을 통해 해당 변수에 값을 설정한 후 객체 전달
 6. 이후 getter를 통해 값을 가져온후, memberService의 join 메서드를 이용하여 입력값 저장.