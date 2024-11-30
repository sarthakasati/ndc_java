package com.ndc.response;

import java.util.List;
import java.util.Map;

public class QueryResponse {
    private List<Map<String, Object>> rows;

    public List<Map<String, Object>> getRows() {
        return rows;
    }

    public void setRows(List<Map<String, Object>> rows) {
        this.rows = rows;
    }
}
