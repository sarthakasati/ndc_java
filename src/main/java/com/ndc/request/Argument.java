package com.ndc.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Argument {
    @JsonProperty("type")
    private String type;

    @JsonProperty("value")
    private Object value;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}

