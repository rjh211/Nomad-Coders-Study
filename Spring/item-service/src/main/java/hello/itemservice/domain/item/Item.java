package hello.itemservice.domain.item;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

//@Data는 모든 변수에대한 Getter,Setter를 생성하기 때문에 사용시 위험이 다른다. 보통은 @Getter, @Setter만 사용
@Data
public class Item {
    private Long id;
    private String itemName;
    private Integer price;
    private Integer quantity;


    public Item(String itemName, Integer price, Integer quantity) {
        this.itemName = itemName;
        this.price = price;
        this.quantity = quantity;
    }
}
