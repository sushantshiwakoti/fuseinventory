package com.fusemachineinventoryproject.repository;


import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface ExtendedRepository<T,ID> extends MongoRepository<T, ID> {
}
