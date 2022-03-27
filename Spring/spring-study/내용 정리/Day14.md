할인 정책 변경

- 기존의 OrderServiceImple은 DiscountPolicy를 의존하고 있었다.
그렇지만 실제로 확인해보면 DiscountPolicy(인터페이스)와 FixDiscountPolicy(구현체)를 동시에 의존하는 꼴이었다.
DiscountPolicy discountpolicy = new FixDiscountPolicy();
=> DIP위반
- 클라이언트가 인터페이스만 의존하도록 변경

AppConfig
 - App전체의 동작 방식을 구성하기 위해 구현객체 생성 / 연결을 하는 책임을 가진 별도의 설정 클래스