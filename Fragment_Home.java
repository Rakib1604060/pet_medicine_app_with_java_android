package com.elanco.elancoapps;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.elanco.elancoapps.FifthButton.Fragment_fifth;
import com.elanco.elancoapps.FirstButton.Fragment_Doglist;
import com.elanco.elancoapps.FourthButton.Fragment_medicinelist;
import com.elanco.elancoapps.SecondButton.Fragment_secondbutton;
import com.elanco.elancoapps.ThirdButton.Fragment_thirdbutton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Home extends Fragment {
     Button firstbutton,secondbutton,thirdbutton,fourthbutton,fifthbutton,aboutusButton;
     View rootview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
         rootview=LayoutInflater.from(container.getContext()).inflate(R.layout.fragment_home,container,false);
       // Initialize all components we need
        InitializeComponents();










        // set action listener to every button;
        firstbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Go to Dog list Fragment

                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Fragment_contatiner,new Fragment_Doglist())
                        .addToBackStack("sample")
                        .commit();
            }
        });

        secondbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Fragment_contatiner,new Fragment_secondbutton())
                        .commit();
            }
        });
        fourthbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Fragment_contatiner,new Fragment_medicinelist())
                        .addToBackStack("sample")
                        .commit();
            }
        });

        thirdbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Fragment_contatiner,new Fragment_thirdbutton())
                        .addToBackStack("sample")
                        .commit();
            }
        });
        fifthbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Fragment_contatiner,new Fragment_fifth())
                        .addToBackStack("sample")
                        .commit();
            }
        });

        aboutusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Fragment_contatiner,new Fragment_aboutus())
                        .addToBackStack("sample")
                        .commit();

            }
        });

        return  rootview;
    }

    private void InitializeComponents() {
        firstbutton=rootview.findViewById(R.id.optiononebutton);
        secondbutton=rootview.findViewById(R.id.optiontwobutton);
        thirdbutton=rootview.findViewById(R.id.optionthree);
        fourthbutton=rootview.findViewById(R.id.optionfourbutton);
        fifthbutton=rootview.findViewById(R.id.optionfivebutton);
        aboutusButton=rootview.findViewById(R.id.aboutusbutton);


    }
}
