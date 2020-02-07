package com.fusemachineinventoryproject.service;

import com.fusemachineinventoryproject.dto.ApiLogDTO;
import com.fusemachineinventoryproject.dto.ItemDTO;
import com.fusemachineinventoryproject.mapper.ItemMapper;
import com.fusemachineinventoryproject.model.ApiLog;
import com.fusemachineinventoryproject.model.Item;
import com.fusemachineinventoryproject.model.Users;
import com.fusemachineinventoryproject.repository.ItemRepository;
import com.fusemachineinventoryproject.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ItemService extends AbstractService<Item, String, ItemRepository> implements CommonServiceMethods<Item, String> {

    private ItemMapper mapper;
    private UserRepository userRepository;


    public ItemService(ItemRepository repository, ItemMapper mapper, UserRepository userRepository) {
        super(repository);
        this.mapper = mapper;
        this.userRepository = userRepository;

    }

    public List<Item> itemList(Integer pageNumber, Integer pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Item> pageResult = repository.findAll(pageable);
        if (pageResult.hasContent()) {
            return pageResult.getContent();
        } else {
            return new ArrayList<>();
        }
    }

    public List<ItemDTO> findItemListByName(String itemName) {
        return mapper.toDTOList(repository.findAllByitemName(itemName));
    }

    public List<ItemDTO> findItemListByType(String type) {
        return mapper.toDTOList(repository.findItemListBytype(type));
    }

    public List<ItemDTO> findItemListByQuantity(Integer quantity) {
        return mapper.toDTOList(repository.findAllByavailableQty(quantity));
    }

    public ItemDTO createItem(String userId, ItemDTO itemDTO) {
        Item item = new Item();

        return addUpdateItem(item, userId, itemDTO);

    }


    public ItemDTO updateItem(String itemId, ItemDTO itemDTO) throws NoSuchFieldException {
        Optional<Item> itemFound = repository.findById(itemId);
        if (itemFound.isPresent()) {
            Item itemExisting = itemFound.get();
            addUpdateItem(itemExisting, "", itemDTO);
        }
        throw new NoSuchFieldException("Cannot find Item with Id" + itemId);
    }

    public ItemDTO addUpdateItem(Item item, String userId, ItemDTO itemDTO) {
        Optional<Users> users = userRepository.findById(userId);
        item.setItemName(itemDTO.getItemName());
        item.setItemCode(itemDTO.getItemCode());
        item.setAvailableQty(itemDTO.getAvailableQty());
        if (users.isPresent()) {
            item.setUsers(users.get());
        }
        item.setType(itemDTO.getType());
        return mapper.toDTO(repository.save(item));
    }



}
