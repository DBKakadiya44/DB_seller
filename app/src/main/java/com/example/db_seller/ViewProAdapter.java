package com.example.db_seller;

import static com.example.db_seller.Splash_Screen.editor;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import DataBase.Instence_class;
import DataBase.Model_Class;
import DataBase.Productdatum;
import fragments.Add_Product_Fragment;
import fragments.Inventory_Fragment;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProAdapter extends RecyclerView.Adapter<ViewProAdapter.ProHolder> {
    Inventory_Fragment inventory_fragment;
    List<Productdatum> productdata;

    public ViewProAdapter(Inventory_Fragment inventory_fragment, List<Productdatum> productdata) {
        this.inventory_fragment = inventory_fragment;
        this.productdata=productdata;
    }

    @NonNull
    @Override
    public ViewProAdapter.ProHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_inventory_item,parent,false);
        ProHolder holder = new ProHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewProAdapter.ProHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.item.setText("\t"+productdata.get(position).getName());
        holder.price.setText("Price : "+productdata.get(position).getPrice());
        holder.stock.setText("Stock : "+productdata.get(position).getStock());
        holder.category.setText("Category : "+productdata.get(position).getCategory());

        Glide.with(inventory_fragment).load("https://dipkakadiya.000webhostapp.com/MySite/"+productdata.get(position).getImage()).into(holder.imageView);

        holder.menuoption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(inventory_fragment.getContext(),v);
                popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {

                        if(item.getItemId()==R.id.updatemenu){
                            editor.putString("from","update");
                            editor.putString("pid",""+productdata.get(position).getId());
                            editor.putString("pname",""+productdata.get(position).getName());
                            editor.putString("pprice",""+productdata.get(position).getPrice());
                            editor.putString("pstock",""+productdata.get(position).getStock());
                            editor.putString("pcategory",""+productdata.get(position).getCategory());
                            editor.putString("pimage",""+productdata.get(position).getImage());
                            editor.commit();

                            addfragment(new Add_Product_Fragment());
                        }
                        if(item.getItemId()==R.id.deletemenu){

                            Instence_class.Callapi().deleteproduct(Integer.parseInt(productdata.get(position).getId())).enqueue(new Callback<Model_Class>() {
                                @Override
                                public void onResponse(Call<Model_Class> call, Response<Model_Class> response) {
                                    if(response.body().getConnection()==1){
                                        if(response.body().getResult()==1){
                                            Toast.makeText(inventory_fragment.getContext(), "Product Delete", Toast.LENGTH_LONG).show();
                                            productdata.remove(position);
                                            notifyDataSetChanged();
                                            notifyDataSetChanged();

                                        }else
                                        {
                                            Toast.makeText(inventory_fragment.getContext(), "Delete Fail", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<Model_Class> call, Throwable t) {

                                }
                            });

                        }
                        return true;
                    }
                });
                popupMenu.show();
            }
        });

    }
    private void addfragment(Fragment fragment)
    {
        FragmentManager fm = inventory_fragment.getParentFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.framlayout,fragment);
        transaction.commit();
    }

    @Override
    public int getItemCount() {
        return productdata.size();
    }

    public class ProHolder extends RecyclerView.ViewHolder {
        ImageView imageView,menuoption;
        TextView item,stock,price,category;

        public ProHolder(@NonNull View itemView) {
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