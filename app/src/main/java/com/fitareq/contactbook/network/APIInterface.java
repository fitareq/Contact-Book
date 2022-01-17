package com.fitareq.contactbook.network;

import com.fitareq.contactbook.model.LoginBody;
import com.fitareq.contactbook.model.LoginResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface APIInterface
{
    @Headers({
            "accept: */*",
            "Content-Type: application/json"
    })
    @POST("Login")
    Call<LoginResponse> userSignIn(
            @Body LoginBody body);
}
