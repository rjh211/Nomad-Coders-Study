package hello.jdbc.repository;

import hello.jdbc.domain.Member;

import java.sql.SQLException;

public interface MemberRepositoryEx {
    //상위객체
    //상위객체에서 throws ...Exception을 적용해야만 구현체에서 해당 Exception 및 해당 Exception의 하위 Exception을 throw할 수 있다.
    Member save(Member member) throws SQLException;
    Member findById(Member member) throws SQLException;
    void update(Member member, int money) throws SQLException;
    void delete(Member member) throws SQLException;
}
