package mandu.galbi.rediscache.service;


import mandu.galbi.rediscache.AutoScanConfig;
import mandu.galbi.rediscache.domain.Item;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;

import org.mockito.Mock;


import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class ItemServiceTest {

    @Mock
    private ItemRepository itemRepository;

//    @InjectMocks
    private ItemService itemService;

    Item item;

    @BeforeEach
    void setup(){
        item = new Item("abc", "123");
        itemService = new ItemServiceImpl(this.itemRepository);
    }

    @Test
    void testGetItemForId(){
        given(itemRepository.findById(anyString())).willReturn(Optional.of(item));
        Item item = itemService.getItemForId("abc");
        System.out.println(item);
        assertThat(item).isNotNull();
    }




}
