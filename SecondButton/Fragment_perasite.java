package com.elanco.elancoapps.SecondButton;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.elanco.elancoapps.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_perasite extends Fragment {
    String number;
    View rootview;

    ImageView  topimage,thirdimage;

    TextView text_one,text_two,text_tree,text_four;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         number=getArguments().getString("PERASITENUMBER");


    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=LayoutInflater.from(inflater.getContext()).inflate(R.layout.perasite_layout,container,false);
        topimage=rootview.findViewById(R.id.perasiteimageview);
        thirdimage=rootview.findViewById(R.id.thirdimage);
        text_one=rootview.findViewById(R.id.persitefirsttext);
        text_two=rootview.findViewById(R.id.secondtextofperasite);
        text_tree=rootview.findViewById(R.id.thirdtextofperasite);
        text_four=rootview.findViewById(R.id.fourthtextid);






        switch (number){
            case "one":
                setButtonOne();
                break;
            case "two":
                setButtontwo();
                break;
            case "three":
                setButtonthree();

                break;
            case "four":
               setButtonfour();
                break;
            case "five":
                setButton_five();
                break;
            case "six":
               setButtoneSix();
                break;
            case "seven":
                setButtonseven();

                break;
            case "eight":
             setButtoneight();
                break;
        }


       return  rootview;
    }

    private  void  setButtoneight(){
        topimage.setImageResource(R.drawable.perasite_eight);
        thirdimage.setVisibility(View.GONE);
        text_four.setVisibility(View.GONE);
        text_one.setText(R.string.perasite_eight_text_one);
        text_two.setText(R.string.perasite_eight_text_two);
        text_tree.setText(R.string.perasite_eight_text_three);

    }

    private void setButtonseven() {
        topimage.setImageResource(R.drawable.perasite_seven);
        thirdimage.setVisibility(View.GONE);
        text_four.setVisibility(View.GONE);
        text_one.setText(R.string.perasite_seven_text_one);
        text_two.setText(R.string.perasite_seven_text_two);
        text_tree.setText(R.string.perasite_seven_text_three);

    }

    private void setButtoneSix() {

        topimage.setImageResource(R.drawable.perasite_six);
        thirdimage.setVisibility(View.GONE);
        text_four.setVisibility(View.GONE);
        text_one.setText(R.string.perasite_six_text_one);
        text_two.setText(R.string.perasite_six_text_two);
        text_tree.setText(R.string.perasite_six_text_three);


    }

    private void setButton_five() {
        topimage.setImageResource(R.drawable.perasite_five);
        thirdimage.setVisibility(View.GONE);
        text_four.setVisibility(View.GONE);
        text_one.setText(R.string.perasite_five_text_one);
        text_two.setText(R.string.perasite_five_text_two);
        text_tree.setText(R.string.perasite_five_text_three);


    }

    private void setButtonfour() {
        topimage.setImageResource(R.drawable.perasite_four);
        thirdimage.setVisibility(View.GONE);
        text_four.setVisibility(View.GONE);
        text_one.setText(R.string.perasite_four_text_one);
        text_two.setText(R.string.perasite_four_text_two);
        text_tree.setText(R.string.perasite_four_text_three);


    }

    private void setButtonthree() {
        topimage.setImageResource(R.drawable.perasite_three);
        thirdimage.setVisibility(View.VISIBLE);
        text_four.setVisibility(View.VISIBLE);
        text_one.setText(R.string.perasite_three_text_one);
        text_two.setText(R.string.perasite_three_text_two);
        text_tree.setText(R.string.perasite_three_text_three);
        text_four.setText(R.string.perasite_three_text_four);

    }

    private void setButtontwo() {
        topimage.setImageResource(R.drawable.perasite_two);
        thirdimage.setVisibility(View.VISIBLE);
        text_four.setVisibility(View.VISIBLE);
        text_one.setText(R.string.perasite_two_text_one);
        text_two.setText(R.string.perasite_two_text_two);
        text_tree.setText(R.string.perasite_two_text_three);
        text_four.setText(R.string.perasite_two_text_four);

    }

    private void setButtonOne() {
        topimage.setImageResource(R.drawable.perasite_one);
        thirdimage.setVisibility(View.GONE);
        text_four.setVisibility(View.GONE);
        text_one.setText(R.string.perasite_one_text_one);
        text_two.setText(R.string.perasite_one_text_two);
        text_tree.setText(R.string.perasite_one_text_three);
    }
}
