package com.fusemachineinventoryproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UsersDTO extends BaseDTO {
    private String username;
    private String password;
    private Integer age;
    private String phoneNumber;
    private String firstName;
    private String lastName;
    private List<ItemDTO> items;
}
