package hello.springstudy.service;

import hello.springstudy.domain.Member;
import hello.springstudy.repository.MemoryMemberRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    @BeforeEach
    public void beforeEach(){
        memberRepository = new MemoryMemberRepository();
        memberService = new MemberService(memberRepository);
    }
    @AfterEach
    public void afterEach(){
//        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        Long saveId = memberService.join(member);
        //then
        Member findMember = memberService.findOne(saveId).get();
        Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    }

    @Test
    void 중복검증(){
        Member member1 = new Member();
        member1.setName("spring1");
        Member member2 = new Member();
        member2.setName("spring1");

        memberService.join(member1);
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> {//try Catch예외 대신으로 사용할 수잇다.
            memberService.join(member2);
        });//IllegalStateExceoption이 나오면 테스트 성공, 이외의 에러 발생시 테스트 실패

        Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

        /*        try{
            memberService.join(member1);
            memberService.join(member2);
            fail();
        }catch(IllegalStateException e){
            Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.")
        }*/
    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}