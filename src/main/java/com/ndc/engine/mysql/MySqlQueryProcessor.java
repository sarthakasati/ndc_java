package com.ndc.engine.mysql;

import com.ndc.engine.QueryProcessor;
import com.ndc.request.*;

import java.util.List;
import java.util.Map;

public class MySqlQueryProcessor implements QueryProcessor {

    @Override
    public String generateSQL(QueryRequest request) {
        MySqlBuilder mySqlBuilder = new MySqlBuilder();

        for (Map.Entry<String, Field> entry : request.getQuery().getFields().entrySet()) {
            if (entry.getValue().getType().equals("column")) {
                mySqlBuilder.select(entry.getValue().getColumn());
            }
        }

        mySqlBuilder.from(request.getCollection());

        if (request.getQuery().getPredicate() != null) {
            String whereClause = processPredicate(request.getQuery().getPredicate());
            mySqlBuilder.where(whereClause);
        }

        if (request.getQuery().getOrderBy() != null) {
            for (OrderElement element : request.getQuery().getOrderBy().getElements()) {
                mySqlBuilder.orderBy(element.getTarget().getName(), element.getOrderDirection());
            }
        }

        if (request.getQuery().getLimit() != null) {
            mySqlBuilder.limit(request.getQuery().getLimit());
        }
        if (request.getQuery().getOffset() != null) {
            mySqlBuilder.offset(request.getQuery().getOffset());
        }

        return mySqlBuilder.build();
    }


    private String processPredicate(Predicate predicate) {
        if (predicate == null) {
            return "";
        }

        switch (predicate.getType()) {
            case "binary_comparison_operator":
                return processBinaryComparisonOperator(predicate);
            case "and":
                return processLogicalOperator(predicate, "AND");
            case "or":
                return processLogicalOperator(predicate, "OR");
            default:
                throw new IllegalArgumentException("Unsupported predicate type: " + predicate.getType());
        }
    }

    private String processBinaryComparisonOperator(Predicate predicate) {
        String column = processColumn(predicate.getColumn());
        String operator = processOperator(predicate.getOperator());
        String value = processScalarValue(predicate.getValue());

        return column + " " + operator + " " + value;
    }

    private String processLogicalOperator(Predicate predicate, String logicalOperator) {
        StringBuilder sqlBuilder = new StringBuilder();
        sqlBuilder.append("(");
        for (int i = 0; i < predicate.getExpressions().size(); i++) {
            Predicate expression = (Predicate) predicate.getExpressions().get(i); // Get each sub-expression
            sqlBuilder.append(processPredicate(expression)); // Recursively process sub-expressions
            if (i < predicate.getExpressions().size() - 1) {
                sqlBuilder.append(" " + logicalOperator + " ");
            }
        }
        sqlBuilder.append(")");
        return sqlBuilder.toString();
    }

    private String processColumn(Column column) {
        if (column == null || column.getName() == null) {
            throw new IllegalArgumentException("Column name cannot be null");
        }
        return column.getName();
    }

    private String processOperator(String operator) {
        switch (operator) {
            case "_eq":
                return "=";
            case "_lt":
                return "<";
            case "_gt":
                return ">";
            case "_lte":
                return "<=";
            case "_gte":
                return ">=";
            case "_in":
                return "IN";
            case "_like":
                return "LIKE";
            default:
                throw new IllegalArgumentException("Unsupported operator: " + operator);
        }
    }

    private String processScalarValue(Value scalarValue) {
        if (scalarValue == null || scalarValue.getValue() == null) {
            throw new IllegalArgumentException("Scalar value cannot be null");
        }

        if (scalarValue.getValue() instanceof String) {
            return "'" + scalarValue.getValue() + "'"; // Wrap strings in quotes
        } else if (scalarValue.getValue() instanceof Number) {
            return scalarValue.getValue().toString(); // Directly use numeric values
        } else if (scalarValue.getValue() instanceof List) {
            List<?> values = (List<?>) scalarValue.getValue();
            StringBuilder inClause = new StringBuilder("(");
            for (int i = 0; i < values.size(); i++) {
                inClause.append(processScalarValue(new Value("scalar", values.get(i))));
                if (i < values.size() - 1) {
                    inClause.append(", ");
                }
            }
            inClause.append(")");
            return inClause.toString();
        } else {
            throw new IllegalArgumentException("Unsupported scalar value type: " + scalarValue.getValue().getClass());
        }
    }
}

