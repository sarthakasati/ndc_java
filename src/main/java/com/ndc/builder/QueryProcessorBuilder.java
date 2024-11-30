package com.ndc.builder;

import com.ndc.engine.QueryProcessor;
import com.ndc.engine.mysql.MySqlQueryProcessor;
import org.springframework.stereotype.Component;

@Component
public class QueryProcessorBuilder {

    public QueryProcessor getQueryProcessor(DatabaseType databaseType) {
        if(databaseType.equals(DatabaseType.MYSQL))  return new MySqlQueryProcessor();
        throw new IllegalArgumentException("Unsupported database type: " + databaseType);
    }

}
