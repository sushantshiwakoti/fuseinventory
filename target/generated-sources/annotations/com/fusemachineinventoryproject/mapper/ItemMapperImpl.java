package com.fusemachineinventoryproject.mapper;

import com.fusemachineinventoryproject.dto.ItemDTO;
import com.fusemachineinventoryproject.model.Item;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-02-07T19:10:15+0545",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class ItemMapperImpl implements ItemMapper {

    @Override
    public ItemDTO toDTO(Item item) {
        if ( item == null ) {
            return null;
        }

        ItemDTO itemDTO = new ItemDTO();

        itemDTO.setItemName( item.getItemName() );
        itemDTO.setItemCode( item.getItemCode() );
        itemDTO.setAvailableQty( item.getAvailableQty() );
        itemDTO.setType( item.getType() );

        return itemDTO;
    }

    @Override
    public List<Item> fromDTO(List<ItemDTO> itemDTOS) {
        if ( itemDTOS == null ) {
            return null;
        }

        List<Item> list = new ArrayList<Item>( itemDTOS.size() );
        for ( ItemDTO itemDTO : itemDTOS ) {
            list.add( itemDTOToItem( itemDTO ) );
        }

        return list;
    }

    @Override
    public List<ItemDTO> toDTOList(List<Item> items) {
        if ( items == null ) {
            return null;
        }

        List<ItemDTO> list = new ArrayList<ItemDTO>( items.size() );
        for ( Item item : items ) {
            list.add( toDTO( item ) );
        }

        return list;
    }

    protected Item itemDTOToItem(ItemDTO itemDTO) {
        if ( itemDTO == null ) {
            return null;
        }

        Item item = new Item();

        item.setItemName( itemDTO.getItemName() );
        item.setItemCode( itemDTO.getItemCode() );
        item.setAvailableQty( itemDTO.getAvailableQty() );
        item.setType( itemDTO.getType() );

        return item;
    }
}
