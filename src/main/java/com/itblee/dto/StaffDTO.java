package com.itblee.dto;

import java.io.Serializable;

public class StaffDTO implements Serializable {
    private Long id;
    private String fullName;
    private String checked;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getChecked() {
        return checked;
    }

    public void setChecked(String checked) {
        this.checked = checked;
    }

    public void check() {
        checked = "checked";
    }
}
