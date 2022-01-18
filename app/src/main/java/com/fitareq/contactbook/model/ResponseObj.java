package com.fitareq.contactbook.model;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "user_data")
public class ResponseObj {

    @SerializedName("token")
    private String token;

    @NonNull
    @PrimaryKey
    @SerializedName("userName")
    private String userName;

    public String getToken() {
        return token;
    }

    public void setToken( String token) {
        this.token = token;
    }

    @NonNull
    public String getUserName() {
        return userName;
    }

    public void setUserName(@NonNull String userName) {
        this.userName = userName;
    }
}
