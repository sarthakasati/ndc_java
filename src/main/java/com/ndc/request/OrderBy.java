package com.ndc.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public class OrderBy {
    @JsonProperty("elements")
    private List<OrderElement> elements; // Sorting elements

    public List<OrderElement> getElements() {
        return elements;
    }

    public void setElements(List<OrderElement> elements) {
        this.elements = elements;
    }
}
