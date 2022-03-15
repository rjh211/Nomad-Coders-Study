1. Optional.ifPresent -> 값이 있다면 콜백 메서드를 실행
2. Optional.orElseGet -> 값이 있다면 꺼내고, 없다면 콜백 함수를 이용해서 처리
3. 빌드시 Test코드는 제외할 수 있으므로 테스트 메서드는 한글로도 많이 작성되는 편이다.
4. 테스트 코드작성시 given(주어진것),when(실행했을때),then(실행 결과)을 작성하여 논리적/직관적으로 테스트케이스를 작성한다.
5. @BeforeEach를 통해 메서드 실행전 미리 세팅을 해둘 수 있다.

* 단축키
    * ctrl+shift+t => 테스트 케이스 자동생성
    * ctrl+alt+shift+t => 메서드/클래스 추출
    * ctrl+shift+enter => 해당 라인 자동완성
    * ctrl+alt+v => 리펙토링
* DI : 의존성주입
  * 객체를 클래스에서 직접 new하지 않고, 외부에서 인자를 통해 전달 받는 행위
  * ex. MemberController가 MemberService를 의존한다.(의존관계)
