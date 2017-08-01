package com.study.yaroslavambrozyak.scheduleme;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent intent1 = new Intent(context,NotificationService.class);
        context.startService(intent1);
    }
}
