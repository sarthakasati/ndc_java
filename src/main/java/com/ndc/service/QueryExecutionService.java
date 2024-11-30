package com.ndc.service;

import com.ndc.builder.DatabaseType;
import com.ndc.builder.JdbcTemplateBuilder;
import com.ndc.builder.QueryProcessorBuilder;
import com.ndc.engine.QueryProcessor;
import com.ndc.request.QueryRequest;
import com.ndc.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class QueryExecutionService {

    @Autowired
    private JdbcTemplateBuilder jdbcTemplateBuilder;

    @Autowired
    private QueryProcessorBuilder queryProcessorBuilder;

    public QueryResponse executeQuery(QueryRequest queryRequest) {
        QueryProcessor queryProcessor = queryProcessorBuilder.getQueryProcessor(DatabaseType.MYSQL);
        String sql = queryProcessor.generateSQL(queryRequest);
        System.out.println("Generated SQL: " + sql);

        List<Map<String, Object>> resultSet = jdbcTemplateBuilder.getJdbcTemplate(DatabaseType.MYSQL).queryForList(sql);

        QueryResponse queryResponse = mapToQueryResponse(resultSet);

        return queryResponse;
    }

    private QueryResponse mapToQueryResponse(List<Map<String, Object>> resultSet) {
        QueryResponse response = new QueryResponse();
        List<Map<String, Object>> rows = new ArrayList<>();

        for (Map<String, Object> rowMap : resultSet) {
            rows.add(rowMap);
        }
        response.setRows(rows);
        return response;
    }

}
