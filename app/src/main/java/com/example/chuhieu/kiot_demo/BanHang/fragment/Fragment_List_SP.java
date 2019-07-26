package com.example.chuhieu.kiot_demo.BanHang.fragment;

import android.app.ProgressDialog;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.chuhieu.kiot_demo.BanHang.MyDividerItemDecoration;
import com.example.chuhieu.kiot_demo.BanHang.adapter.AdapterListSanPham;
import com.example.chuhieu.kiot_demo.BanHang.model.Category;
import com.example.chuhieu.kiot_demo.BanHang.model.CategoryResponse;
import com.example.chuhieu.kiot_demo.Login.activity.LoginActivity;
import com.example.chuhieu.kiot_demo.R;
import com.example.chuhieu.kiot_demo.Service.ApiService;
import com.example.chuhieu.kiot_demo.Service.ApiUtils;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Fragment_List_SP extends Fragment {
    private ProgressDialog progressDialog;
    private RecyclerView recyclerView;
    private AdapterListSanPham adapterListSanPham;
    private List<CategoryResponse> responseList = new ArrayList<>();
    private LinearLayoutManager linearLayoutManager;
    private SearchView searchView;
    private ImageView imgqrCode;
    private int lastList, totalCount;
    private int currentPage = 0;
    private String currenName;
    private Category category;
    ApiService apiService;


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list_sp, container, false);
        searchView = view.findViewById(R.id.search);

        imgqrCode = view.findViewById(R.id.qrCode);
        recyclerView = view.findViewById(R.id.rcListSp);
        apiService = ApiUtils.getService();
        dialog();
        category = new Category("", "", "", true, 0, "", 20, 0);
        initdata();
        scrollRecyclerview();
        seachview();

        imgqrCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


            }
        });

        initRecyclerview();
        return view;

    }

    private void seachOnline() {

        Category category1 = new Category("", "", "",
                true, 0, "", 10, 0);
        currenName = String.valueOf(searchView.getQuery());
        Log.e("err", currenName);
        category1.setProductName(currenName);

        apiService.body("Bearer " + LoginActivity.token, category1).enqueue(new Callback<List<CategoryResponse>>() {
            @Override
            public void onResponse(Call<List<CategoryResponse>> call, Response<List<CategoryResponse>> response) {
                if (response.body() != null) {
                    if (response.code() == 200) {
                        adapterListSanPham.clearList();
                        responseList = response.body();
                        adapterListSanPham.updateData(responseList);
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CategoryResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "Co loi xay ra", Toast.LENGTH_SHORT).show();

            }
        });

    }

    private void scrollRecyclerview() {
        linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);

                if (linearLayoutManager != null) {
                    lastList = linearLayoutManager.findLastVisibleItemPosition();
                }
                totalCount = Integer.parseInt(String.valueOf(recyclerView.getAdapter().getItemCount()));
                if (!recyclerView.canScrollVertically(1) && newState == RecyclerView.SCROLL_STATE_IDLE) {
                    currentPage++;
                    category.setPageIndex(currentPage);
                    progressDialog.show();
                    initdata();
                }
            }
        });
    }

    private void initRecyclerview() {
        adapterListSanPham = new AdapterListSanPham(getContext(), responseList);
        linearLayoutManager = new LinearLayoutManager(this.getContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new MyDividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL, 66));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapterListSanPham);
    }

    private void dialog() {
        progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Loading");
        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
    }

    private void seachview() {
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
                seachOnline();
                return false;
            }
        });


    }

    private void initdata() {
        apiService.body("Bearer " + LoginActivity.token, category).enqueue(new Callback<List<CategoryResponse>>() {
            @Override
            public void onResponse(Call<List<CategoryResponse>> call, Response<List<CategoryResponse>> response) {
                if (response.body() != null) {
                    if (response.code() == 200) {
                        responseList = response.body();
                        adapterListSanPham.updateData(responseList);
                        progressDialog.dismiss();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<CategoryResponse>> call, Throwable t) {
                Toast.makeText(getContext(), "Co loi xay ra", Toast.LENGTH_SHORT).show();

            }
        });

    }


}
