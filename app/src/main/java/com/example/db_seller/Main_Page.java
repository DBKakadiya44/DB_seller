package com.example.db_seller;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.example.db_seller.databinding.HomePageBinding;
import com.google.android.material.navigation.NavigationView;

import fragments.Add_Product_Fragment;
import fragments.Home_Fragment;
import fragments.Inventory_Fragment;

public class Main_Page extends AppCompatActivity
{
    HomePageBinding binding;
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        binding=HomePageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        addfragment(new Home_Fragment());

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(Main_Page.this,binding.drawerlayout,binding.appbarMain.toolbar,R.string.open,R.string.close);
        binding.drawerlayout.addDrawerListener(toggle);
        toggle.syncState();

        binding.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId()==R.id.home){
                    addfragment(new Home_Fragment());
                    binding.drawerlayout.close();
                }
                if(item.getItemId()==R.id.addproduct){
                    addfragment(new Add_Product_Fragment());
                    binding.drawerlayout.close();
                }
                if(item.getItemId()==R.id.inventory){
                    addfragment(new Inventory_Fragment());
                    binding.drawerlayout.close();
                }

                return true;
            }
        });

    }

    private void addfragment(Fragment fragment)
    {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.framlayout,fragment);
        transaction.commit();
    }
}
