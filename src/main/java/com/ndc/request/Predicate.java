package com.ndc.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class Predicate {
    @JsonProperty("type")
    private String type;

    @JsonProperty("column")
    private Column column;

    @JsonProperty("operator")
    private String operator;

    @JsonProperty("value")
    private Value value;

    @JsonProperty("expressions")
    private List<Predicate> expressions;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Column getColumn() {
        return column;
    }

    public void setColumn(Column column) {
        this.column = column;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public List<Predicate> getExpressions() {
        return expressions;
    }

    public void setExpressions(List<Predicate> expressions) {
        this.expressions = expressions;
    }
}

