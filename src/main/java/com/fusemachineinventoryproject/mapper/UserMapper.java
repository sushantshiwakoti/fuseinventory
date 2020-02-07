package com.fusemachineinventoryproject.mapper;

import com.fusemachineinventoryproject.dto.UsersDTO;
import com.fusemachineinventoryproject.model.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;


@Mapper(componentModel = "spring", uses = ItemMapper.class)
public interface UserMapper extends CommonMapper<Users, UsersDTO> {
    @Mappings({})
    @Override
    UsersDTO toDTO(Users users);


    @Mappings({})
    List<UsersDTO> toDTOList(List<Users> users);
}
