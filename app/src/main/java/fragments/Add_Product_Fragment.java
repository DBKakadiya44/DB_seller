package fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import DataBase.Instence_class;
import DataBase.PModel;
import com.example.db_seller.R;
import com.example.db_seller.Splash_Screen;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Add_Product_Fragment extends Fragment
{

    ImageView imageView;
    EditText fname,fstock,fprice,fcategory;
    TextView submitbutton;

    int SELECT_PICTURE = 200;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view = LayoutInflater.from(getContext()).inflate(R.layout.add_product_fragment,container,false);

       imageView = view.findViewById(R.id.addimage);
       fname=view.findViewById(R.id.namefield);
       fstock=view.findViewById(R.id.stockfield);
       fprice=view.findViewById(R.id.pricefield);
       fcategory=view.findViewById(R.id.categoryfield);
       submitbutton = view.findViewById(R.id.submitbutton);

       imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               imageChooser();
           }
       });

        submitbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addfragment(new Home_Fragment());
                Instence_class.Callapi().addproduct(Splash_Screen.preferences.getInt("sellerid",0),fname.getText().toString(),fstock.getText(),fprice.getText(),fcategory.getText().toString()).enqueue(new Callback<PModel>() {
                    @Override
                    public void onResponse(Call<PModel> call, Response<PModel> response) {
                        if(response.body().getConnection()==1)
                        {
                            if(response.body().getProductaddd()==1)
                            {
                                Toast.makeText(getContext(), "Product Add Sucessfully", Toast.LENGTH_LONG).show();
                            }else
                            {
                                Toast.makeText(getContext(), "Failed to Add Product", Toast.LENGTH_LONG).show();
                            }
                        }else
                        {
                            Toast.makeText(getContext(), "Check Internet Connection", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<PModel> call, Throwable t) {

                    }
                });

            }
        });

        return view;
    }

    private void addfragment(Fragment fragment)
    {
        FragmentManager fm = getParentFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.framlayout,fragment);
        transaction.commit();
    }


    void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();
        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    // update the preview image in the layout
                    imageView.setImageURI(selectedImageUri);
                }
            }
        }
    }
}
