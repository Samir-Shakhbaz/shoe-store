package com.shoemaster.item.services;

import com.shoemaster.item.models.Item;
import com.shoemaster.item.repositories.ItemRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class ItemService {

    @Autowired
    ItemRepository itemRepository;

    public List<Item> getAllItems() {
        return itemRepository.getAllItems();
    }

    public Item saveNewItem(Item item) {

        return itemRepository.save(item);
    }

    public Item getItemById(Long id) {
        Optional<Item> optional;
        if ((optional = itemRepository.findById(id)).isEmpty()) {
            return null;
        } else {
            return optional.get();
        }
    }

    public Item createNewUser(Item newItem) {
        return itemRepository.save(newItem);
    }

    public Item updateItem(Item updatedItem) {
        Item item = itemRepository.findById(updatedItem.getItemId()).orElseThrow();
        BeanUtils.copyProperties(updatedItem, item);
        return itemRepository.save(item);
    }

    public void deleteItemById(Long id) {
        itemRepository.deleteById(id);
    }
}