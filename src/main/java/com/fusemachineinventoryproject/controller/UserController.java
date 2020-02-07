package com.fusemachineinventoryproject.controller;

import com.fusemachineinventoryproject.dto.UsersDTO;
import com.fusemachineinventoryproject.mapper.UserMapper;
import com.fusemachineinventoryproject.model.RestResponse;
import com.fusemachineinventoryproject.model.Users;
import com.fusemachineinventoryproject.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@Api(value = "Users", tags = {"Users"})
public class UserController extends AbstractController<Users, UsersDTO, UserService, UserMapper> {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(produces = "application/json")
    public RestResponse createUsers(@RequestBody UsersDTO usersDTO) {

        return RestResponse.builder().body(userService.createUsers(usersDTO)).build();

    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public RestResponse updateUsers(@PathVariable("id") Long userId, @RequestBody UsersDTO usersDTO) throws NoSuchFieldException {
        Users users = userService.findOne(userId);
        if (users == null) {
            return RestResponse.builder().body("Cannot find users with Id" + userId).build();
        }
        return RestResponse.builder().body(userService.updateUsers(userId, usersDTO)).build();
    }

    @DeleteMapping("/{id}")
    public RestResponse deleteUser(@PathVariable("userId") Long userId) {
        Users users = userService.findOne(userId);
        if (users != null) {
            userService.deleteById(userId);
            return RestResponse.builder().message("User delete successfully.").build();
        } else {
            return RestResponse.builder().error("Cannot find User with Id" + userId).build();
        }
    }

    @GetMapping(produces = "application/json")
    public RestResponse usersList() {
        return RestResponse.builder().body(userService.userList().stream().collect(Collectors.toList())).build();
    }
}
