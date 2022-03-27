package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;

public class OrderServiceImpl implements OrderService{


    private final MemberRepository memberRepository; //final 사용시 반드시 생성자가 할당이 되어있어야함.
    private final DiscountPolicy discountPolicy; //인터페이스에만 의존하도록 변경(AppConfig.java)
//    private final DiscountPolicy discountPolicy = new RateDiscountPolicy();
//    private final DiscountPolicy discountPolicy = new FixDiscountPolicy(); //할인정책을 바꾸기 위해서는 클라이언트의 코드를 해당 라인처럼 수정을 해야하는 번거로움이 있음.
    public OrderServiceImpl(MemberRepository memberRepository, DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {
        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);
    }
}
