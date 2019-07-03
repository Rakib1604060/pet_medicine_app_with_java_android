package com.elanco.elancoapps.FirstButton;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.widget.Toast;

import com.elanco.elancoapps.MainActivity;
import com.elanco.elancoapps.NotificationHelper;
import com.elanco.elancoapps.R;

import androidx.core.app.NotificationCompat;

import static android.content.Context.NOTIFICATION_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class MyBroadCastReciver extends BroadcastReceiver {


    private static final String PRIMARY_CHANNEL_ID = "primary_notification_channel";
    private NotificationManager mNotifyManager;
    private NotificationHelper notificationHelper;


    @Override
    public void onReceive(Context context, Intent intent) {
        notificationHelper=new NotificationHelper(context);
        NotificationCompat.Builder nb= notificationHelper.getChannelNotification("ATTENTION!!!","ONE OF YOUR PET MEDICINE WIll FINISH TOMORROW!!");
        notificationHelper.getManager().notify(1,nb.build());
        Toast.makeText(context, "Alarm....", Toast.LENGTH_LONG).show();

    }
}
