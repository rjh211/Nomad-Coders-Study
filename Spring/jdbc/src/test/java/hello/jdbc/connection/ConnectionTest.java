package hello.jdbc.connection;

import com.zaxxer.hikari.HikariDataSource;
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

    @Test
    void dataSourceConnectionPool() throws SQLException, InterruptedException {
        //커넥션 풀링
        //cmd상단에서 Hikari의 파라미터를 확인할 수 있다.
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl(URL);
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setMaximumPoolSize(10);
        dataSource.setPoolName("MyPool");

        userDataSource(dataSource);
        Thread.sleep(1000);//sleep을 하지 않으면 생성메서드 실행후 바로 커넥션생성 메서드가 끝나므로, 여러개의 POOL생성이 되지 않는다.
    }

    private void userDataSource(DataSource dataSource) throws SQLException {
        //Datasource를 사용하여 Connection생성시에는 DataSource초기값 한번만 입력하면된다.
        Connection con1 = dataSource.getConnection();
        Connection con2 = dataSource.getConnection();
        //최대치 이상의 커넥션 획득시 total = 10, active = 10, waiting = N 으로 표기가되며, block에 걸린다.
        //request time out 설정을 한다면, 해당시간이 지난후 접속이 끊기게 된다.

        //서로다른 connection임을 알 수 있다.
        log.info("connection={}, class={}", con1, con1.getClass());
        log.info("connection={}, class={}", con2, con2.getClass());
    }
}
