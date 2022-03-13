package hello.springstudy.repository;

import hello.springstudy.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest { //굳이 public으로 생성하지 않아도됨

    MemoryMemberRepository repository = new MemoryMemberRepository();

    @AfterEach
    public void afterEach(){
        //테스트 메서드가 끝날때마다 동작을 하는 메서드(call back method와 비슷)
        repository.clearStore();    //테스트 메서드 동작 후 구현체의 clearStore 메서드 수행
    }

    @Test//테스트 어노테이션 작성시 아래 메서드를 실행할 수있음
    public void save(){
        Member member = new Member();
        member.setName("Spring");

        repository.save(member);

        Member result = repository.findById(member.getId()).get();//optional 반환타입에서 가져올경우 .get()메서드로 가져옴

        //System.out.println("reulst = " + (result == member)); //콘솔로 확인할경우
        //Assertions.assertEquals(member,result + "1");//result와 member가 같은지 확인 같다면 Run에서 녹색불이 뜨고, 틀리다면 Run에서 빨간불이뜸 (JUNIT 사용시)
        Assertions.assertThat(member).isEqualTo(result);//assertj.core에서 사용시 사용 ; JUNIT과 ASSERTJ 중 static import를 통해 사용할 모듈을 선택 할 수있다.
    }

    @Test
    public void findByName(){
        Member member1 = new Member();  //member1 save
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();  //member2 save
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
