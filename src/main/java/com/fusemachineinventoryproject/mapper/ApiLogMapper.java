package com.fusemachineinventoryproject.mapper;


import com.fusemachineinventoryproject.dto.ApiLogDTO;
import com.fusemachineinventoryproject.model.ApiLog;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ApiLogMapper extends CommonMapper<ApiLog, ApiLogDTO> {
    @Override
    List<ApiLogDTO> toDTOList(List<ApiLog> apiLogs);

    @Override
    ApiLogDTO toDTO(ApiLog apiLog);
}
