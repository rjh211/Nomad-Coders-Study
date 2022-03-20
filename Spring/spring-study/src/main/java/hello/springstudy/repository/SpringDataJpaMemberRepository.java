package hello.springstudy.repository;

import hello.springstudy.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SpringDataJpaMemberRepository extends JpaRepository<Member, Long>, MemberRepository{
    //JPQL : select m from Member m where m.name = ?
    //메서드의 이름을보고 JPQL을 구성함(ex. findByNameAndId(String name, Long id))
    @Override
    Optional<Member> findByName(String name);
}
