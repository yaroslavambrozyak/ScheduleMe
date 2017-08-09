package com.study.yaroslavambrozyak.scheduleme.utils;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent serviceIntent = new Intent(context,NotificationService.class);
        serviceIntent.putExtra(Constant.REMIND_TITLE,intent.getStringExtra(Constant.REMIND_TITLE));
        serviceIntent.putExtra(Constant.REMIND_DESCRIPTION,intent.getStringExtra(Constant.REMIND_DESCRIPTION));
        context.startService(serviceIntent);
    }
}
