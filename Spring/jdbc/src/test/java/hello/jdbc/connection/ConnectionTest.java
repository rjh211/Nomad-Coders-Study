package hello.jdbc.connection;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static hello.jdbc.connection.ConnectionConst.*;

@Slf4j
public class ConnectionTest {

    @Test
    void driverManager() throws SQLException {
        //Connection 생성마다 URL, USERNAME, PASSWORD를 넘겨줘야함
        Connection con1 = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        Connection con2 = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        //서로다른 connection임을 알 수 있다.
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con1.getClass());
    }

    @Test
    void dataSourceDrivaerManager() throws SQLException {
        //항상 새로운 커넥션 획득
        DataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);
        userDataSource(dataSource);
    }

    private void userDataSource(DataSource dataSource) throws SQLException {
        //Datasource를 사용하여 Connection생성시에는 DataSource초기값 한번만 입력하면된다.
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();

        //서로다른 connection임을 알 수 있다.
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());
    }
}
