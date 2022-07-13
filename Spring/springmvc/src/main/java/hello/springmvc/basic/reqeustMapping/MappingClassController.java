package hello.springmvc.basic.reqeustMapping;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("mapping/users")
public class MappingClassController {

    @GetMapping
    public String user(){
        return "get users";
    }

    @PostMapping
    public String addUser(){
        return "get user";
    }

    @GetMapping("/{userId}")
    public String findUser(@PathVariable String userId){
        return "find user";
    }

    @PatchMapping("/{userId}")
    public String updateUser(@PathVariable String userId){
        return "update user";
    }

    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable String userId){
        return "delete user";
    }
}
