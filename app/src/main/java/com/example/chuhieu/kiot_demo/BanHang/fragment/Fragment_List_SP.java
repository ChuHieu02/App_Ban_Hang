package com.example.chuhieu.kiot_demo.BanHang.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chuhieu.kiot_demo.BanHang.ItemonClick;
import com.example.chuhieu.kiot_demo.BanHang.adapter.AdapterListSanPham;
import com.example.chuhieu.kiot_demo.BanHang.model.Category;
import com.example.chuhieu.kiot_demo.BanHang.model.CategoryResponse;
import com.example.chuhieu.kiot_demo.BarCode.BarCodeActivity;
import com.example.chuhieu.kiot_demo.Login.activity.LoginActivity;
import com.example.chuhieu.kiot_demo.R;
import com.example.chuhieu.kiot_demo.Service.ApiService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment_List_SP extends Fragment {
    private RecyclerView recyclerView;
    private AdapterListSanPham adapterListSanPham;
    public static List<CategoryResponse> responseList;
    private LinearLayoutManager linearLayoutManager;
    private String token = LoginActivity.token;
    private   SearchView searchView;
    private ImageView imgqrCode;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_sp, container, false);

        responseList = new ArrayList<>();
        recyclerView = view.findViewById(R.id.rcListSp);
        linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        adapterListSanPham = new AdapterListSanPham(getContext(), responseList);
        recyclerView.setAdapter(adapterListSanPham);
        adapterListSanPham.setItemonClick(new ItemonClick() {
            @Override
            public void Onclick(CategoryResponse categoryResponse) {
            }
        });


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://bmsapi.hosco.com.vn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        ApiService client = retrofit.create(ApiService.class);
        final Category category = new Category("", "", "",
                true, 0, "", 200, 0);

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
                Toast.makeText(getContext(), "Co loi xay ra", Toast.LENGTH_SHORT).show();
            }
        });

            searchView = view.findViewById(R.id.search);
            searchView.setActivated(true);
            searchView.setQueryHint("Nhập tên, mã, Serial/IMEL, lô, hsd");
            searchView.onActionViewExpanded();
            searchView.setIconified(false);
            searchView.clearFocus();
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {

                    adapterListSanPham.getFilter().filter(query);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    adapterListSanPham.getFilter().filter(newText);
                    return false;
                }
            });
            imgqrCode=view.findViewById(R.id.qrCode);
            imgqrCode.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getContext(),BarCodeActivity.class));
                }
            });

//  Create line Recycleview
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(getContext(), linearLayoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);
        return view;

    }


}
