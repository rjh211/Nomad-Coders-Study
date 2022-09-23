package hello.itemservice.validation;

import hello.itemservice.domain.item.Item;
import org.junit.jupiter.api.Test;

import javax.validation.*;
import java.util.Set;

public class beanValidationTest {
    @Test
    void beanValidation(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();

        Item item = new Item();
        item.setItemName(" ");
        item.setPrice(0);
        item.setQuantity(10000);

        Set<ConstraintViolation<Item>> validations = validator.validate(item);
        for (ConstraintViolation<Item> violation : validations) {
            System.out.println("violation = " + violation);
            System.out.println("violation = " + violation.getMessage()); //하이버네이트에서 기본적으로 제공해주는 메세지를 출력한다.
        }
        
    }
}
