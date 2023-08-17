package com.itblee.repository.sqlbuilder.key;

import com.itblee.constant.SystemConstant;
import com.itblee.repository.sqlbuilder.SqlKey;
import com.itblee.repository.sqlbuilder.SqlStatement;
import com.itblee.repository.sqlbuilder.model.SqlJoin;
import com.itblee.repository.sqlbuilder.model.SqlQuery;
import com.itblee.utils.StringUtils;

import java.util.List;

public enum CustomerKey implements SqlKey {

    ID ("id", Long.class, SqlQuery.builder()
        .from("customer")
        .where("customer.id").build()
    ),

    FULL_NAME ("fullName", String.class, SqlQuery.builder()
        .from("customer")
        .where("customer.fullname").build()
    ),

    PHONE ("phone", String.class, SqlQuery.builder()
            .from("customer")
            .where("customer.phone").build()
    ),

    EMAIL ("email", String.class, SqlQuery.builder()
            .from("customer")
            .where("customer.email").build()
    ),

    STATUS ("status", Integer[].class, SqlQuery.builder()
            .from("customer")
            .where("customer.status").build()
    ),

    STAFF ("staffId", List.class, SqlQuery.builder()
        .from("customer")
        .joinWith(
            SqlJoin.builder()
            .type(SqlJoin.Type.LEFT_JOIN)
            .join("assignmentcustomer")
            .on("customer.id = assignmentcustomer.customerid").done(),

            SqlJoin.builder()
            .type(SqlJoin.Type.LEFT_JOIN)
            .join(
                SqlQuery.builder()
                .select("user.id")
                .from("user")
                .joinWith(
                    SqlJoin.builder()
                    .type(SqlJoin.Type.LEFT_JOIN)
                    .join("user_role")
                    .on("user.id = user_role.userid").done(),

                    SqlJoin.builder()
                    .type(SqlJoin.Type.LEFT_JOIN)
                    .join("role")
                    .on("role.id = user_role.roleid").done()
                )
                .where("role.code = " + SystemConstant.STAFF)
                .as("user").build()
            )
            .on("user.id = assignmentcustomer.staffid").done()
        )
        .where("user.id").build()
    ),

    //SCOPE
    ALL (SqlQuery.builder()
        .select("customer.*")
        .from("customer")
        .groupBy("customer.id").build()
    ),

    //SCOPE
    COUNT (SqlQuery.builder()
        .select("count(DISTINCT customer.id) AS count")
        .from("customer").build()
    );

    private final String param;
    private final Class<?> fieldType;
    private final SqlStatement statement;

    CustomerKey(SqlStatement statement) {
        if (statement == null)
            throw new IllegalArgumentException();
        this.param = "";
        this.fieldType = Object.class;
        this.statement = statement;
    }

    CustomerKey(String param, Class<?> fieldType, SqlStatement statement) {
        StringUtils.requireNonBlank(param);
        if (fieldType == null)
            throw new IllegalArgumentException();
        this.param = param;
        this.fieldType = fieldType;
        this.statement = statement;
    }

    @Override
    public SqlStatement getStatement() {
        return statement;
    }

    @Override
    public String getParamName() {
        return param;
    }

    @Override
    public Class<?> getType() {
        return fieldType;
    }

    @Override
    public boolean isScope() {
        return StringUtils.isBlank(getParamName())
                && getType() == Object.class
                && statement != null;
    }

}
