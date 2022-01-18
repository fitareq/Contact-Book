package com.fitareq.contactbook.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIClient {
    private static Retrofit retrofit;

    public static Retrofit getClient()
    {
        if (retrofit == null)
        {
            synchronized (APIClient.class){
                if (retrofit == null)
                {
                    retrofit = new Retrofit.Builder()
                            .baseUrl("http://52.76.178.223:5060/api/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                }
            }
        }

        return retrofit;
    }
}
