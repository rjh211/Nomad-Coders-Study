package hello.itemservice.web.basic;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @PostMapping("/add") //같은 URL호출이여도 호출 방법에 따라 기능이 달라지게 설계
    public String save(){
        return "basic/addForm";
    }
}
