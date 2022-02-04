package mandu.galbi.rediscache.service;

import mandu.galbi.rediscache.domain.Item;

public interface ItemService {
    Item getItemForId(String id);
}
