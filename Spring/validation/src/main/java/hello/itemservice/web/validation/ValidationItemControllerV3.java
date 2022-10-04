package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import hello.itemservice.domain.item.SaveCheck;
import hello.itemservice.domain.item.UpdateCheck;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/validation/v3/items")
@RequiredArgsConstructor
public class ValidationItemControllerV3 {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v3/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v3/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v3/addForm";
    }

    @PostMapping("/add")
    public String addItem(@Validated(SaveCheck.class) @ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        //@Validated는 검증기를 실행하라는 Annotation은 Spring에서 글로벌 검증기로 등록을하여 사용이된다. (global Validator은 중복으로 등록할 수 없다. 사용자 custom Validator가 우선이 됨)
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
//            model.addAttribute("errors", errors);bindingresult는 자동으로 viuw로 넘어가기 때문에 ModelAttribute에 넣을 필요가 없다.
            return "validation/v3/addForm";
        }

        if(item.getPrice() != null && item.getQuantity() != null){
            //@ScriptAssert는 너무 기능이 약하기 때문에 자바단에서 로직을 만드는것이 더 효율이 좋을때가 많다.
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        //성공로직
        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v3/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v3/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @Validated(UpdateCheck.class) @ModelAttribute Item item, BindingResult bindingResult) {

        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
//            model.addAttribute("errors", errors);bindingresult는 자동으로 viuw로 넘어가기 때문에 ModelAttribute에 넣을 필요가 없다.
            return "validation/v3/editForm";
        }

        if(item.getPrice() != null && item.getQuantity() != null){
            //@ScriptAssert는 너무 기능이 약하기 때문에 자바단에서 로직을 만드는것이 더 효율이 좋을때가 많다.
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }
        itemRepository.update(itemId, item);
        return "redirect:/validation/v3/items/{itemId}";
    }

}

