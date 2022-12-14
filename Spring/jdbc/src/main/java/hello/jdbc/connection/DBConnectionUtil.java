package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class DBConnectionUtil {
    //jdbc 표준인터페이스가 제공하는 방식
    public static Connection getConnection(){
        try {
            //url을 바탕으로 해당 DB의 jdbc를 호출한다.(여러 dbms의 lib를 가지고 있을경우)
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            log.info("get connection = {}, class = {}", connection, connection.getClass());
            return connection;
        } catch (SQLException e) {
            throw new IllegalStateException(e);//check Exception -> RunTime Exception으로 전환
        }
    }
}
