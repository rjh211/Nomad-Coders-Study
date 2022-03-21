package hello.springstudy.service;

import hello.springstudy.domain.Member;
import hello.springstudy.repository.MemberRepository;
import hello.springstudy.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

//jpa는 모든 작업이 트랜젝션 안에서 실행되어야 하는데, 해당 트랜젝션의 범위를 해당 어노테이션의 위치로 설정을 한다.
@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(MemberRepository memberRepository){    //DI : 의존성 주입
        this.memberRepository = memberRepository;
    }
    //회원가입
    public Long join(Member member){
        long start = System.currentTimeMillis();    //기존의 시간측정 방법
        try{
            validateDuplicateMember(member);
            memberRepository.save(member);
            return member.getId();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("join = " + timeMs + "ms");
        }

    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m ->{ //Optional에서 null 아니라 값이 있다면 작동.(중복체크 로직)
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
