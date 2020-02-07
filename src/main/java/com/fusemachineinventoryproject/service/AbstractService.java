package com.fusemachineinventoryproject.service;


import com.fusemachineinventoryproject.repository.ExtendedRepository;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.*;

@Slf4j
public abstract class AbstractService<T, ID, Repo extends ExtendedRepository<T, ID>> {
    protected Repo repository;




    public AbstractService(Repo repository) {
        this.repository = repository;
    }

    protected Class<T> getGenericTypeClass() {
        return (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_UNCOMMITTED)
    public T insert(T t) {
        return repository.save(t);
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public List<T> insertAll(Iterable<T> t) {
        return Lists.newArrayList(repository.saveAll(t));
    }

    @Transactional()
    public List<T> findAll() {
        return (List) repository.findAll();
    }

    @Transactional
    public void deleteAll(Iterable<T> all) {
        repository.deleteAll(all);
    }


    @Transactional
    public void deleteById(ID id) {
        repository.deleteById(id);
    }


    @Transactional
    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    @Transactional
    public T findOne(String id) {
        Optional<T> byId = repository.findById((ID) id);
        return byId.isPresent() ? byId.get() : null;
    }


}
