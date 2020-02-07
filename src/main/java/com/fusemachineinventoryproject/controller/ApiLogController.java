package com.fusemachineinventoryproject.controller;


import com.fusemachineinventoryproject.dto.ApiLogDTO;

import com.fusemachineinventoryproject.mapper.ApiLogMapper;
import com.fusemachineinventoryproject.model.ApiLog;
import com.fusemachineinventoryproject.model.RestResponse;
import com.fusemachineinventoryproject.service.ApiLogService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/apiLog")
public class ApiLogController extends AbstractController<ApiLog, ApiLogDTO, ApiLogService, ApiLogMapper> {
//    private ApiLogService apiService;

//    public ApiLogController(ApiLogService apiService) {
////        this.apiService = apiService;
//    }

    @GetMapping(produces = "application/json")
    public RestResponse apiLogList(@RequestParam  String sortBy) {
        return RestResponse.builder().body(service.apiLogList(sortBy).stream().collect(Collectors.toList())).build();
    }

}
