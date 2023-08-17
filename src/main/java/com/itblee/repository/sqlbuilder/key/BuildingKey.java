package com.itblee.repository.sqlbuilder.key;

import com.itblee.constant.SystemConstant;
import com.itblee.repository.sqlbuilder.SqlKey;
import com.itblee.repository.sqlbuilder.SqlStatement;
import com.itblee.repository.sqlbuilder.model.*;
import com.itblee.utils.StringUtils;

import java.util.List;

public enum BuildingKey implements SqlKey {

    ID ("id", Long.class, SqlQuery.builder()
        .from("building")
        .where("building.id").build()
    ),

    NAME ("name", String.class, SqlQuery.builder()
        .from("building")
        .where("building.name").build()
    ),

    DISTRICT ("districtCode", Code.class, SqlQuery.builder()
        .from("building")
        .where("building.district").build()
    ),

    WARD ("ward", String.class, SqlQuery.builder()
        .from("building")
        .where("building.ward").build()
    ),

    LEVEL ("level", String.class, SqlQuery.builder()
        .from("building")
        .where("building.level").build()
    ),

    STREET ("street", String.class, SqlQuery.builder()
        .from("building")
        .where("building.street").build()
    ),

    DIRECTION ("direction", String.class, SqlQuery.builder()
        .from("building")
        .where("building.direction").build()
    ),

    RENT_PRICE("rentPrice", Range.class, SqlQuery.builder()
        .from("building")
        .where("building.rentprice").build()
    ),

    FLOOR_AREA ("floorArea", Integer.class, SqlQuery.builder()
        .from("building")
        .where("building.floorarea").build()
    ),

    MANAGER_NAME ("managerName", String.class, SqlQuery.builder()
        .from("building")
        .where("building.managername").build()
    ),

    MANAGER_PHONE ("managerPhone", String.class, SqlQuery.builder()
        .from("building")
        .where("building.managerphone").build()
    ),

    NUMBER_OF_BASEMENT ("numberOfBasement", Integer.class, SqlQuery.builder()
        .from("building")
        .where("building.numberofbasement").build()
    ),

    /*RENT_AREA ("rentarea", Range.class, SqlQuery.builder()
        .select("rentarea.id AS \"rentarea.Id\"",
                "rentarea.value AS \"rentarea.Value\"")
        .from("building")
        .joinWith(SqlJoin.builder()
            .type(SqlJoin.Type.INNER_JOIN)
            .join("rentarea")
            .on("building.id = rentarea.buildingid").done())
        .where("rentarea.value").build()
    ),*/

    RENT_AREA ("rentArea", Range.class, SqlQuery.builder()
            .from("building")
            .where(SqlQuery.builder()
                    .select("*")
                    .from("rentarea")
                    .whereWithoutValue("rentarea.buildingid = building.id")
                    .where("rentarea.value")
                    .build()
            ).build()
    ),

    RENT_TYPES ("rentTypeCodes", CodeSet.class, SqlQuery.builder()
        .from("building")
        .where("building.type").build()
    ),

    STAFF ("staffId", List.class, SqlQuery.builder()
        .from("building")
        .joinWith(
            SqlJoin.builder()
            .type(SqlJoin.Type.LEFT_JOIN)
            .join("assignmentbuilding")
            .on("building.id = assignmentbuilding.buildingid").done(),

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
            .on("user.id = assignmentbuilding.staffid").done()
        )
        .where("user.id").build()
    ),

    //SCOPE
    ALL (SqlQuery.builder()
        .select("building.*")
        .from("building")
        .groupBy("building.id").build()
    ),

    //SCOPE
    COUNT (SqlQuery.builder()
        .select("count(DISTINCT building.id) AS count")
        .from("building").build()
    );

    private final String param;
    private final Class<?> fieldType;
    private final SqlStatement statement;

    BuildingKey(SqlStatement statement) {
        if (statement == null)
            throw new IllegalArgumentException();
        this.param = "";
        this.fieldType = Object.class;
        this.statement = statement;
    }

    BuildingKey(String param, Class<?> fieldType, SqlStatement statement) {
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
