package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV2;
import hello.jdbc.repository.MemberRepositoryV3;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/*
* 트랜젝션 - 트랜잭션 매니저
* */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV3_1 {
    //@RequiredArgsConstructor 어노테이션은 final 키워드가 붙은 변수의 생성자를 자동으로 생성해주는 기능제공

    //    private final DataSource dataSource;
    private final PlatformTransactionManager transactionManager;//new DataSourceTransactionManager()를 주입받게됨-> jdbc관련 TR매니저(외부에서 주입할 예정)
    private final MemberRepositoryV3 memberRepository;
    //    public MemberServiceV1(MemberRepositoryV1 memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        //xmfoswortus tlwkr
        TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());//timeout등 기본 설정값 정의(default로 설정)

        try{
            bizLogic( fromId, toId, money);

            transactionManager.commit(status); //성공시 커밋
        } catch(Exception e){
            transactionManager.rollback(status); // tlfvotl fhfqor
            throw new IllegalStateException(e);
        } finally{
//            release(con); transactionManager가 알아서 commit/rollback시 release해주므로 해당 메서드는 쓸모가없다.
        }

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
