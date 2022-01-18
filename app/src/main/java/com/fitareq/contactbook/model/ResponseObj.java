package com.fitareq.contactbook.model;

import androidx.room.Entity;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "user_data")
public class ResponseObj {
    @SerializedName("token")
    private String token;

    @SerializedName("userName")
    private String userName;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
