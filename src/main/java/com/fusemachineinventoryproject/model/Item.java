package com.fusemachineinventoryproject.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;



@Data
@Document(collection = "item")
public class Item extends BaseModel<String> {

    @Field(name = "item_name")
    private String itemName;
    @Field(name = "item_code")
    private String itemCode;
    @Field(name = "available_qty")
    private Integer availableQty;
    @Field(name = "type")
    private ItemType type;
    private Users users;
}
