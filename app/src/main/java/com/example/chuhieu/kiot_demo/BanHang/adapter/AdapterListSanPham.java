package com.example.chuhieu.kiot_demo.BanHang.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chuhieu.kiot_demo.BanHang.model.CategoryResponse;
import com.example.chuhieu.kiot_demo.R;

import java.util.List;

public class AdapterListSanPham extends RecyclerView.Adapter<AdapterListSanPham.SanPhamHolder> {
    List<CategoryResponse> categoryRespons;

    public AdapterListSanPham(List<CategoryResponse> categoryRespons) {
        this.categoryRespons = categoryRespons;
    }

    @NonNull
    @Override
    public AdapterListSanPham.SanPhamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sp, parent, false);
        return new SanPhamHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AdapterListSanPham.SanPhamHolder holder, int position) {
        CategoryResponse categoryRespon = categoryRespons.get(position);
        holder.tv_name.setText(categoryRespon.getName());
        holder.tv_code.setText(categoryRespon.getProductCode());
        holder.tv_Price.setText(categoryRespon.getPrice()+"");
        holder.tv_Number.setText(categoryRespon.getUnit());
        
    }

    @Override
    public int getItemCount() {
        return categoryRespons.size();
    }

    public class SanPhamHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView tv_name;
        TextView tv_code;
        TextView tv_Price;
        TextView tv_Number;


        public SanPhamHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_code = itemView.findViewById(R.id.tv_code);
            tv_Price = itemView.findViewById(R.id.tv_price);
            tv_Number = itemView.findViewById(R.id.tv_number);

        }
    }

    public void updateData(List<CategoryResponse> responseList) {
        this.categoryRespons.addAll(responseList);
        notifyDataSetChanged();
    }
}
