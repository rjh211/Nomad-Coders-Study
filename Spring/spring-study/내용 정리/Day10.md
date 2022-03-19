순수 JDBC
1. build.gradle 에 jdbc, H2 DB 라이블러리 추가
2. application.properties에 접속정보 입력(build.gradle에서 dependency동기화를 해주어야함)
3. spring2.4 이상버전은 `spring.datasource.username=sa`도 반드시 입력해주어야함
4. 상속 repository를 작성 후 springConfig.java에서 datasource 설정
 ![img_6.png](img_6.png)