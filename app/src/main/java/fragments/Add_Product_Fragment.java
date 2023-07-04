package fragments;

import static android.app.Activity.RESULT_OK;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.db_seller.R;
import com.example.db_seller.databinding.AddProductFragmentBinding;

public class Add_Product_Fragment extends Fragment
{
    int sellerid,price,stock;
    String name,category;
    ImageView imageView;
    EditText fname,fstock,fprice,fcategory;

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

       imageView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               imageChooser();
           }
       });

        return view;
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
