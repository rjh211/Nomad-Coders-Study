package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("memberSerivceBean")//Bean Name 지정
public class MemberServiceImpl implements MemberService{


    private final MemberRepository memberRepository; //DIP를 지키게됨 생성자로 의존성을 주입함

    @Autowired //ac.getBean(MemberRepository.class) 가 들어간다고 생각하면됨.
    public MemberServiceImpl(MemberRepository memberRepository) { //생성자 주입
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트용
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
