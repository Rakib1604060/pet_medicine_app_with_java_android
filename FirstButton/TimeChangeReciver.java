package com.elanco.elancoapps.FirstButton;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import android.widget.Toast;

import com.elanco.elancoapps.Saving;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

public class TimeChangeReciver extends BroadcastReceiver {
    DateTime currentDate=new DateTime();
    DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd");


    Context context;
    @Override
    public void onReceive(Context context, Intent intent) {
         this.context=context;

        Dog dog;
        //set all alarm to be continue again
        for (int i=0;i<4;i++){
            // get all dog information one by one
            dog= Saving.getAdogByPosition(i,context);

            if (!dog.getOne_medicine_date().equals("NULL")){
                resetAlarm(dog.getOne_medicine_date(),i);
            }
            if (!dog.getTwo_medicine_date().equals("NULL")){
                resetAlarm(dog.getTwo_medicine_date(),i);
            }


    }}

        private void resetAlarm(String one_medicine_date,int position) {
            //reset the alarm

            DateTime dogAlarmTIme=formatter.parseDateTime(one_medicine_date);

            Intent intent = new Intent(context, MyBroadCastReciver.class);
            //i just add 60 with the dog position to make a request code;

            PendingIntent pendingIntent=PendingIntent.getBroadcast(context,
                    position+60,intent,0 );

            long diffInmillisec=dogAlarmTIme.getMillis()-currentDate.getMillis();


            AlarmManager alarmManager= (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
            alarmManager.setExact(alarmManager.ELAPSED_REALTIME_WAKEUP, SystemClock.elapsedRealtime()+diffInmillisec,pendingIntent);
            Toast.makeText(context, "Alarm Updated!!", Toast.LENGTH_SHORT).show();


        }
}
