package DataBase;

import android.text.Editable;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_Interface
{
    @FormUrlEncoded
    @POST("Register.php")
    Call<Model_Class> registerUser(@Field("name") String name, @Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<LoginClass> loginUser(@Field("email") String email,@Field("password") String password);

    @FormUrlEncoded
    @POST("addProduct.php")
    Call<PModel> addproduct(@Field("sellerid") int sellerid, @Field("name") String name, @Field("stock") Editable stock, @Field("price") Editable price, @Field("category") String category, @Field("productimage") String imagedata);

    @FormUrlEncoded
    @POST("viewProduct.php")
    Call<ViewProductClass> viewproduct(@Field("sellerid") int sellerid);

    @FormUrlEncoded
    @POST("updateproduct.php")
    Call<Model_Class> updateproduct(@Field("name") String name, @Field("price") String price, @Field("stock") String stock, @Field("category") String category,@Field("id") String id);

    @FormUrlEncoded
    @POST("deleteproduct.php")
    Call<Model_Class> deleteproduct(@Field("id") int id);

}
