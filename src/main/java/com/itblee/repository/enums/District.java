package com.itblee.repository.enums;

public enum District {
    QUAN_1("Quận 1"),
    QUAN_2("Quận 2"),
    QUAN_4("Quận 4");

    private final String name;

    District(String name) {
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
