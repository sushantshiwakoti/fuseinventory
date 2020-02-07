package com.fusemachineinventoryproject.service;

import com.fusemachineinventoryproject.dto.ItemDTO;
import com.fusemachineinventoryproject.mapper.ItemMapper;
import com.fusemachineinventoryproject.model.Item;
import com.fusemachineinventoryproject.model.Users;
import com.fusemachineinventoryproject.repository.ItemRepository;
import com.fusemachineinventoryproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ItemService extends AbstractService<Item, Long, ItemRepository> implements CommonServiceMethods<Item, Long> {

    private ItemMapper mapper;
    private UserRepository userRepository;

    public ItemService(ItemRepository repository, ItemMapper mapper, UserRepository userRepository) {
        super(repository);
        this.mapper = mapper;
        this.userRepository = userRepository;

    }

    public List<ItemDTO> itemList() {
        return mapper.toDTOList(repository.findAll());
    }

    public ItemDTO createItem(Long userId, ItemDTO itemDTO) {
        Item item = new Item();
        return addUpdateItem(item, userId, itemDTO);

    }

    public ItemDTO updateItem(Long itemId, ItemDTO itemDTO) throws NoSuchFieldException {
        Optional<Item> itemFound = repository.findById(itemId);
        if (itemFound.isPresent()) {
            Item itemExisting = itemFound.get();
            addUpdateItem(itemExisting, 0l, itemDTO);
        }
        throw new NoSuchFieldException("Cannot find Item with Id" + itemId);
    }

    public ItemDTO addUpdateItem(Item item, Long userId, ItemDTO itemDTO) {
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
