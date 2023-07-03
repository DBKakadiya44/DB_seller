package com.example.db_seller;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Instence_class
{
    public static Api_Interface Callapi(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://dipkakadiya.000webhostapp.com/MySite/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api_Interface service = retrofit.create(Api_Interface.class);
        return service;
    }
}
