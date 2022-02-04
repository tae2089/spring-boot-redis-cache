package mandu.galbi.rediscache.service;

import mandu.galbi.rediscache.domain.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@DataJpaTest
@Transactional
@ActiveProfiles("test")
class ItemRepositoryTest {

    @Autowired
    private ItemRepository itemRepository;

    Item item;

    @BeforeEach
    void setup(){
        item = new Item("abcd", "123");
    }

    @Test
    void findId(){
        itemRepository.save(item);
        Optional<Item> findItem = itemRepository.findById("abcd");
        System.out.println(findItem.get().toString());
        assertThat(findItem.get().getId()).isEqualTo("abcd");
    }

}
