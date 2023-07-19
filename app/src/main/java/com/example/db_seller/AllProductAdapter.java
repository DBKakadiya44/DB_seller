package com.example.db_seller;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import DataBase.Productdatum;
import fragments.Home_Fragment;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.ViewHolder> {
    Home_Fragment home_fragment;
    List<Productdatum> productdata;

    public AllProductAdapter(Home_Fragment home_fragment, List<Productdatum> productdata) {
        this.home_fragment = home_fragment;
        this.productdata = productdata;
    }

    @NonNull
    @Override
    public AllProductAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_inventory_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        holder.menuoption.setVisibility(View.INVISIBLE);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllProductAdapter.ViewHolder holder, int position) {
        holder.item.setText("\t"+productdata.get(position).getName());
        holder.price.setText("Price : "+productdata.get(position).getPrice());
        holder.stock.setText("Stock : "+productdata.get(position).getStock());
        holder.category.setText("Category : "+productdata.get(position).getCategory());

        Glide.with(home_fragment).load("https://dipkakadiya.000webhostapp.com/MySite/"+productdata.get(position).getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return productdata.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView,menuoption;
        TextView item,stock,price,category;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.in_re_img);
            item=itemView.findViewById(R.id.in_re_title);
            stock=itemView.findViewById(R.id.in_re_stock);
            price=itemView.findViewById(R.id.in_re_price);
            category=itemView.findViewById(R.id.in_re_category);
            menuoption=itemView.findViewById(R.id.menuoption);
        }
    }
}
