package com.fitareq.contactbook.model;

public class LoginBody
{
    private String userName;
    private String password;

    public LoginBody(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }
}
