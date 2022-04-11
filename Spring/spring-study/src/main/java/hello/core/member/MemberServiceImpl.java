package hello.core.member;

public class MemberServiceImpl implements MemberService{


    private final MemberRepository memberRepository; //DIP를 지키게됨 생성자로 의존성을 주입함

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
