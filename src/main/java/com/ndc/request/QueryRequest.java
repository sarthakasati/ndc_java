package com.ndc.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Map;

public class QueryRequest {
    @JsonProperty("collection")
    private String collection;

    @JsonProperty("query")
    private Query query;

    @JsonProperty("arguments")
    private Map<String, Argument> arguments;

    @JsonProperty("collection_relationships")
    private Map<String, Relationship> collectionRelationships;

    public String getCollection() {
        return collection;
    }

    public void setCollection(String collection) {
        this.collection = collection;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public Map<String, Argument> getArguments() {
        return arguments;
    }

    public void setArguments(Map<String, Argument> arguments) {
        this.arguments = arguments;
    }

    public Map<String, Relationship> getCollectionRelationships() {
        return collectionRelationships;
    }

    public void setCollectionRelationships(Map<String, Relationship> collectionRelationships) {
        this.collectionRelationships = collectionRelationships;
    }
}


