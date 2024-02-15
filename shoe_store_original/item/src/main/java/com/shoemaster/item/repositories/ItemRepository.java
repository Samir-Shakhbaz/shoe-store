package com.shoemaster.item.repositories;

import com.shoemaster.item.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> getAllItems();


    void insertNewItem(Item item);
}
