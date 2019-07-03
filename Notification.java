package com.elanco.elancoapps;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.service.autofill.SaveInfo;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.elanco.elancoapps.FirstButton.Dog;
import com.elanco.elancoapps.FirstButton.MyBroadCastReciver;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;

public class Notification extends AppCompatActivity {
 DateTimeFormatter formatter;

 Button yesButton,NoButton;
  boolean isFound=false;
    int which ;
    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        formatter= DateTimeFormat.forPattern("yyyy-MM-dd");

        yesButton=findViewById(R.id.yesButton);
        NoButton=findViewById(R.id.noButton);





        for (int i=0;i<4;i++){
            Dog dog ;

            dog= Saving.getAdogByPosition(i,this);

            if (!dog.getOne_medicine_date().equals("NULL")){

                int days=getIntervalDays(1,dog);
                if (days==0||days<=0){
                    which=1;
                    position=i;
                    isFound=true;
                    break;


                }
                days=getIntervalDays(2,dog);
                if (days==0||days<=0){

                    which=2;
                    position=i;
                    isFound=true;
                    break;
                }

            }




        }
        NoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setAlermandSaveDate();
                finish();

            }
        });

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteHistory();
                Intent intent=new Intent(Notification.this,MainActivity.class);
                startActivity(intent);
                finish();

            }
        });


    }

    private void DeleteHistory() {
        Dog dog=Saving.getAdogByPosition(position,this);
        if (which==1){
            dog.setOne_medicine_name(dog.getTwo_medicine_name());
            dog.setOne_medicine_date(dog.getTwo_medicine_date());
            dog.setTwo_medicine_name("NULL");
            dog.setTwo_medicine_date("NULL");
        }
        else{
            dog.setTwo_medicine_name("NULL");
            dog.setTwo_medicine_date("NULL");
        }

        Saving.SaveADog(dog,this,position);


    }

    private Integer getIntervalDays(int i,Dog dog) {
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


        return day;


    }
    private void setAlermandSaveDate() {

        Intent intent = new Intent(this, MyBroadCastReciver.class);
        //i just add 60 with the dog position to make a request code;

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        calendar.set(Calendar.HOUR_OF_DAY, 21);


        int reqcode=getRequestCode(position,which);

        PendingIntent pendingIntent=PendingIntent.getBroadcast(this,
                reqcode,intent,0 );

        AlarmManager alarmManager= (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.setExact(alarmManager.ELAPSED_REALTIME_WAKEUP, calendar.getTimeInMillis(),pendingIntent);



    }
    private int getRequestCode(int position, int which){
        int requestcode=0;
        requestcode=(5*position)+(60)+which;

        return requestcode;

    }


}