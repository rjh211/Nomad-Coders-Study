package hello.typeconverter.controller;

import hello.typeconverter.Type.IpPort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class HelloController {
    @GetMapping("/hello-v1")
    public String helloV1(HttpServletRequest request){
        String data = request.getParameter("data");//default로 String Type이 넘어옴

        //일반적인 형변환 방법
        int intValue = Integer.parseInt(data);
        Integer wraperInteger = Integer.valueOf(data);

        return "ok";
    }

    @GetMapping("/hello-v2")
    public String helloV2(@RequestParam Integer data){
        //String 형태의 data가 Integer Type으로 알아서 형변환해줌(Spring 내부에서 형변환을 일으킴)
        return "ok";
    }
    @GetMapping("/ip-port")
    public String ipPort(@RequestParam IpPort ipPort){
        System.out.println("ipPort IP = " + ipPort.getIp());
        System.out.println("ipPort PORT = " + ipPort.getPort());
        return "ok";
    }
}
