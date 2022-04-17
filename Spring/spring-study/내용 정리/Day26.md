롬복
 - 생성자주입이 좋긴하지만, 코드가 너무 길어진다는 단점이 있음
 - 자바의 getter, setter, toString과 같은 메서드를 자동으로 어노테이션 프로세싱해주는 라이브러리
 - RequiredArgsConstructor final키워드가 붙은 변수에 대해 생성자를 만들어줌(ctrl + F12)
![img_21.png](img_21.png)

설치 방법

![img_19.png](img_19.png)

![img_20.png](img_20.png)

build.grade
 - 	//lombok 라이브러리 추가
      compileOnly 'org.projectlombok:lombok'
      annotationProcessor 'org.projectlombok:lombok'

	testCompileOnly 'org.projectlombok:lombok'
	testAnnotationProcessor 'org.projectlombok:lombok'
 - //lombok 설정 추가 시작
configurations {
compileOnly {
extendsFrom annotationProcessor
}
}
