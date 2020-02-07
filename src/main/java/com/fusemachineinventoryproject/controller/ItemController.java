package com.fusemachineinventoryproject.controller;

import com.fusemachineinventoryproject.dto.ItemDTO;
import com.fusemachineinventoryproject.mapper.ItemMapper;
import com.fusemachineinventoryproject.model.ApiLog;
import com.fusemachineinventoryproject.model.Item;
import com.fusemachineinventoryproject.model.RestResponse;
import com.fusemachineinventoryproject.model.Users;
import com.fusemachineinventoryproject.repository.ApiLogRepository;

import com.fusemachineinventoryproject.service.ItemService;
import com.fusemachineinventoryproject.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/item")
public class ItemController extends AbstractController<Item, ItemDTO, ItemService, ItemMapper> {
    private ItemService service;
    private UserService userService;
    private ApiLogRepository apiLogRepository;

    public ItemController(ItemService service, UserService userService, ApiLogRepository apiLogRepository) {
        this.service = service;
        this.userService = userService;
        this.apiLogRepository = apiLogRepository;
    }

    @PostMapping(value = "/{userId}", produces = "application/json", consumes = "application/json")
    public RestResponse createItem(@PathVariable("userId") String userId, @RequestBody ItemDTO itemDTO) {
        Users users = userService.findOne(userId);
        if (users != null) {
            ApiLog apiLog = new ApiLog();
            apiLog.setMessage(itemDTO.getItemName() + " saved successfully by " + users.getFirstName());
            apiLog.setApiPathName("/item/{userId}");
            addApiLog(apiLog);
            return RestResponse.builder().body(service.createItem(userId, itemDTO)).build();
        } else {
            return RestResponse.builder().error("Cannot find user with Id" + userId).build();
        }
    }

    @PutMapping(value = "/{itemId}", produces = "application/json", consumes = "application/json")
    public RestResponse updateItem(@PathVariable("itemId") String itemId, @RequestBody ItemDTO itemDTO) throws NoSuchFieldException {
        Item item = service.findOne(itemId);
        if (item != null) {
            ApiLog apiLog = new ApiLog();
            apiLog.setMessage(item.getItemName() + " was updated successfully by " + item.getUsers().getFirstName());
            apiLog.setApiPathName("/item/{itemId}");
            addApiLog(apiLog);
            return RestResponse.builder().body(service.updateItem(itemId, itemDTO)).build();
        } else {
            return RestResponse.builder().error("Cannot find Item with Id" + itemId).build();
        }
    }

    @DeleteMapping("/{itemId}")
    public RestResponse deleteItem(@PathVariable("itemId") String itemId) {
        Item itemFound = service.findOne(itemId);
        if (itemFound != null) {
            ApiLog apiLog = new ApiLog();
            apiLog.setMessage(itemFound.getItemName() + " saved successfully by " + itemFound.getUsers().getFirstName());
            apiLog.setApiPathName("/item/{itemId}");
            addApiLog(apiLog);
            service.deleteById(itemId);
            return RestResponse.builder().message("Item deleted successfully.").build();
        }
        return RestResponse.builder().error("Cannot find Item with Id" + itemId).build();
    }

    @GetMapping(produces = "application/json")
    public RestResponse itemList(@RequestParam Integer pageNumber, @RequestParam Integer pageSize) {
        return RestResponse.builder().body(service.itemList(pageNumber, pageSize).stream().collect(Collectors.toList())).build();
    }

    @GetMapping(value = "/itemName/{itemName}", produces = "application/json")
    public RestResponse findItemListByName(@PathVariable("itemName") String itemName) {
        return RestResponse.builder().body(service.findItemListByName(itemName).stream().collect(Collectors.toList())).build();
    }

    @GetMapping(value = "/itemType/{type}", produces = "application/json")
    public RestResponse findItemListByType(@PathVariable("type") String type) {
        return RestResponse.builder().body(service.findItemListByType(type).stream().collect(Collectors.toList())).build();

    }

    @GetMapping(value = "/itemQty/{quantity}", produces = "application/json")
    public RestResponse findItemListByQuantity(@PathVariable("quantity") Integer quantity) {
        return RestResponse.builder().body(service.findItemListByQuantity(quantity).stream().collect(Collectors.toList())).build();
    }

    public void addApiLog(ApiLog apiLog) {
        apiLog.setCreatedDate(new Date());
        apiLogRepository.save(apiLog);

    }
}
