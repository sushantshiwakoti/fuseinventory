package com.fusemachineinventoryproject.repository;

import com.fusemachineinventoryproject.model.Item;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends ExtendedRepository<Item, Long> {


    List<Item> findItemList();
}
