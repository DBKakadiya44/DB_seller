package com.example.db_seller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import fragments.Inventory_Fragment;

public class ViewProAdapter extends RecyclerView.Adapter<ViewProAdapter.ProHolder> {
    Inventory_Fragment inventory_fragment;

    public ViewProAdapter(Inventory_Fragment inventory_fragment) {
        this.inventory_fragment = inventory_fragment;
    }

    @NonNull
    @Override
    public ViewProAdapter.ProHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_inventory_item,parent,false);
        ProHolder holder = new ProHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewProAdapter.ProHolder holder, int position) {
        holder.item.setText("Mouse");
        holder.price.setText("100");
        holder.stock.setText("20");
        holder.category.setText("Electronics");
        holder.imageView.setImageResource(R.drawable.mobile);
    }

    @Override
    public int getItemCount() {
        return 1;
    }

    public class ProHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView item,stock,price,category;
        public ProHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.in_re_img);
            item=itemView.findViewById(R.id.in_re_title);
            stock=itemView.findViewById(R.id.in_re_stock);
            price=itemView.findViewById(R.id.in_re_price);
            category=itemView.findViewById(R.id.in_re_category);
        }
    }
}
