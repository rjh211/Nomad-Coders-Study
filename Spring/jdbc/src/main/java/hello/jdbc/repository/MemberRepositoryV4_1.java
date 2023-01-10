package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import hello.jdbc.repository.ex.MyDbException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.DataSourceUtils;
import org.springframework.jdbc.support.JdbcUtils;

import javax.sql.DataSource;
import java.sql.*;
import java.util.NoSuchElementException;

//예외 누수 문제 해결
// 체크 예외를 런타임 예외로 변경
// MemberRepository 인터페이스 사용
// throws SQLException 제거
// 런타임 Exception으로 변경하여 예외 누수는 막았지만, SQL Exception중 처리를 하고자 하는 예외 또한 잡을수 없게 되었다.
@Slf4j
public class MemberRepositoryV4_1 implements MemberRepository{

    private final DataSource dataSource;

    public MemberRepositoryV4_1(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Member save(Member member) {
        /*생쿼리를 String으로 작성하여 excute할경우 SQL Injection 공격을 당할수 있다.*/
        String sql = "insert into member(member_id, mony) values(?, ?)";
        Connection con = null;
        PreparedStatement pstmt = null;

        try{
            //연결
            con = getConnection();
            pstmt = con.prepareStatement(sql);

            //바인딩
            pstmt.setString(1, member.getMemberId());
            pstmt.setInt(2, member.getMoney());

            //실행
            int rowCount = pstmt.executeUpdate();//영향받는 row의 개수를 return해줌
            return member;
        } catch (SQLException e){
            //체크예외 -> 런타임예외
            throw new MyDbException(e);
        } finally {
            close(con, pstmt, null);
        }
    }

    @Override
    public Member findById(String memberId) {
        //con을 파라미터로 넘기며 메서드를 생성해야 하기 때문에 복잡하다.
        String sql = "select * from member where member_id = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);

            pstmt.executeQuery();//select문은 executeQuery 메서드 사용

            if(rs.next()){ //1회 호출 이후 실 데이터가 들어있다.
                Member member = new Member();
                member.setMemberId(rs.getString("member_id"));
                member.setMoney(rs.getInt("money"));
                return member;
            } else {
                throw new NoSuchElementException("member not found memberId = " + memberId);
            }
        } catch (SQLException e){
            throw new MyDbException(e);
        } finally {
            close(con, pstmt, rs);
        }
    }

    @Override
    public void update(String memberId, int money) {
        String sql = "update member set money = ? where member_id = ?";
        Connection con = null;
        PreparedStatement pstmt = null;
        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, money);
            pstmt.setString(2, memberId);

            int resultSize = pstmt.executeUpdate();//update문은  executeUpdate 메서드 사용

            log.info("resultSize = {}", resultSize);
        } catch (SQLException e){
            throw new MyDbException(e);
        } finally {
            JdbcUtils.closeStatement(pstmt);
        }
    }

    @Override
    public void delete(String memberId) {
        String sql = "delete from member where member_id = ?";
        Connection con = null; //밖에 선언하는 이유는 try내부에 선언을 하면 scope에 의해 finally에서 close를 할 수 없게되므로
        PreparedStatement pstmt = null;
        try{
            con = getConnection();
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, memberId);

            int resultSize = pstmt.executeUpdate();//delete문은  executeUpdate 메서드 사용

            log.info("resultSize = {}", resultSize);
        } catch (SQLException e){
            throw new MyDbException(e);
        } finally {
            close(con, pstmt, null);
        }
    }

    private void close(Connection con, Statement stmt, ResultSet rs){
        JdbcUtils.closeResultSet(rs);
        JdbcUtils.closeStatement(stmt);
        //주의 ! 트랜잭션 동기화 사용을 위해 DataSourceUtils를 사용해야한다.
        //커넥션을 닫지 않은채로 보관한다.
        DataSourceUtils.releaseConnection(con, dataSource);
    }

    public Connection getConnection() throws SQLException {
        //주의 ! 트랜잭션 동기화 사용을 위해 DataSourceUtils를 사용해야한다.
        //트랜잭션 동기화 매니저가 관리하는 커넥션이 있다면 해당 커넥션을 반환(getconnection())
        Connection con = DataSourceUtils.getConnection(dataSource);//내부적으로 트랜잭션동기화매니저에 커넥션을 보관하도록한다.
        log.info("get connection = {}, class={}", con, con.getClass());
        return con;
    }
}
