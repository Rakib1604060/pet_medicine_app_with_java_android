package com.elanco.elancoapps.FourthButton;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.elanco.elancoapps.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_medicinelist  extends Fragment {
    View rootview;

    Button one,two,three,four,five,six;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragment_medicinelist,container,false);

        Initialize();



        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putBundleinFragment("one");
            }
        });


        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putBundleinFragment("two");
            }
        });


        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putBundleinFragment("three");
            }
        });

        four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putBundleinFragment("four");
            }
        });
        five.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putBundleinFragment("five");
            }
        });

        six.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                putBundleinFragment("six");
            }
        });


        return rootview;
    }

    private void Initialize() {
        one=rootview.findViewById(R.id.button);
        two=rootview.findViewById(R.id.button2);
        three=rootview.findViewById(R.id.button3);
        four=rootview.findViewById(R.id.button4);
        five =rootview.findViewById(R.id.button5);
        six=rootview.findViewById(R.id.button6);

    }

    private void putBundleinFragment(String number){
        final Bundle bundle=new Bundle();
        bundle.putString("MEDICINE",number);
        bundle.putString("SHOWBUTTON","NO");
        Fragment fragment=new Fragment_Allmedicine();
        fragment.setArguments(bundle);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.Fragment_contatiner,fragment)
                .commit();


    }




}
