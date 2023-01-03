package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.Connection;
import java.sql.SQLException;

/*
* 트랜젝션 - 트랜잭션 템플릿
* */
@Slf4j
public class MemberServiceV3_2 {
    //비지니스 로직 + 트랜잭션 메서드
    private final MemberRepositoryV3 memberRepository;
    private final TransactionTemplate txTemplate;

    public MemberServiceV3_2(PlatformTransactionManager transactionManager, MemberRepositoryV3 memberRepository) {
        this.txTemplate = new TransactionTemplate(transactionManager);
        this.memberRepository = memberRepository;
    }

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        //Transaction Manager를 통해 프록시 시작
        //bizLogic이 예외없이 성공하면 commit, 예외발생시 rollback을 진행한다.
        txTemplate.executeWithoutResult((status)->{
            //람다 내부에서는 checked Exception을 밖으로 던질수 없기 때문에 IllegalStateException(unChecked Exception)으로 변경하여 사용하기 위한 try-catch문
            try {
                bizLogic(fromId, toId, money);
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            }
        });
    }

    private void bizLogic(String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(fromId);
        Member toMember = memberRepository.findById(toId);

        //계좌이체 트랜젝션 생성
        memberRepository.update(fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update(toId, toMember.getMoney() + money);
    }

    private static void release(Connection con) {
        if(con != null){
            try{
                con.setAutoCommit(true);//기본값이 true이기 때문에 원복시켜줌
                con.close();
            } catch (Exception e){
                log.info("error", e);
            } finally {

            }
        }
    }

    private static void validation(Member toMember) {
        if(toMember.getMemberId().equals("ex")){//고의로 예외발생을 위해 ex인 멤버에게 송금시 에러발생
            throw new IllegalStateException("Ex에게 송금 불가");
        }
    }
}
