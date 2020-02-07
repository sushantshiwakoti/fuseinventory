package com.fusemachineinventoryproject.mapper;

import com.fusemachineinventoryproject.dto.UsersDTO;
import com.fusemachineinventoryproject.model.Users;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-02-08T00:41:15+0545",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public UsersDTO toDTO(Users users) {
        if ( users == null ) {
            return null;
        }

        UsersDTO usersDTO = new UsersDTO();

        usersDTO.setId( users.getId() );
        usersDTO.setCreatedDate( users.getCreatedDate() );
        usersDTO.setUsername( users.getUsername() );
        usersDTO.setPassword( users.getPassword() );
        usersDTO.setAge( users.getAge() );
        usersDTO.setPhoneNumber( users.getPhoneNumber() );
        usersDTO.setFirstName( users.getFirstName() );
        usersDTO.setLastName( users.getLastName() );
        usersDTO.setItems( itemMapper.toDTOList( users.getItems() ) );

        return usersDTO;
    }

    @Override
    public List<UsersDTO> toDTOList(List<Users> users) {
        if ( users == null ) {
            return null;
        }

        List<UsersDTO> list = new ArrayList<UsersDTO>( users.size() );
        for ( Users users1 : users ) {
            list.add( toDTO( users1 ) );
        }

        return list;
    }
}
