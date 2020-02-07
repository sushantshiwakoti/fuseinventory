package com.fusemachineinventoryproject.mapper;

import com.fusemachineinventoryproject.dto.ApiLogDTO;
import com.fusemachineinventoryproject.model.ApiLog;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2020-02-08T00:41:16+0545",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_231 (Oracle Corporation)"
)
@Component
public class ApiLogMapperImpl implements ApiLogMapper {

    @Override
    public List<ApiLogDTO> toDTOList(List<ApiLog> apiLogs) {
        if ( apiLogs == null ) {
            return null;
        }

        List<ApiLogDTO> list = new ArrayList<ApiLogDTO>( apiLogs.size() );
        for ( ApiLog apiLog : apiLogs ) {
            list.add( toDTO( apiLog ) );
        }

        return list;
    }

    @Override
    public ApiLogDTO toDTO(ApiLog apiLog) {
        if ( apiLog == null ) {
            return null;
        }

        ApiLogDTO apiLogDTO = new ApiLogDTO();

        apiLogDTO.setId( apiLog.getId() );
        apiLogDTO.setCreatedDate( apiLog.getCreatedDate() );
        apiLogDTO.setMessage( apiLog.getMessage() );
        apiLogDTO.setApiPathName( apiLog.getApiPathName() );

        return apiLogDTO;
    }
}
