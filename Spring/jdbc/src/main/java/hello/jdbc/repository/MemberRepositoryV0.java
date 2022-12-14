package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;

//JDBC - DriverManager 사용
@Slf4j
public class MemberRepositoryV0 {

    public Member save(Member member) throws SQLException{
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
            log.error("db error", e);
            throw e;
        } finally {
            close(con, pstmt, null);
        }
    }

    private void close(Connection con, Statement stmt, ResultSet rs){
        //statement는 sql을 그대로 실행하는 역할만 하지만, prepareStatement는 조금더 많은 기능을 제공한다. (Statemet를 상속받아 추가기능을 개발함)
        if(rs != null){
            try{
                rs.close();
            } catch (SQLException e){
                log.info("error",e );
            }
        }
        if(stmt != null){
            try{
                stmt.close();
            } catch (SQLException e){
                log.info("error", e);
            }
        }
        if(con != null){
            try{
                con.close();
            } catch (SQLException e){
                log.info("error", e);
            }
        }
    }
    public Connection getConnection(){
        return DBConnectionUtil.getConnection();
    }
}
