package hello.springstudy.repository;

import hello.springstudy.domain.Member;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

public class JpaMemberRepository implements MemberRepository{

    //JPA는 엔터티 매니저라는 걸로 모든게 동작한다.
    private final EntityManager em;

    public JpaMemberRepository(EntityManager em) {
        this.em = em;
    }

    @Override
    public Member save(Member member) {
        em.persist(member);//insert 쿼리를 만들어서 저장까지해줌. member의 setid까지 해줌
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) { //pk기반으로 1건만 가지고 오는경우는 쿼리작성이 필요없다.
        Member member = em.find(Member.class, id); //조회할 type과 식별자를 넣어준다.
        return Optional.ofNullable(member);
    }

    @Override
    public Optional<Member> findByName(String name) {
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList().stream().findAny();

    }

    @Override
    public List<Member> findAll() {
        return em.createQuery("select m from member m", Member.class) //여기선 객체 자체를 조회하기 때문에 *가 아닌 m자체를 select한다.
                .getResultList();
        //jpql 쿼리를 작성함 -> 테이블 대상이 아닌 객체를 대상으로 쿼리를 날림 -> SQL로 번역이됨
    }
}
