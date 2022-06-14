package hello.servlet.basic;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter //실제로 Getter , Setter를 생성한건과 같아짐
public class HelloData {
    private String username;
    private int age;
}
