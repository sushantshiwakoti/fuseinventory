package com.fusemachineinventoryproject.mapper;

import com.fusemachineinventoryproject.dto.ItemDTO;
import com.fusemachineinventoryproject.model.Item;
import org.mapstruct.Mapper;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ItemMapper extends CommonMapper<Item, ItemDTO> {


    @Mappings({})
    @Override
    ItemDTO toDTO(Item item);


    @Mappings({})
    List<Item> fromDTO(List<ItemDTO> itemDTOS);

    @Mappings({})
    List<ItemDTO> toDTOList(List<Item> items);

}
