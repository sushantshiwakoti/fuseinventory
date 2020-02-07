package com.fusemachineinventoryproject.controller;

import com.fusemachineinventoryproject.mapper.CommonMapper;
import com.fusemachineinventoryproject.service.CommonServiceMethods;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class AbstractController<Entity, DTO, Service extends CommonServiceMethods, Mapper extends CommonMapper> {

    @Autowired
    protected Service service;

    @Autowired
    protected Mapper mapper;
}
