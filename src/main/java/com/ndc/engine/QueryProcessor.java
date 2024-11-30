package com.ndc.engine;

import com.ndc.request.QueryRequest;

public interface QueryProcessor {
    String generateSQL(QueryRequest request);
}
