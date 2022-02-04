package mandu.galbi.rediscache;

import lombok.RequiredArgsConstructor;
import mandu.galbi.rediscache.domain.Item;
import mandu.galbi.rediscache.service.ItemService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ItemController {

    private final ItemService itemService;

    @GetMapping("/item/{id}")
    public Item getItemById(@PathVariable String id) {
        System.out.println(id);
        return itemService.getItemForId(id);
    }


}
