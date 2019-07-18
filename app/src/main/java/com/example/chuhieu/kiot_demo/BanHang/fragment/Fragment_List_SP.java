package com.example.chuhieu.kiot_demo.BanHang.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.chuhieu.kiot_demo.BanHang.adapter.AdapterListSanPham;
import com.example.chuhieu.kiot_demo.BanHang.model.Category;
import com.example.chuhieu.kiot_demo.BanHang.model.CategoryResponse;
import com.example.chuhieu.kiot_demo.Login.activity.LoginActivity;
import com.example.chuhieu.kiot_demo.Service.ApiService;
import com.example.chuhieu.kiot_demo.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_List_SP extends Fragment {
    RecyclerView recyclerView;
    AdapterListSanPham adapterListSanPham;
    List<CategoryResponse> responseList;
    LinearLayoutManager linearLayoutManager;
    String token = LoginActivity.token;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_sp, container, false);

        responseList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.rcListSp);
        linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterListSanPham = new AdapterListSanPham(responseList);
        recyclerView.setAdapter(adapterListSanPham);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://bmsapi.hosco.com.vn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService client = retrofit.create(ApiService.class);
        final Category category = new Category("", "", "",
                true, 0, "", 100, 0);

        Call<List<CategoryResponse>> call = client.body1("Bearer " + token, category);
        call.enqueue(new Callback<List<CategoryResponse>>() {
            @Override
            public void onResponse(Call<List<CategoryResponse>> call, Response<List<CategoryResponse>> response) {
                if (response.body() != null) {
                    if (response.code() == 200) {
                        responseList = response.body();
                        adapterListSanPham.updateData(responseList);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CategoryResponse>> call, Throwable t) {

            }
        });

//  Create line Recycleview
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        return view;
    }


}
