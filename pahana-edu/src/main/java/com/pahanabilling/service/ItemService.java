package com.pahanabilling.service;

import com.pahanabilling.model.Item;
import java.util.List;
import java.util.Optional;

public interface ItemService {
    List<Item> list();
    Optional<Item> get(int itemId);
    int create(Item item);          // returns new itemId
    void update(Item item);
    void delete(int itemId);
}
