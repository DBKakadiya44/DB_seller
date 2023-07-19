package fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.db_seller.AllProductAdapter;
import com.example.db_seller.R;
import com.example.db_seller.Splash_Screen;

import DataBase.Instence_class;
import DataBase.ViewProductClass;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Home_Fragment extends Fragment
{
    ImageView img1;
    int prodata= 0;
    RecyclerView recyclerView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.home_fragment,container,false);

        img1 = view.findViewById(R.id.img1);
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Instence_class.Callapi().allproduct(String.valueOf(prodata)).enqueue(new Callback<ViewProductClass>() {
                    @Override
                    public void onResponse(Call<ViewProductClass> call, Response<ViewProductClass> response) {
                        if(response.body().getConnection()==1){
                            if(response.body().getResult()==1){

                                AllProductAdapter adapter = new AllProductAdapter(Home_Fragment.this,response.body().getProductdata());
                                recyclerView=view.findViewById(R.id.recyclerhome);
                                LinearLayoutManager manager = new LinearLayoutManager(getContext());
                                manager.setOrientation(RecyclerView.VERTICAL);
                                recyclerView.setLayoutManager(manager);
                                recyclerView.setAdapter(adapter);

//                                addfragment(new Inventory_Fragment());
                            }
                            else {
                                Log.d("QQQ", "onResponse: result = "+response.body().getResult());
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<ViewProductClass> call, Throwable t) {

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
}
