package com.fusemachineinventoryproject.controller;

import com.fusemachineinventoryproject.dto.UsersDTO;
import com.fusemachineinventoryproject.mapper.UserMapper;
import com.fusemachineinventoryproject.model.ApiLog;
import com.fusemachineinventoryproject.model.RestResponse;
import com.fusemachineinventoryproject.model.Users;
import com.fusemachineinventoryproject.repository.ApiLogRepository;
import com.fusemachineinventoryproject.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
@Api(value = "Users", tags = {"Users"})
public class UserController extends AbstractController<Users, UsersDTO, UserService, UserMapper> {
    private UserService userService;
    private ApiLogRepository apiRepository;

    @Autowired
    public UserController(UserService userService, ApiLogRepository apiRepository) {
        this.userService = userService;
        this.apiRepository = apiRepository;
    }

    @PostMapping(produces = "application/json")
    public RestResponse createUsers(@RequestBody UsersDTO usersDTO) {
        ApiLog apiLog = new ApiLog();
        apiLog.setMessage(usersDTO.getFirstName() + " saved successfully");
        apiLog.setApiPathName("/users");
        addApiLog(apiLog);
        return RestResponse.builder().body(userService.createUsers(usersDTO)).build();

    }

    private void addApiLog(ApiLog apiLog) {
        apiLog.setCreatedDate(new Date());

    }

    @PutMapping(value = "/{id}", produces = "application/json", consumes = "application/json")
    public RestResponse updateUsers(@PathVariable("id") String userId, @RequestBody UsersDTO usersDTO) throws NoSuchFieldException {
        Users users = userService.findOne(userId);
        if (users == null) {
            return RestResponse.builder().error("Cannot find users with Id" + userId,400).build();
        }
        ApiLog apiLog = new ApiLog();
        apiLog.setMessage(users.getFirstName() + " updated successfully");
        apiLog.setApiPathName("/users/{id}");
        addApiLog(apiLog);
        return RestResponse.builder().body(userService.updateUsers(userId, usersDTO)).build();
    }

    @DeleteMapping("/{userId}")
    public RestResponse deleteUser(@PathVariable("userId") String userId) {
        Users users = userService.findOne(userId);
        if (users != null) {
            ApiLog apiLog = new ApiLog();
            apiLog.setMessage(users.getFirstName() + " deleted successfully");
            apiLog.setApiPathName("/users/{userId}");
            addApiLog(apiLog);
            userService.deleteById(userId);
            return RestResponse.builder().message("User delete successfully.").build();
        } else {
            return RestResponse.builder().error("Cannot find User with Id" + userId,400).build();
        }
    }

    @GetMapping(produces = "application/json")
    public RestResponse usersList() {
        return RestResponse.builder().body(userService.userList().stream().collect(Collectors.toList())).build();
    }
}
