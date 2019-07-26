package com.example.chuhieu.kiot_demo.Service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private static final String BASE_URL = "http://bmsapi.hosco.com.vn/";
    private static Retrofit retrofit=null;
    public static Retrofit getRetrofitClient(String baseUrl) {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(baseUrl).addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}
