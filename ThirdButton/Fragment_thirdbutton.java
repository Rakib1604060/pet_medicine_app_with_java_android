package com.elanco.elancoapps.ThirdButton;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.elanco.elancoapps.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

public class Fragment_thirdbutton extends Fragment {

    View rootview;
    CardView one,two,three,four;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragment_thirdbutton,container,false);
         Initialize();


        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setFragment("one");

            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setFragment("two");

            }
        });


        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setFragment("three");

            }
        });


        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setFragment("four");

            }
        });

        return rootview;
    }

    private void setFragment(String number) {
        Bundle bundle=new Bundle();
        bundle.putString("CARDNUMBER",number);

        Fragment fragment=new Fragment_Howto();
        fragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.Fragment_contatiner,fragment)
                .commit();


    }

    private void Initialize() {
        one=rootview.findViewById(R.id.cardviewone);
        two=rootview.findViewById(R.id.cardviewtwo);
        three=rootview.findViewById(R.id.cardviewthree);
        four=rootview.findViewById(R.id.cardviewfour);

    }
}
