package hello.jdbc.repository;

import com.zaxxer.hikari.HikariDataSource;
import hello.jdbc.connection.ConnectionConst;
import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static hello.jdbc.connection.ConnectionConst.*;
import static hello.jdbc.connection.ConnectionConst.PASSWORD;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@Slf4j
class MemberRepositoryV1Test {

    MemberRepositoryV1 repository;

    @BeforeEach
    void beforeEach(){
        //기본 DriverManager - 항상 새로운 커넥션 획득
//        DriverManagerDataSource dataSource = new DriverManagerDataSource(URL, USERNAME, PASSWORD);

        //Connection Pooling 사용 (Hikari)
        //Connection Pool은 사용시 자원을 획득하고, close시 자원을 반환하도록 설계가 되어있다.(재사용성이 증가함)
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setUsername(USERNAME);
        dataSource.setPassword(PASSWORD);
        dataSource.setJdbcUrl(URL);

        repository = new MemberRepositoryV1(dataSource);
    }

    @AfterEach
    void afterEach() throws SQLException {
        repository.delete("memberV0");
    }

    @Test
    void crud() throws SQLException {
        Member member = new Member("memberV0", 10000);
        repository.save(member);

        //findById
        Member findMember = repository.findById(member.getMemberId());
        log.info("findMember = {}", findMember);

        assertThat(findMember).isEqualTo(member);//Lombok의 @Data 내부에는 ToString과 EqualsAndHashCode가 재정의 되어있기 때문에 자동으로 Equals 연산시 같다고 나오게된다.

        //update 10000 -> 20000
        repository.update(member.getMemberId(), 20000);
        Member updateMember = repository.findById(member.getMemberId());
        assertThat(updateMember.getMoney()).isEqualTo(20000);

        repository.delete(member.getMemberId());
        assertThatThrownBy(() -> repository.findById(member.getMemberId())).isInstanceOf(NoSuchElementException.class);

        //해당 테스트 메서드는 CRUD를 한번에 테스트 할 수 있도록 생성 -> 조회 -> 변경 -> 삭제 순환이 이루어졌기 떄문에 테스트하기가 편해진다.
    }
}