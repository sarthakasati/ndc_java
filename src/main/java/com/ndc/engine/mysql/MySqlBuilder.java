package com.ndc.engine.mysql;

public class MySqlBuilder {
    private StringBuilder selectClause = new StringBuilder("SELECT ");
    private StringBuilder fromClause = new StringBuilder(" FROM ");
    private StringBuilder joinClause = new StringBuilder();
    private StringBuilder whereClause = new StringBuilder(" WHERE ");
    private StringBuilder orderByClause = new StringBuilder(" ORDER BY ");
    private StringBuilder limitOffsetClause = new StringBuilder();

    public MySqlBuilder select(String column) {
        if (selectClause.length() > 7) selectClause.append(", ");
        selectClause.append(column);
        return this;
    }

    public MySqlBuilder from(String table) {
        fromClause.append(table);
        return this;
    }

    public MySqlBuilder join(String table, String condition) {
        joinClause.append(" JOIN ").append(table).append(" ON ").append(condition);
        return this;
    }

    public MySqlBuilder where(String condition) {
        if (whereClause.length() > 7) whereClause.append(" AND ");
        whereClause.append(condition);
        return this;
    }

    public MySqlBuilder orderBy(String column, String direction) {
        if (orderByClause.length() > 10) orderByClause.append(", ");
        orderByClause.append(column).append(" ").append(direction);
        return this;
    }

    public MySqlBuilder limit(int limit) {
        limitOffsetClause.append(" LIMIT ").append(limit);
        return this;
    }

    public MySqlBuilder offset(int offset) {
        limitOffsetClause.append(" OFFSET ").append(offset);
        return this;
    }

    public String build() {
        return selectClause.append(fromClause).append(joinClause)
                .append(whereClause.length() > 7 ? whereClause : "")
                .append(orderByClause.length() > 10 ? orderByClause : "")
                .append(limitOffsetClause).toString();
    }
}

