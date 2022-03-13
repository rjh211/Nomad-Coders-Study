package hello.springstudy.repository;

import hello.springstudy.domain.Member;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));//Optional로 감싸서 반환시 Client단에서 처리할 방안이 생김
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream()//옆으로 돌리기
                .filter(member -> member.getName().equals(name))//store의 멤버 이름과 인자로 들어온 이름을 비교하여
                .findAny();  //찾으면 바로 반환하도록 설정(findAny)
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    public void clearStore(){
        store.clear();
    }
}
