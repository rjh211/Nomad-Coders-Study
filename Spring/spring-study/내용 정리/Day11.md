JPA
 - SQL 쿼리도 JPA가 알아서 작성해줌
 - SQL - 데이터 중심 설계에서 객체 중심으로 패러다임 변환
 - 개발 생산성 향상
 - JPA는 Interface이다. 해당 Interface를 제공받아 구현체로 Hibernate등 기술들을 만듬(여러 벤더사가 제공)
 - 해당 실습에선 Hibernate를 사용함
 - JPA는 ORM이라는 기술이다. (Object, Relational, Mapping -> Annotation으로 매핑)
 - @Commit 어노테이션 입력시 실 DB에 반여이 된다.

1. build.gradle
 - implementation 'org.springframework.boot:spring-boot-starter-data-jpa' 작성후 Ctrl + Shift + P
 - data-jpa에는 jdbc도 포함되어있기 때문에 starter-jdbc는 지워도됨
 - SpringBoot가 자동으로 EntityManager를 생성한다.(해당 객체를 주입받아서 사용)
2. Application.properties
 - spring.jpa.show-sql=true 추가(jpa가 날리는 sql확인가능)
   ex `Hibernate: select member0_.id as id1_0_, member0_.name as name2_0_ from member member0_ where member0_.name=?
   Hibernate: insert into member (id, name) values (default, ?)`
 - spring.jpa.hibernate.ddl-auto=none 추가(jpa는 객체를 보고 테이블 DDL도 알아서 생성을 함, 이 실습에서는 미리 만들어진 테이블을 사용하기 때문에 해당기능을 none 상태로 설정 후 실습을 진행함)
 - ddl-auto를 create로 바꾸면 자동ddl이 생성됨
3. @Entity 매핑
4. 