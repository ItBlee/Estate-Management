package com.itblee.constant;

public interface SystemConstant {
    /*Spring security 4: ROLE_ADMIN, Spring security 3 not required*/
    String ADMIN_ROLE = "ROLE_MANAGER";
    /*Spring security 4: ROLE_ADMIN, Spring security 3 not required*/
    String USER_ROLE = "ROLE_STAFF";

    String MANAGER = "MANAGER";
    String STAFF = "STAFF";

    String ADMIN_HOME = "/admin/home";
    String MODEL = "model";

    String PASSWORD_DEFAULT = "123456";

    String BUILDING_AVATAR_PATH = "building/avatars/";
    String LIST_TABLE_ID = "tableList";
}
