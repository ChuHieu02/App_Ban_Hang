package com.example.chuhieu.kiot_demo.Service;

import com.example.chuhieu.kiot_demo.BanHang.model.Category;
import com.example.chuhieu.kiot_demo.BanHang.model.CategoryResponse;
import com.example.chuhieu.kiot_demo.Login.model.Login;
import com.example.chuhieu.kiot_demo.Login.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiService {
    @POST("token")
    Call<User> token(@Body Login login);


    @Headers({"Content-Type: application/json"})
    @POST("api/Category/ProductList")
    Call<List<CategoryResponse>> body1(@Header("Authorization") String auth, @Body Category category);


}
