package com.fitareq.contactbook.model;

import com.google.gson.annotations.SerializedName;

public class LoginResponse {

    @SerializedName("responseObj")
    private ResponseObj responseObj;

    @SerializedName("responseCode")
    private Integer responseCode;

    public ResponseObj getResponseObj() {
        return responseObj;
    }

    public void setResponseObj(ResponseObj responseObj) {
        this.responseObj = responseObj;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

}

