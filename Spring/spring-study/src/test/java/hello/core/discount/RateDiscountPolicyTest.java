package hello.core.discount;

import hello.core.member.Grade;
import hello.core.member.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

class RateDiscountPolicyTest {

    RateDiscountPolicy discountPolicy = new RateDiscountPolicy();
    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")//한글로 테스트명을 작성해줌
    void vip_o(){   //성공테스트
        Member member = new Member(1L, "memberVIP", Grade.VIP);

        int discount = discountPolicy.discount(member, 10000);
        assertThat(discount).isEqualTo(1000); //Static import로 사용하는편이 좋음
    }
    @Test
    @DisplayName("VIP가 아니면 10% 할인이 적용되어서는 안된다.")//한글로 테스트명을 작성해줌
    void vip_x(){
        Member member = new Member(2L, "gradeBasic", Grade.BASIC);

        int discount = discountPolicy.discount(member, 10000);
        assertThat(discount).isEqualTo(1000);
        //기대값과 실제값이 나옴
//        org.opentest4j.AssertionFailedError:
//        expected: 1000
//        but was: 0
//        필요:1000
//        실제   :0
    }
}