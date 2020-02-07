package com.fusemachineinventoryproject.model;

import lombok.AccessLevel;
import lombok.Data;

import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Data
@Document(collation = "users")
public class Users extends BaseModel {

    @Field(name = "user_name")
    private String username;
    private String password;
    @Field(name = "first_name")
    private String firstName;
    @Field(name = "last_name")
    private String lastName;
    private Integer age;
    @Field(name = "phone_number")
    private String phoneNumber;

    @Setter(AccessLevel.NONE)

    private List<Item> items;

    public void addItems(Item item) {
        if (items == null) {
            items = new ArrayList<>();
        }
        if (item != null && !items.contains(item)) {
            items.add(item);
            item.setUsers(this);
        }
    }

    public void setItems(List<Item> items) {
        if (items != null) {
            for (Item item : items) {
                addItems(item);
            }
        }
    }

}
