package com.fusemachineinventoryproject.model;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "api_log")
public class ApiLog extends BaseModel<String> {

    private String message;
    private String apiPathName;

}
