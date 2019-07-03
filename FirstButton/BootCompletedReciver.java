package com.elanco.elancoapps.FirstButton;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;

import androidx.core.view.KeyEventDispatcher;

public class BootCompletedReciver extends BroadcastReceiver {
    //when phone get off, the Reciver will call the service. and the service will
    //reset the alarm again.

    @Override
    public void onReceive(Context context, Intent intent) {
        if ("android.intent.action.BOOT_COMPLETED".equals(intent.getAction())) {
            Intent i = new Intent(context, ResetAlarmService.class);
            ComponentName service = context.startService(i);
        }

            }
}
