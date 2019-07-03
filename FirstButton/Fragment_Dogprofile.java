package com.elanco.elancoapps.FirstButton;

import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.elanco.elancoapps.R;
import com.elanco.elancoapps.Saving;
import com.elanco.elancoapps.SelcectDateFragment;
import com.squareup.picasso.Picasso;
import com.theartofdev.edmodo.cropper.CropImage;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Formatter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Fragment_Dogprofile extends Fragment {
    View rootview;
    ImageView dogimage;
    TextView dogname;
    Button plusButton,doglistbutton,orderlifebutton,medicien_onebutton,medicine_twobutton;

    Dog dog;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    Context context;
    Fragment myfragment;
    TextView medicineonetext,medicinetwotext;
    DateTimeFormatter formatter;
    ImageView plusbuttonimage;

    Uri uri;
    int capable=0;
    int count =0;
    String date;
    Button datepickerButton;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragment_dogprofile,container,false);
        context=getActivity();
        formatter= DateTimeFormat.forPattern("yyyy-MM-dd");
        myfragment=this;




        final int position=getArguments().getInt("POSITION");
        dog= Saving.getAdogByPosition(position,getActivity());


        InitializeComponents();
        //setimage and name of dog
        UpdateUi();
        onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month++;
                date=year+"/"+month+"/"+day;
                datepickerButton.setText(date);


            }
        };

        orderlifebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Edit the things
                BuildDialogue(position);


            }
        });
         doglistbutton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 getFragmentManager().beginTransaction()
                         .replace(R.id.Fragment_contatiner,new Fragment_Doglist())
                         .commit();
             }
         });


         plusButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Bundle bundle=new Bundle();
                 bundle.putInt("POSITION",position);
                 Fragment fragment=new Fragment_Medicine();
                 fragment.setArguments(bundle);
                 getActivity().getSupportFragmentManager().beginTransaction()
                       .replace(R.id.Fragment_contatiner,fragment)
                       .commit();
             }
         });


         medicien_onebutton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 Bundle bundle=new Bundle();
                 bundle.putInt("POSITION",position);
                 bundle.putInt("WHICHONE",1);
                 Fragment fragment=new Fragment_DateSetting();
                 fragment.setArguments(bundle);
                 getActivity().getSupportFragmentManager().beginTransaction()
                         .replace(R.id.Fragment_contatiner,fragment)
                         .commit();


             }
         });

         medicine_twobutton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Bundle bundle=new Bundle();
                 bundle.putInt("POSITION",position);
                 bundle.putInt("WHICHONE",2);
                 Fragment fragment=new Fragment_DateSetting();
                 fragment.setArguments(bundle);
                 getActivity().getSupportFragmentManager().beginTransaction()
                         .replace(R.id.Fragment_contatiner,fragment)
                         .commit();

             }
         });

        ObjectAnimator animation = ObjectAnimator.ofFloat(plusbuttonimage, "translationY", 10f);
        animation.setDuration(1000);
        animation.setRepeatCount(Animation.INFINITE);
        animation.start();





        return  rootview;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE){

            CropImage.ActivityResult result= CropImage.getActivityResult(data);

            if (resultCode==getActivity().RESULT_OK){
                uri=result.getUri();
                Toast.makeText(getActivity(),"uri"+uri, Toast.LENGTH_SHORT).show();
                dogimage.setImageURI(uri);
                capable++;


            }
            else if (resultCode==CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE){

                Exception e=result.getError();
                Toast.makeText(getActivity(),"error: "+e, Toast.LENGTH_SHORT).show();
            }

        }

    }

    private void UpdateUi() {

        Picasso.with(getActivity()).load(dog.getDog_imagepath())
                .fit()
                .into(dogimage);

        String  dognamee=dog.getDog_name()+" "+getActivity().getResources().getString(R.string.registeryourmedicine);

        dogname.setText(dognamee);


        if (!dog.getOne_medicine_name().equals("NULL")){
            medicien_onebutton.setVisibility(View.VISIBLE);
            String dogmedicinename=dog.getOne_medicine_name();
            SetButtonBackground(dogmedicinename,medicien_onebutton);
            medicineonetext.setVisibility(View.VISIBLE);
            String days=getIntervalDays(1);
            medicineonetext.setText(days);
            count++;
            if (count==2){
                plusButton.setVisibility(View.GONE);
                plusbuttonimage.setVisibility(View.GONE);
            }




        }
        if (!dog.getTwo_medicine_name().equals("NULL")){
            medicine_twobutton.setVisibility(View.VISIBLE);
            String dogmedicinename=dog.getTwo_medicine_name();
            SetButtonBackground(dogmedicinename,medicine_twobutton);
            medicinetwotext.setVisibility(View.VISIBLE);
            medicinetwotext.setVisibility(View.VISIBLE);
            String days=getIntervalDays(2);
            medicinetwotext.setText(days);
            count++;
            if (count==2){
                plusButton.setVisibility(View.GONE);
            }
        }


    }

    private String getIntervalDays(int i) {
        LocalDate localDate=new LocalDate();
        LocalDate dogdate=null;

        switch (i){
            case 1:
                dogdate=formatter.parseLocalDate(dog.getOne_medicine_date());

                break;

            case 2:
                dogdate=formatter.parseLocalDate(dog.getTwo_medicine_date());
                break;

        }

        int day= Days.daysBetween(localDate,dogdate).getDays();
        Toast.makeText(getActivity(),dogdate.toString(), Toast.LENGTH_SHORT).show();

        return day+"";



    }


    private void BuildDialogue(final int position) {
        final Dialog dialog = new Dialog(getActivity());
        dialog.setContentView(R.layout.dogsavingdialogue);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.setTitle("Register a dog");
        dialog.setCancelable(false);

        //get components from dialogue view
        dogimage = dialog.findViewById(R.id.imageView3);
        Picasso.with(getActivity()).load(dog.getDog_imagepath()).into(dogimage);

        Button deletebutton=dialog.findViewById(R.id.deletebutton);
        deletebutton.setVisibility(View.VISIBLE);
        final EditText dogname = dialog.findViewById(R.id.editText);
        final Button savebutton = dialog.findViewById(R.id.registerdogbutton);
        Button cancelbutton = dialog.findViewById(R.id.cancellbutton);
         datepickerButton = dialog.findViewById(R.id.datebutton);
        datepickerButton.setText(dog.getDog_birthdate());
        dogname.setText(dog.getDog_name());
        savebutton.setEnabled(false);

        deletebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Saving.DeleteAdogatPosition(position,getActivity());
                getActivity().getSupportFragmentManager().beginTransaction()
                        .replace(R.id.Fragment_contatiner,new Fragment_Doglist())
                        .commit();
                dialog.dismiss();

            }
        });


        // cancel button if press calcel
        cancelbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        datepickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //date picker
                Calendar calendar = Calendar.getInstance();
                int year = calendar.get(Calendar.YEAR);
                int month = calendar.get(Calendar.MONTH);
                int day = calendar.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,onDateSetListener, year, month, day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();

            }
        });

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dog dog = new Dog();
                dog.setDog_birthdate(date);
                dog.setDog_name(dogname.getText().toString());
                Saving.SaveADog(dog, getActivity(), position);


            }
        });


        //if click the image icon
        dogimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               CropImage.activity().start(context, myfragment);
            }
        });

        dialog.show();
    }



        private void InitializeComponents() {

        dogimage=rootview.findViewById(R.id.profileimage);
        dogname=rootview.findViewById(R.id.dognametv);
        plusButton=rootview.findViewById(R.id.plsbutton);
        orderlifebutton=rootview.findViewById(R.id.orderbutton);
        doglistbutton=rootview.findViewById(R.id.backtodoglist);
        medicien_onebutton=rootview.findViewById(R.id.medicinebuttonone);
        medicine_twobutton=rootview.findViewById(R.id.medicinetwobutton);
        medicineonetext=rootview.findViewById(R.id.textview10);
        medicinetwotext=rootview.findViewById(R.id.textView9);
        plusbuttonimage=rootview.findViewById(R.id.plussbuttonimage);

    }

    private  void SetButtonBackground(String name,Button button){
        switch ( name){
            case "one":
                button.setBackgroundResource(R.drawable.logo1);
                break;
            case "two":
                button.setBackgroundResource(R.drawable.logo2);
                break;
            case "three":
                button.setBackgroundResource(R.drawable.logo3);
                break;
            case "four":
                button.setBackgroundResource(R.drawable.logo4);
                break;
            case "five":
                button.setBackgroundResource(R.drawable.logo5);
                break;
            case "six":
                button.setBackgroundResource(R.drawable.logo6);
                break;
             default:
                button.setBackgroundResource(R.color.maincolor);
                button.setText(name);
                break;
        }

    }
}
