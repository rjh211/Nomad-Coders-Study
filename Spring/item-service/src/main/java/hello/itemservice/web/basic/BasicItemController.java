package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;

@Controller
@Slf4j
@RequestMapping("/basic/items")
@RequiredArgsConstructor
public class BasicItemController {

    private final ItemRepository repository;

//    @Autowired @RequiredArgsConstructor에서 같은 코드를 생성해줌
//    public BasicItemController(ItemRepository repository){
//        this.repository = repository;
//    }
    @GetMapping
    public String items(Model model){
        List<Item> items = repository.findAll();
        model.addAttribute("items", items);
        return "basic/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model){
        Item item = repository.findById(itemId);
        model.addAttribute("items", item);
        return "basic/item";
    }

    @PostConstruct
    public void init(){
        repository.save(new Item("ItemA", 10000, 10));
        repository.save(new Item("ItemB", 20000, 20));
    }

    @GetMapping("/add")
    public String addForm(){
        return "basic/addForm";
    }

    //    @PostMapping("/add") //같은 URL호출이여도 호출 방법에 따라 기능이 달라지게 설계
    public String save(@RequestParam String itemName, @RequestParam int price, @RequestParam Integer quantity, Model model){
        Item item = new Item(itemName, price, quantity);

        repository.save(item);

        model.addAttribute("item", item);

        return "basic/item";
    }

    @PostMapping("/add") //같은 URL호출이여도 호출 방법에 따라 기능이 달라지게 설계
    public String save(@ModelAttribute("item") Item item, Model model){ //속성들을 일일히 가져오지 않고, ModelAttribute를 통해 item 객체를 바로가져옴(요청 파라미터 처리)
        repository.save(item);

//        model.addAttribute("item", item); //@ModelAttribute는 모델에 직접 객체를 넣어주기 떄문에 메서드 생략이 가능하다.(Model 추가)

        return "basic/item";
    }
}
