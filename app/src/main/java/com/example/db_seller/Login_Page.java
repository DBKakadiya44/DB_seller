package com.example.db_seller;

import static com.example.db_seller.Splash_Screen.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.db_seller.databinding.LoginPageBinding;

import DataBase.Instence_class;
import DataBase.LoginClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login_Page extends AppCompatActivity {
    LoginPageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = LoginPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Instence_class.Callapi().loginUser(binding.email.getText().toString(),binding.password.getText().toString()).enqueue(new Callback<LoginClass>() {
                    @Override
                    public void onResponse(Call<LoginClass> call, Response<LoginClass> response) {
                        if(response.body().getConnection()==1) {
                            if (response.body().getResult() == 1) {
                                Intent intent = new Intent(Login_Page.this, Main_Page.class);
                                editor.putInt("login",1);
                                editor.putInt("sellerid", Integer.parseInt(response.body().getUserdata().getId()));
                                editor.putString("sellername",response.body().getUserdata().getName());
                                editor.putString("selleremail",response.body().getUserdata().getEmail());
                                editor.commit();
                                startActivity(intent);
                                Toast.makeText(Login_Page.this, "Log In SuccessFully", Toast.LENGTH_LONG).show();
                            }
                            if (response.body().getResult() == 0) {
                                Intent intent = new Intent(Login_Page.this,Signup_Page.class);
                                startActivity(intent);
                                Toast.makeText(Login_Page.this, "User Not Found!!!", Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Toast.makeText(Login_Page.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<LoginClass> call, Throwable t) {
                        Log.d("TTT", "onFailure: off = "+t.getLocalizedMessage());
                    }
                });

            }
        });

    }
}