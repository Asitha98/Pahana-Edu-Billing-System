package com.pahanabilling.service.impl;

import com.pahanabilling.factory.DaoFactory;
import com.pahanabilling.dao.ItemDao;
import com.pahanabilling.model.Item;
import com.pahanabilling.service.ItemService;

import java.util.List;
import java.util.Optional;

public class ItemServiceImpl implements ItemService {

    private final ItemDao itemDao;

    public ItemServiceImpl() {
        this(DaoFactory.getItemDao());
    }

    // For tests (dependency injection)
    ItemServiceImpl(ItemDao itemDao) {
        this.itemDao = itemDao;
    }

    @Override
    public List<Item> list() {
        return itemDao.findAll();
    }

    @Override
    public Optional<Item> get(int itemId) {
        return itemDao.findById(itemId);
    }

    @Override
    public int create(Item item) {
        return itemDao.create(item);
    }

    @Override
    public void update(Item item) {
        itemDao.update(item);
    }

    @Override
    public void delete(int itemId) {
        itemDao.delete(itemId);
    }
}
