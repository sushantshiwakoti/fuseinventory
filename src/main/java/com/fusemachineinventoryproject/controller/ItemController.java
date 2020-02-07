package com.fusemachineinventoryproject.controller;

import com.fusemachineinventoryproject.dto.ItemDTO;
import com.fusemachineinventoryproject.mapper.ItemMapper;
import com.fusemachineinventoryproject.model.Item;
import com.fusemachineinventoryproject.model.RestResponse;
import com.fusemachineinventoryproject.model.Users;
import com.fusemachineinventoryproject.service.ItemService;
import com.fusemachineinventoryproject.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/item")
public class ItemController extends AbstractController<Item, ItemDTO, ItemService, ItemMapper> {
    private ItemService service;
    private UserService userService;

    public ItemController(ItemService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @PostMapping(value = "{userId}", produces = "application/json", consumes = "application/json")
    public RestResponse createItem(@PathVariable("userId") Long userId, @RequestBody ItemDTO itemDTO) {
        Users users = userService.findOne(userId);
        if (users != null) {
            return RestResponse.builder().body(service.createItem(userId, itemDTO)).build();
        } else {
            return RestResponse.builder().error("Cannot find user with Id" + userId).build();
        }
    }

    @PutMapping(value = "/{itemId}", produces = "application/json", consumes = "application/json")
    public RestResponse updateItem(@PathVariable("itemId") Long itemId, @RequestBody ItemDTO itemDTO) throws NoSuchFieldException {
        Item item = service.findOne(itemId);
        if (item != null) {
            return RestResponse.builder().body(service.updateItem(itemId, itemDTO)).build();
        } else {
            return RestResponse.builder().error("Cannot find Item with Id" + itemId).build();
        }
    }

    @DeleteMapping("/{itemId}")
    public RestResponse deleteItem(@PathVariable("itemId") Long itemId) {
        Item itemFound = service.findOne(itemId);
        if (itemFound != null) {
            service.deleteById(itemId);
            return RestResponse.builder().message("Item deleted successfully.").build();
        }
        return RestResponse.builder().error("Cannot find Item with Id" + itemId).build();
    }

    @GetMapping(produces = "application/json")
    public RestResponse itemList() {
        return RestResponse.builder().body(service.itemList().stream().collect(Collectors.toList())).build();
    }
}
