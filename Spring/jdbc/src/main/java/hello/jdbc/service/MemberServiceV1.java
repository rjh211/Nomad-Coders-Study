package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;
import lombok.RequiredArgsConstructor;

import java.sql.SQLException;

@RequiredArgsConstructor
public class MemberServiceV1 {

    //@RequiredArgsConstructor 어노테이션은 final 키워드가 붙은 변수의 생성자를 자동으로 생성해주는 기능제공
    private final MemberRepositoryV1 memberRepository;
    //    public MemberServiceV1(MemberRepositoryV1 memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);

        //계좌이체 트랜젝션 생성
        memberRepository.update(fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update(toId, toMember.getMoney() + money);
    }

    private static void validation(Member toMember) {
        if(toMember.getMemberId().equals("ex")){//고의로 예외발생을 위해 ex인 멤버에게 송금시 에러발생
            throw new IllegalStateException("Ex에게 송금 불가");
        }
    }
}
