package com.fusemachineinventoryproject.mapper;

public interface CommonMapper<T, DTO> {
    DTO toDTO(T t);
}
