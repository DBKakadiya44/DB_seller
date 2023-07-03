package com.example.db_seller;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_Interface
{
    @FormUrlEncoded
    @POST("Register.php")
    Call<Model_Class> registerUser(@Field("name") String name,@Field("email") String email,@Field("password") String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<Model_Class> loginUser(@Field("email") String email,@Field("password") String password);

}
