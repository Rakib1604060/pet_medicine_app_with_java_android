package com.elanco.elancoapps.ThirdButton;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.elanco.elancoapps.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Howto extends Fragment {
      String number;
      View rootview;
      ImageView first,second;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragment_howto,container,false);
        number=getArguments().getString("CARDNUMBER");

        Initialize();

        switch (number){
            case "one":
            second.setVisibility(View.GONE);
            first.setImageResource(R.drawable.img_drink1);
                break;
            case "two":
                second.setVisibility(View.VISIBLE);
                first.setImageResource(R.drawable.img_drink2);
                break;
            case "three":
                second.setVisibility(View.VISIBLE);
                first.setImageResource(R.drawable.img_drink3);
                break;
            case "four":
                second.setVisibility(View.VISIBLE);
                first.setImageResource(R.drawable.img_drink4);
                break;

        }
        return  rootview;
    }

    private void Initialize() {

        first=rootview.findViewById(R.id.firstimage);
        second=rootview.findViewById(R.id.secondimage);
    }
}
