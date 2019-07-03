package com.elanco.elancoapps.FourthButton;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.elanco.elancoapps.FirstButton.Fragment_DateSetting;
import com.elanco.elancoapps.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Allmedicine extends Fragment {


  View rootview;

  String name;
    int position=999;

    String showbutton=null;
    Button datesetButton;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        name=getArguments().getString("MEDICINE");
        showbutton=getArguments().getString("SHOWBUTTON");


        if (name!=null){
         position=getArguments().getInt("POSITION",999);

        }


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        switch (name){
            case "one":
                rootview=LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragment_medicine_one,container,false);
                MedicineOne("one");
                break;
            case "two":
                rootview=LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragment_medicine_two,container,false);
                MedicineOne("two");
                break;
            case "three":
                rootview=LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragment_medicine_three,container,false);
                MedicineOne("three");
                break;
            case "four":
                rootview=LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragment_medicine_four,container,false);
                MedicineOne("four");
                break;
            case "five":
                rootview=LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragment_medicine_five,container,false);
                MedicineOne("five");
                break;
            case "six":

                rootview=LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragment_medicine_six,container,false);
                MedicineOne("six");
                break;

            case "others":
                rootview=LayoutInflater.from(inflater.getContext()).inflate(R.layout.otherslayout,container,false);
               OthersButton();
        }








        return  rootview;
    }

    private void OthersButton() {
        final EditText et_name=rootview.findViewById(R.id.editText2);
        datesetButton=rootview.findViewById(R.id.button_det);
        datesetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (et_name.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Please inseet A name", Toast.LENGTH_SHORT).show();
                }
                else {
                    String medicinename=et_name.getText().toString();
                    setBundleInFragment(medicinename);

                }

            }
        });


    }

    private void MedicineOne(final String number) {
        Button dosedateButton=rootview.findViewById(R.id.datesettingbutton);

     if (showbutton!=null){
         dosedateButton.setVisibility(View.GONE);
     }else{

         dosedateButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 setBundleInFragment(number);

             }
         });
     }





    }


    private  void setBundleInFragment(String number){
        Bundle bundle=new Bundle();
        bundle.putInt("POSITION",position);
        bundle.putString("MEDICINE",number);
        Fragment fragment=new Fragment_DateSetting();
        fragment.setArguments(bundle);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.Fragment_contatiner,fragment)
                .commit();

    }
}
