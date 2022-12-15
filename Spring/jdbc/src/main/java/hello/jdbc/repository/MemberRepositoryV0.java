package hello.jdbc.repository;

import hello.jdbc.connection.DBConnectionUtil;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;

import java.sql.*;
import java.util.NoSuchElementException;

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

    public Member findById(String memberId) throws SQLException {
        String sql = "select * from member where member_id = ?";
        Connection con = null; //밖에 선언하는 이유는 try내부에 선언을 하면 scope에 의해 finally에서 close를 할 수 없게되므로
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
            log.error("db error", e);
            throw e;
         } finally {
            close(con, pstmt, rs);
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
