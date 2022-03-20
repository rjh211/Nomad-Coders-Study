package hello.springstudy.domain;

import javax.persistence.*;

//@Entity를 사용하면 JPA가 관리하는 엔터티가 되는것이다.
@Entity
public class Member {
    //pk와 시퀀스(Identity) 설정을위해 아래와 같은 어노테이션을 작성한다.
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //실제 DB의 컬럼명이 usrename일 경우 @Column으로 변수와 컬럼명을 매칭시킨다.
//    @Column(name = "username")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
