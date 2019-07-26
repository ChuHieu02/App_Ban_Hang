package com.example.chuhieu.kiot_demo.BanHang.adapter;

import android.content.Context;
import android.content.Intent;
import android.icu.text.DecimalFormat;
import android.icu.text.DecimalFormatSymbols;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.chuhieu.kiot_demo.BanHang.ItemonClick;
import com.example.chuhieu.kiot_demo.BanHang.model.CategoryResponse;
import com.example.chuhieu.kiot_demo.Bill.BillActivity;
import com.example.chuhieu.kiot_demo.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterListSanPham extends RecyclerView.Adapter<AdapterListSanPham.SanPhamHolder> implements Filterable {
    private List<CategoryResponse> mlist;
    private List<CategoryResponse> mFilter;
    private ItemonClick itemonClick;
    private Context context;

    public void setItemonClick(ItemonClick itemonClick) {
        this.itemonClick = itemonClick;
    }

    public AdapterListSanPham(Context context, List<CategoryResponse> mlist) {
        this.mlist = mlist;
        this.mFilter = mlist;
        this.context = context;
    }
    @NonNull
    @Override
    public SanPhamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sp, parent, false);
        return new SanPhamHolder(view);
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public void onBindViewHolder(@NonNull SanPhamHolder holder, final int position) {

        final CategoryResponse categoryRespon = mFilter.get(position);
        holder.tv_name.setText(categoryRespon.getName());
        holder.tv_code.setText(categoryRespon.getProductCode());
        holder.tv_Price.setText(formarttoMoney(String.valueOf(categoryRespon.getPrice()))+" đ");
        holder.tv_Number.setText(categoryRespon.getUnit());

       holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View view) {
//               itemonClick.Onclick(mFilter.get(position));
                Intent intent = new Intent(context,BillActivity.class);
                intent.putExtra("Name",categoryRespon.getName());
                intent.putExtra("Price",(String.valueOf(categoryRespon.getPrice())));

                context.startActivity(intent);

           }
        });

    }

    @Override
    public int getItemCount() {
        return mFilter.size();
    }
    //    tạo bộ lọc fiter de seach
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    mFilter = mlist;
                } else {
                    List<CategoryResponse> filteredList = new ArrayList<>();
                    for (CategoryResponse row : mlist) {
                        if (row.getName().toLowerCase().contains(charString.toLowerCase()) || row.getId().contains(charSequence)) {
                            filteredList.add(row);
                        }
                    }
                    mFilter = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mFilter;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mFilter = (ArrayList<CategoryResponse>) filterResults.values;
                notifyDataSetChanged();
            }
        };
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
        this.mFilter.addAll(responseList);
        notifyDataSetChanged();
    }
    @RequiresApi(api = Build.VERSION_CODES.N)
    public String formarttoMoney(String data) {
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setDecimalSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###", symbols);
        return decimalFormat.format(Double.parseDouble(data));
    }
    public void clearList() {
        this.mFilter.clear();
        notifyDataSetChanged();
    }

}
