package com.example.db_seller;

import static com.example.db_seller.Splash_Screen.editor;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Signup_Page extends AppCompatActivity
{
    TextView signup;
    EditText name,email,password;
    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        signup=findViewById(R.id.signup);
        name=findViewById(R.id.signupname);
        email=findViewById(R.id.signupemail);
        password=findViewById(R.id.signuppassword);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Instence_class.Callapi().registerUser(name.getText().toString(),email.getText().toString(),password.getText().toString()).enqueue(new Callback<Model_Class>() {
                    @Override
                    public void onResponse(Call<Model_Class> call, Response<Model_Class> response) {
                        if(response.body().getConnection()==1) {
                            if (response.body().getResult() == 1) {
                                Intent intent = new Intent(Signup_Page.this, Main_Page.class);
                                editor.putInt("login",1);
                                editor.commit();
                                startActivity(intent);
                                Toast.makeText(Signup_Page.this, "Sign Up SuccessFully", Toast.LENGTH_LONG).show();
                            }
                            if (response.body().getResult() == 0) {
                                Toast.makeText(Signup_Page.this, "Sign Up Failed", Toast.LENGTH_LONG).show();
                            }

                        }else {
                            Toast.makeText(Signup_Page.this, "Check Internet Connection", Toast.LENGTH_LONG).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Model_Class> call, Throwable t) {
                        Log.d("TTT", "onFailure: off = "+t.getLocalizedMessage());
                    }
                });

            }
        });
    }
}
