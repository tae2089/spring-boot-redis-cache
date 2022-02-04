package mandu.galbi.rediscache.service;

import mandu.galbi.rediscache.domain.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ExtendWith(SpringExtension.class)
public class ItemServiceIntegrationTest {

    @Autowired
    private ItemRepository itemRepository;

    private ItemService itemService;
    private Item item;
    @BeforeEach
    void setup(){
        item = new Item("abc", "123");
        itemService = new ItemServiceImpl(itemRepository);
        itemRepository.save(item);
        itemRepository.save(new Item("abcd","1234"));
    }

    @Test
    void 널인가(){
        assertThat(itemRepository).isNotNull();

        Item item = itemService.getItemForId("abc");
        System.out.println(itemRepository);
        System.out.println(item);
        assertThat(item.getId()).isEqualTo("abc");
    }

    @Test
    void t(){
       List<Item> itemList = itemRepository.findAll();
        assertThat(itemList.size()).isEqualTo(2);
    }


}
