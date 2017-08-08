package com.study.yaroslavambrozyak.scheduleme.utils;


import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.net.Uri;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

import com.study.yaroslavambrozyak.scheduleme.R;
import com.study.yaroslavambrozyak.scheduleme.view.MainActivity;

public class NotificationService extends Service {

    private Intent intent;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        this.intent = intent;
        buildNotification();
        return super.onStartCommand(intent, flags, startId);
    }

    private void buildNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_calendar_clock_grey600_24dp);
        builder.setContentTitle(intent.getStringExtra(Constant.REMIND_TITLE));
        builder.setContentText(intent.getStringExtra(Constant.REMIND_DESCRIPTION));
        builder.setVibrate(new long[]{100, 100, 100, 100, 100});
        Uri uri = Uri.parse("android.resource://" + getPackageName() + "/raw/sound");
        builder.setSound(uri);
        Intent intent = new Intent(this, MainActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(pIntent);
        builder.setAutoCancel(true);
        NotificationManager manager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
    }
}
