package com.ndc.controller;

import com.ndc.request.QueryRequest;
import com.ndc.response.QueryResponse;
import com.ndc.service.QueryExecutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/")
public class MyRestController {

    @Autowired
    private QueryExecutionService queryExecutionService;

    @GetMapping("/test")
    public String test() {
        return "Hello, World!";
    }

    @PostMapping("/query")
    public QueryResponse executeQuery(@RequestBody QueryRequest queryRequest) {
        return queryExecutionService.executeQuery(queryRequest);
    }


}
