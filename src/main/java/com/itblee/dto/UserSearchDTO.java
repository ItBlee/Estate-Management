package com.itblee.dto;

import java.io.Serializable;

public class UserSearchDTO implements Serializable {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
