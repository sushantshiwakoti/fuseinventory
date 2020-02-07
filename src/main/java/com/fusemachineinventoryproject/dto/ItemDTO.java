package com.fusemachineinventoryproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fusemachineinventoryproject.model.ItemType;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemDTO {
    private String itemName;
    private String itemCode;
    private Double availableQty;
    private ItemType type;

}
