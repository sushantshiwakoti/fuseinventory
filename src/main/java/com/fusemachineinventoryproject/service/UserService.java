package com.fusemachineinventoryproject.service;

import com.fusemachineinventoryproject.dto.UsersDTO;

import com.fusemachineinventoryproject.mapper.ItemMapper;
import com.fusemachineinventoryproject.mapper.UserMapper;
import com.fusemachineinventoryproject.model.Users;

import com.fusemachineinventoryproject.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService extends AbstractService<Users, Long, UserRepository> implements CommonServiceMethods<Users, Long> {


    private UserMapper userMapper;

    private ItemMapper itemMapper;

    public UserService(UserRepository repository, UserMapper userMapper, ItemMapper itemMapper) {
        super(repository);
        this.itemMapper = itemMapper;
        this.userMapper = userMapper;
    }

    public List<UsersDTO> userList() {
        return userMapper.toDTOList(repository.findAll());
    }

    public UsersDTO createUsers(UsersDTO usersDTO) {
        Users users = new Users();
        return addUpdateUsers(users, usersDTO);

    }

    public UsersDTO updateUsers(Long userId, UsersDTO usersDTO) throws NoSuchFieldException {
        Optional<Users> users = repository.findById(userId);
        if (users.isPresent()) {
            Users existingUser = users.get();
            return addUpdateUsers(existingUser, usersDTO);
        } else {
            throw new NoSuchFieldException("Cannot find user with Id" + userId);
        }


    }

    public UsersDTO addUpdateUsers(Users users, UsersDTO usersDTO) {
        users.setUsername(usersDTO.getUsername());
        users.setPassword(usersDTO.getPassword());
        users.setFirstName(usersDTO.getFirstName());
        users.setLastName(usersDTO.getLastName());
        users.setAge(usersDTO.getAge());
        users.setPhoneNumber(usersDTO.getPhoneNumber());
        if (usersDTO.getItems() != null || usersDTO.getItems().size() > 0) {
            users.setItems(itemMapper.fromDTO(usersDTO.getItems()));
        }
        return userMapper.toDTO(repository.save(users));
    }

}
