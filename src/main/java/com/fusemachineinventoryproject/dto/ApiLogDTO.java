package com.fusemachineinventoryproject.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiLogDTO extends BaseDTO {

    private String message;
    private String apiPathName;
}
