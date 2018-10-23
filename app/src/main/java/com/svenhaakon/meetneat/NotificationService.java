package com.svenhaakon.meetneat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;


public class NotificationService extends Service {

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(getApplicationContext(), "I MinService", Toast.LENGTH_SHORT).show();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        Intent i = new Intent(this, PersonHandler.class);
        PendingIntent pintent = PendingIntent.getActivity(this, 0, i, 0);
        Notification notification = new Notification.Builder(this)
                .setContentTitle("Test")
                .setContentText("Testtekst")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pintent)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        notificationManager.notify(0,notification);






        return super.onStartCommand(intent, flags, startId);
    }

}
