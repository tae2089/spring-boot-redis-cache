package mandu.galbi.rediscache.service;

import lombok.RequiredArgsConstructor;
import mandu.galbi.rediscache.domain.Item;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service("itemService")
@RequiredArgsConstructor
public class ItemServiceImpl implements ItemService {

    private final  ItemRepository itemRepository;

//    public ItemServiceImpl(ItemRepository itemRepository) {
//        this.itemRepository = itemRepository;
//    }

    @Override
    @Cacheable(value = "itemCache")
    public Item getItemForId(String id) {
        Optional<Item> findItem = itemRepository.findById(id);
        System.out.println(findItem.get());
        if(findItem.isPresent()){
            System.out.println("findItem = " + findItem);
            return findItem.get();
        }else{
            return  null;
        }
    }
}
