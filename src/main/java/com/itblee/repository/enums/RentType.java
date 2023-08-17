package com.itblee.repository.enums;

public enum RentType {
    TANG_TRET("Tầng trệt"),
    NGUYEN_CAN("Nguyên Căn"),
    NOI_THAT("Nội thất");

    private final String name;

    RentType(String name) {
        this.name = name;
    }

    public String getCode() {
        return name();
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
