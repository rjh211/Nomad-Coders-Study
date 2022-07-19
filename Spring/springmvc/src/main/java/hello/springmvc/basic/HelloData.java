package hello.springmvc.basic;

import lombok.Data;

@Data   //getter, setter, ToString등을 자동으로 만들어준다.
public class HelloData {
    private String username;
    private int age;
}
