package com.lenovo.smarttraffic.bean;

public class User {
    private String UserName;
    private String UserRole;

    public User(String userName, String userRole) {
        UserName = userName;
        UserRole = userRole;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserRole() {
        return UserRole;
    }

    public void setUserRole(String userRole) {
        UserRole = userRole;
    }
}
