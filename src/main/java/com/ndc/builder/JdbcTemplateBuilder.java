package com.ndc.builder;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;

@Service
public class JdbcTemplateBuilder {

    public JdbcTemplate getJdbcTemplate(DatabaseType databaseType) {
        //can pass database config from top level
        if(databaseType.equals(DatabaseType.MYSQL)) return new JdbcTemplate(mysqlDataSource());
        throw new IllegalArgumentException("Unsupported database type: " + databaseType);
    }

    private DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/chinook?useSSL=false&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("sadmin");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return dataSource;
    }

}
