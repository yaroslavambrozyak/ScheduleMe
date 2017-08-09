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
        serviceIntent.putExtra(Constant.MUSIC_ENABLE,intent.getBooleanExtra(Constant.MUSIC_ENABLE,true));
        serviceIntent.putExtra(Constant.VIBRATION_ENABLE,intent.getBooleanExtra(Constant.VIBRATION_ENABLE,true));
        context.startService(serviceIntent);
    }
}
