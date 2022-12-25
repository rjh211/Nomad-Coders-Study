package hello.jdbc.service;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.MemberRepositoryV1;
import hello.jdbc.repository.MemberRepositoryV2;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
/*
* 트랜젝션 - 파라미터 연동, 풀을 고려한 종료
* */
@Slf4j
@RequiredArgsConstructor
public class MemberServiceV2 {

    //@RequiredArgsConstructor 어노테이션은 final 키워드가 붙은 변수의 생성자를 자동으로 생성해주는 기능제공
    private final DataSource dataSource;
    private final MemberRepositoryV2 memberRepository;
    //    public MemberServiceV1(MemberRepositoryV1 memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    public void accountTransfer(String fromId, String toId, int money) throws SQLException {
        Connection con = dataSource.getConnection();
        try{
            con.setAutoCommit(false);

            bizLogic(con, fromId, toId, money);

            con.commit(); //성공시 커밋
        } catch(Exception e){
            con.rollback(); // tlfvotl fhfqor
            throw new IllegalStateException(e);
        } finally{
            release(con);
        }

    }

    private void bizLogic(Connection con, String fromId, String toId, int money) throws SQLException {
        Member fromMember = memberRepository.findById(con, fromId);
        Member toMember = memberRepository.findById(con, toId);

        //계좌이체 트랜젝션 생성
        memberRepository.update(con, fromId, fromMember.getMoney() - money);
        validation(toMember);
        memberRepository.update(con, toId, toMember.getMoney() + money);
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
