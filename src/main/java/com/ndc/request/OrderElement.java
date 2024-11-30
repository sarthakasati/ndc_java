package com.ndc.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class OrderElement {
    @JsonProperty("order_direction")
    private String orderDirection; // Sorting direction ("asc"/"desc")

    @JsonProperty("target")
    private Column target; // Column to sort by

    public String getOrderDirection() {
        return orderDirection;
    }

    public void setOrderDirection(String orderDirection) {
        this.orderDirection = orderDirection;
    }

    public Column getTarget() {
        return target;
    }

    public void setTarget(Column target) {
        this.target = target;
    }
}

