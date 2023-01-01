package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Connection;
import java.sql.SQLException;

/*
* 트랜젝션 - @Transactional AOP
* */
@Slf4j
public class MemberServiceV3_3 {
    //비지니스 로직 + 트랜잭션 메서드
    private final MemberRepositoryV3 memberRepository;

    public MemberServiceV3_3(MemberRepositoryV3 memberRepository) {
        this.memberRepository = memberRepository;
    }

    //클래스 단위로 어노테이션을 붙여도된다.
    @Transactional
    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        //Spring AOP를 이용한 트랜잭션 자동화( 해당 메서드 호출시 자동으로 트랜잭션 적용 )
        bizLogic(fromId, toId, money);
    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
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
