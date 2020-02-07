package com.fusemachineinventoryproject.service;

import org.springframework.data.domain.Page;

import java.util.List;

public interface CommonServiceMethods<T, ID> {
    List<T> findAll();

    List<T> insertAll(Iterable<T> t);

    T insert(T user);

    T findOne(ID id);

    void deleteById(ID id);

    void deleteAll(Iterable<T> all);

    boolean existsById(ID id);


}
