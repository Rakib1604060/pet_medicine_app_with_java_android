package com.elanco.elancoapps.SecondButton;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.elanco.elancoapps.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_secondbutton extends Fragment {
    View rootview;
    ImageButton one,two,three,four,five,six,seven,eight;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragment_secondbutton,container,false);
        InitializeButton();

        one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Bundle bundle=new Bundle();
           bundle.putString("PERASITENUMBER","one");
           Fragment fragment=new Fragment_perasite();
           fragment.setArguments(bundle);
           getActivity().getSupportFragmentManager().beginTransaction()
                  .replace(R.id.Fragment_contatiner,fragment)
                  .commit();


            }
        });

        two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Bundle bundle=new Bundle();
                bundle.putString("PERASITENUMBER","two");
                Fragment fragment=new Fragment_perasite();
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Fragment_contatiner,fragment)
                        .commit();


            }
        });

        three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle=new Bundle();
                bundle.putString("PERASITENUMBER","three");
                Fragment fragment=new Fragment_perasite();
                fragment.setArguments(bundle);
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Fragment_contatiner,fragment)
                        .commit();
            }
        });
       four.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Bundle bundle=new Bundle();
               bundle.putString("PERASITENUMBER","four");
               Fragment fragment=new Fragment_perasite();
               fragment.setArguments(bundle);
               getActivity().getSupportFragmentManager().beginTransaction()
                       .replace(R.id.Fragment_contatiner,fragment)
                       .commit();
           }
       });
     five.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Bundle bundle=new Bundle();
             bundle.putString("PERASITENUMBER","five");
             Fragment fragment=new Fragment_perasite();
             fragment.setArguments(bundle);
             getActivity().getSupportFragmentManager().beginTransaction()
                     .replace(R.id.Fragment_contatiner,fragment)
                     .commit();
         }
     });

     six.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Bundle bundle=new Bundle();
             bundle.putString("PERASITENUMBER","six");
             Fragment fragment=new Fragment_perasite();
             fragment.setArguments(bundle);
             getActivity().getSupportFragmentManager().beginTransaction()
                     .replace(R.id.Fragment_contatiner,fragment)
                     .commit();
         }
     });
     seven.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Bundle bundle=new Bundle();
             bundle.putString("PERASITENUMBER","seven");
             Fragment fragment=new Fragment_perasite();
             fragment.setArguments(bundle);
             getActivity().getSupportFragmentManager().beginTransaction()
                     .replace(R.id.Fragment_contatiner,fragment)
                     .commit();
         }
     });

     eight.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             Bundle bundle=new Bundle();
             bundle.putString("PERASITENUMBER","eight");
             Fragment fragment=new Fragment_perasite();
             fragment.setArguments(bundle);
             getActivity().getSupportFragmentManager().beginTransaction()
                     .replace(R.id.Fragment_contatiner,fragment)
                     .commit();
         }
     });


        return  rootview;
    }

    private void InitializeButton() {

        one=rootview.findViewById(R.id.perasite_one);
        two=rootview.findViewById(R.id.perasite_two);
        three=rootview.findViewById(R.id.perasite_three);
        four=rootview.findViewById(R.id.perasite_four);
        five=rootview.findViewById(R.id.perasite_five);
        six=rootview.findViewById(R.id.perasite_six);
        seven=rootview.findViewById(R.id.perasite_seven);
        eight=rootview.findViewById(R.id.perasite_eight);

    }
}
