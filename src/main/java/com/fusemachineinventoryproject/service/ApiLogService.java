package com.fusemachineinventoryproject.service;

import com.fusemachineinventoryproject.dto.ApiLogDTO;
import com.fusemachineinventoryproject.mapper.ApiLogMapper;
import com.fusemachineinventoryproject.model.ApiLog;
import com.fusemachineinventoryproject.repository.ApiLogRepository;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApiLogService extends AbstractService<ApiLog, String, ApiLogRepository> implements CommonServiceMethods<ApiLog, String> {


    private ApiLogMapper logMapper;

    public ApiLogService(ApiLogRepository repository, ApiLogMapper logMapper) {
        super(repository);
        this.logMapper = logMapper;
    }

    public List<ApiLogDTO> apiLogList(String sortBy) {
        return logMapper.toDTOList(repository.findAll(Sort.by(sortBy).descending()));
    }
}
