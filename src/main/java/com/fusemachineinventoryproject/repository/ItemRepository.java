package com.fusemachineinventoryproject.repository;

import com.fusemachineinventoryproject.model.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends ExtendedRepository<Item, String> {

    List<Item> findAllByitemName(String name);

    List<Item> findItemListBytype(String type);

    List<Item> findAllByavailableQty(Integer quantity);
}
