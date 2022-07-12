package hello.springmvc.basic.reqeustMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
public class MappingController {

    @RequestMapping(value = {"/hello-basic", "/another-url"}, method = RequestMethod.GET)
    public String HelloBasic(){//가장 간단한 방법의 구현체 - URL호출
        log.info("hello Basic");
        return "ok";
    }

    @RequestMapping(value = "/mapping-get-v1", method = RequestMethod.GET)
    public String mappingGetV1(){
        log.info("mapping get v1");
        return "ok";
    }

    @GetMapping(value = "/mapping-get-v2")
    public String mappingGetV2(){
        log.info("mapping get v2");
        return "ok";
    }

    @GetMapping("/mapping/{userId}")
    public String mappingPath(@PathVariable("userId") String data){//리소스 경로에 식별자를 넣어 사용 (최근 GET 호출시 선호하는 방식), PathVariable과 이름이 동일하다면 괄호 내부를 생략가능
        log.info("mappingPath userId={}", data);
        return "ok";
    }

    @GetMapping("/mapping/{userId}/orders/{orderId}")
    public String mappingPath(@PathVariable("userId") String data, @PathVariable Long orderId){//리소스 경로에 식별자를 넣어 사용 (최근 GET 호출시 선호하는 방식), PathVariable과 이름이 동일하다면 괄호 내부를 생략가능
        log.info("mappingPath userId = {}, orderId = {}", data, orderId);
        return "ok";
    }

    /**
     * 파라미터 추가매핑
     * params = "mode"
     * params = "!mode"
     * params = "mode = debug"
     * params = "mode != debug"
     * params = {"mode=debug", "data=good"} 둘중 하나가 있다면 호출
     * */
    @GetMapping(value = "mapping-param", params="mode=debug")   //params : mode=debug라는 파라미터가 있어야지만 메소드 호출이 가능해짐 -> localhost:8080/mapping-param?mode=debug
    public String mappingParam(){
        log.info("mapping Param");
        return "ok";
    }

    /**
     * 헤더 추가매핑
     * headers = "mode"
     * headers = "!mode"
     * headers = "mode = debug"
     * headers = "mode != debug"
     * headers = {"mode=debug", "data=good"} 둘중 하나가 있다면 호출
     * */
    @GetMapping(value = "mapping-header", headers = "mode=debug") // 헤더정보를 바탕으로 메서드 호출여부 결정
    public String mappingHeader(){
        log.info("mappingHeader");
        return "ok";
    }

    @PostMapping(value = "/mapping-consume", consumes = "application/json")//컨텐츠 타입에 따라서 호출여부 결정
    public String mappingConsume(){
        log.info("mapping Consumes");
        return "ok";
    }

    @PostMapping(value = "/mapping-produce", produces = "text/html") // return의 타입에 따라서 호출여부 결정(accept Header) 다르다면 406 에러발생
    public String mappingProduces(){
        log.info("mapping Produces");
        return "ok";
    }
}
