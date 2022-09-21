package hello.itemservice.web.validation;
import hello.itemservice.domain.item.Item;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class itemValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Item.class.isAssignableFrom(clazz);//자식클래스를 커버하기 떄문에 if == 기능보다 더 효율이 좋다.
    }

    @Override
    public void validate(Object target, Errors errors) {
        //Errors 객체는 BindingResult의 부모클래스
        Item item = (Item) target;
        //bindingResult 에 type이 잘못들어가는경우 504 에러가 아닌 bindingresult에 Exception message가 삽입된다.
        //검증오류 보관
//        Map<String, String> errors = new HashMap<>();//BindingResult객체로 대체, Argument 순서가 중요한다.(ModelAttribute 바로 옆)

        /*if(!StringUtils.hasText(item.getItemName())){//글자가 없다면(Null Check)
//            errors.put("itemName", "상품 명은 필수입니다.");//errors Map을 bindingResult로 매핑시킴
            bindingResult.rejectValue("itemName", "required");//rejectValue의 argument인 errorCode + FieldError의 argument인 objectName + field로 errors.properties에서 자동으로 문자를 들고온다.
        }*/ //아래의 ValidationUtils 코드로 대체할 수 있다.
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "itemName", "required");

        if(item.getPrice() == null || item.getPrice() < 1000 || item.getPrice() > 1000000){
            errors.rejectValue("price", "range", new Object[]{1000, 100000}, null);    //BIndingResult는 앞의 @ModelAttribute(target)을 미리알고 있으므로, rejectValue로 에러메세지를 가져올 수 있다.
        }

        if(item.getQuantity() == null || item.getQuantity() >= 9999){
//            errors.put("quantity", "수량은 9999까지 허용합니다.");
            errors.rejectValue("quantity", "max", new Object[]{9999}, null); //field에러를 해당 메서드에서 자동으로 생성해줌
        }

        //업무규칙 검증
        if(item.getPrice() != null && item.getQuantity() != null){
            int resultPrice = item.getPrice() * item.getQuantity();
            if(resultPrice < 10000){
                errors.reject("totalPriceMin", new Object[]{10000, resultPrice}, null);
            }
        }


    }
}
