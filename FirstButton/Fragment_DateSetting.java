package com.elanco.elancoapps.FirstButton;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PostProcessor;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.elanco.elancoapps.Fragment_Home;
import com.elanco.elancoapps.R;
import com.elanco.elancoapps.Saving;

import org.joda.time.DateTime;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.AlarmManagerCompat;
import androidx.fragment.app.Fragment;

public class Fragment_DateSetting extends Fragment {

    View rootview;

    Button datepicker,dateshower,savebutton,deleteButton;

    EditText showmonth;
    DatePickerDialog.OnDateSetListener onDateSetListener;
    int month=0;
    String startdate;
    boolean possible=false;


    boolean savingpossible=true;
    DateTimeFormatter formatter;
    int position;
    String name;

    String finaldate=null;
    DateTime localTime;
    int whichone;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        whichone=getArguments().getInt("WHICHONE",99);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootview=LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragmentdatesetting,container,false);
        position=getArguments().getInt("POSITION");
        name=getArguments().getString("MEDICINE");




        datepicker=rootview.findViewById(R.id.datepcikerbutton);
        dateshower=rootview.findViewById(R.id.dateshowerbutton);
        showmonth=rootview.findViewById(R.id.monthcount);
        savebutton=rootview.findViewById(R.id.saveAlamrmButton);
        deleteButton=rootview.findViewById(R.id.deletealarmbutton);
        formatter= DateTimeFormat.forPattern("yyyy-MM-dd");
        showmonth.setText("0");
        localTime=new DateTime();

        startdate=String.valueOf(formatter.print(localTime));

        if (whichone==1||whichone==2){
            deleteButton.setVisibility(View.VISIBLE);
           Dog dog=Saving.getAdogByPosition(position,getActivity());

           if (whichone==1){
               dateshower.setText(dog.getOne_medicine_date());
           }
           else{
               dateshower.setText(dog.getTwo_medicine_date());
           }

        }



      deleteButton.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              CancelAlarm();

          }
      });

        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (savingpossible){
                    setAlermandSaveDate();
                }

            }
        });

        datepicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar= Calendar.getInstance();
                int year= calendar.get(Calendar.YEAR);
                int month=calendar.get(Calendar.MONTH);
                int day=calendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog=new DatePickerDialog(getActivity(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,onDateSetListener
                        ,year,month,day);

                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

      showmonth.addTextChangedListener(new TextWatcher() {
          @Override
          public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

          }

          @Override
          public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

          }

          @Override
          public void afterTextChanged(Editable editable) {
             if (showmonth.getText().toString().equals("0")){

             }
             else{
                 if (showmonth.getText().toString().equals("")){

                 }
                 else{
                     month=Integer.parseInt(showmonth.getText().toString());
                       if (month>0){
                         setdate(month);
                     }
                 }




             }
          }
      });


        onDateSetListener=new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                int mon=i1+1;

                String date=i+"-"+mon+"-"+i2;
                datepicker.setText(date);
                startdate=date;
                possible=true;

                if (showmonth.getText().toString().equals("")){
                    Toast.makeText(getActivity(), "Set Month ", Toast.LENGTH_SHORT).show();
                }
                else {
                    setdate(month);
                }

            }
        };


        return  rootview;


    }

    private void CancelAlarm() {
        Intent intent = new Intent(getActivity(), MyBroadCastReciver.class);
        int reqcode=getRequestCode(position,whichone);

        PendingIntent pendingIntent=PendingIntent.getBroadcast(getActivity(),
                reqcode,intent,0 );
        AlarmManager alarmManager= (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(pendingIntent);

        Toast.makeText(getActivity(), "Date Deleted!! ", Toast.LENGTH_SHORT).show();
        Dog dog=Saving.getAdogByPosition(position,getActivity());

        if (whichone==1){
            dog.setOne_medicine_name(dog.getTwo_medicine_name());
            dog.setOne_medicine_date(dog.getTwo_medicine_date());
            dog.setTwo_medicine_name("NULL");
            dog.setTwo_medicine_date("NULL");
        }
        else{
            dog.setTwo_medicine_name("NULL");
            dog.setTwo_medicine_date("NULL");
        }

        Saving.SaveADog(dog,getActivity(),position);

        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.Fragment_contatiner,new Fragment_Home())
                .commit();


    }

    private void setAlermandSaveDate() {
        SaveDogInfo();
        Intent intent = new Intent(getActivity(), MyBroadCastReciver.class);
        //i just add 60 with the dog position to make a request code;

        DateTime dogalarmDate= formatter.parseDateTime(finaldate);

        long diffinmillisec=dogalarmDate.getMillis()-localTime.getMillis();
        int reqcode=getRequestCode(position,whichone);

        PendingIntent pendingIntent=PendingIntent.getBroadcast(getActivity(),
                reqcode,intent,0 );

        AlarmManager alarmManager= (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(alarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+diffinmillisec,pendingIntent);





    }

    private void setdate(int month) {

        DateTime finaldatee;

        DateTime dt = formatter.parseDateTime(startdate);
        finaldatee= dt.plusMonths(month);
        finaldate=String.valueOf(formatter.print(finaldatee));
        dateshower.setText(finaldate);

        possible=true;




    }

     private void SaveDogInfo(){
        Dog dog= Saving.getAdogByPosition(position,getActivity());
        if (dog.getOne_medicine_name().equals("NULL")){
            dog.setOne_medicine_name(name);
            dog.setOne_medicine_date(finaldate);
            whichone=1;
        }
        else{
            whichone=2;
            dog.setTwo_medicine_name(name);
            dog.setTwo_medicine_date(finaldate);
        }
        Saving.SaveADog(dog,getActivity(),position);
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.Fragment_contatiner,new Fragment_Doglist())
                .commit();

     }


     private int getRequestCode(int position, int which){
        int requestcode=0;
        requestcode=(5*position)+(60)+which;

        return requestcode;

     }
}
