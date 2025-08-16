package com.pahanabilling.dao;

import com.pahanabilling.model.Item;
import java.util.*;

public interface ItemDao {
    Optional<Item> findById(int itemId);
    Optional<Item> findBySku(String sku);
    List<Item> findAll();
    int create(Item item);      // returns generated itemId
    void update(Item item);
    void delete(int itemId);
}
