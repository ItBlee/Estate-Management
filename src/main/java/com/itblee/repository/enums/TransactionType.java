package com.itblee.repository.enums;

public enum TransactionType {
    CSKH("QUÁ TRÌNH CSKH"),
    VIEW("DẪN ĐI XEM");

    private final String name;

    TransactionType(String name) {
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
