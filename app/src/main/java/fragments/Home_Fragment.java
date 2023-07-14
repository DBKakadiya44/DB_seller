package fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.db_seller.R;
import com.example.db_seller.Splash_Screen;

public class Home_Fragment extends Fragment
{
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(getContext()).inflate(R.layout.home_fragment,container,false);

        Log.d("TTTT", "onCreateView: Pid = "+ Splash_Screen.preferences.getString("pid",null));
        return view;
    }
}
