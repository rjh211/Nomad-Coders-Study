package hello.itemservice.web.validation;

import hello.itemservice.domain.item.Item;
import hello.itemservice.domain.item.ItemRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping("/validation/v2/items")
@RequiredArgsConstructor
public class ValidationItemControllerV2 {

    private final ItemRepository itemRepository;

    @GetMapping
    public String items(Model model) {
        List<Item> items = itemRepository.findAll();
        model.addAttribute("items", items);
        return "validation/v2/items";
    }

    @GetMapping("/{itemId}")
    public String item(@PathVariable long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/item";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("item", new Item());
        return "validation/v2/addForm";
    }

    @PostMapping("/add")
    public String addItemV2(@ModelAttribute Item item, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) {
        //bindingResult 에 type이 잘못들어가는경우 504 에러가 아닌 bindingresult에 Exception message가 삽입된다.
        //검증오류 보관
//        Map<String, String> errors = new HashMap<>();//BindingResult객체로 대체, Argument 순서가 중요한다.(ModelAttribute 바로 옆)
        if(!StringUtils.hasText(item.getItemName())){//글자가 없다면(Null Check)
//            errors.put("itemName", "상품 명은 필수입니다.");//errors Map을 bindingResult로 매핑시킴
            bindingResult.rejectValue("itemName", "required");//rejectValue의 argument인 errorCode + FieldError의 argument인 objectName + field로 errors.properties에서 자동으로 문자를 들고온다.
        }

        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
            bindingResult.rejectValue("price", "range", new Object[]{1000, 100000}, null);    //BIndingResult는 앞의 @ModelAttribute(target)을 미리알고 있으므로, rejectValue로 에러메세지를 가져올 수 있다.
        }

        if(item.getQuantity() == null || item.getQuantity() >= 9999){
//            errors.put("quantity", "수량은 9999까지 허용합니다.");
            bindingResult.rejectValue("quantity", "max", new Object[]{9999}, null); //field에러를 해당 메서드에서 자동으로 생성해줌
        }

        //업무규칙 검증
        if(item.getPrice() != null && item.getQuantity() != null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
                bindingResult.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }

        //검증이 실패하면 다시 입력폼으로 돌아가기
        if(bindingResult.hasErrors()){
            log.info("errors = {}", bindingResult);
//            model.addAttribute("errors", errors);bindingresult는 자동으로 viuw로 넘어가기 때문에 ModelAttribute에 넣을 필요가 없다.
            return "validation/v2/addForm";
        }

        //성공로직

        Item savedItem = itemRepository.save(item);
        redirectAttributes.addAttribute("itemId", savedItem.getId());
        redirectAttributes.addAttribute("status", true);
        return "redirect:/validation/v2/items/{itemId}";
    }

    @GetMapping("/{itemId}/edit")
    public String editForm(@PathVariable Long itemId, Model model) {
        Item item = itemRepository.findById(itemId);
        model.addAttribute("item", item);
        return "validation/v2/editForm";
    }

    @PostMapping("/{itemId}/edit")
    public String edit(@PathVariable Long itemId, @ModelAttribute Item item) {
        itemRepository.update(itemId, item);
        return "redirect:/validation/v2/items/{itemId}";
    }

}

