package hello.core.member;

import hello.core.AppConfig;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class MemberServiceTest {

    MemberService memberService;

    @BeforeEach
    public void beforeEach(){
        AppConfig appConfig = new AppConfig();
        memberService = appConfig.memberService();
    }
//    MemberService memberService = new MemberServiceImpl();

    @Test
    void join(){
        //given
        Member member = new Member(1L, "memberA", Grade.VIP);
        //when
        memberService.join(member);
        Member findMember = memberService.findMember(1L);
        //then
        Assertions.assertThat(member).isEqualTo(findMember);
    }

    @Test
    void select(){
        Member member = new Member(1L, "memberA", Grade.VIP);

        memberService.join(member);

        memberService.join(new Member(2L, "memberB", Grade.BASIC));

        Member findMember = memberService.findMember(1L);

        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }
}
