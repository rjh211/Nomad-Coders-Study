package hello.itemservice.repository.mybatis;

import hello.itemservice.domain.Item;
import hello.itemservice.repository.ItemSearchCond;
import hello.itemservice.repository.ItemUpdateDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
import java.util.Optional;

@Mapper
public interface ItemMapper {

    void save(Item item);

    void update(@Param("id") Long id, @Param("updateParam")ItemUpdateDto updateParam);

//    @Select("select id, item_name, price, quantity from item where id = #{id}")
    //@Select 애너테이션을 사용할 수 있지만, xml사용의 장점이 없어져버리기 때문에 굳이 이런방식으로 사용하지는 않는다.
    Optional<Item> findbyId(Long id);

    List<Item> findAll(ItemSearchCond itemSearch);
}
