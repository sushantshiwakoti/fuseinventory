package com.fusemachineinventoryproject.mapper;

import java.util.List;

public interface CommonMapper<T, DTO> {
    DTO toDTO(T t);

    List<DTO> toDTOList(List<T> ts);


}
