package com.ndc.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Field {
    @JsonProperty("type")
    private String type;

    @JsonProperty("column")
    private String column;

    @JsonProperty("fields")
    private Map<String, Field> fields;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getColumn() {
        return column;
    }

    public void setColumn(String column) {
        this.column = column;
    }

    public Map<String, Field> getFields() {
        return fields;
    }

    public void setFields(Map<String, Field> fields) {
        this.fields = fields;
    }
}

