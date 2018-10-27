package com.svenhaakon.meetneat;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.util.Log;

import java.util.Calendar;

public class SetPeriodicService extends Service {

    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId){
        //Stop old alarm when adding a new one
        Intent oldintent = new Intent(this, NotificationService.class);
        PendingIntent oldpintent = PendingIntent.getService(this,0,oldintent,0);
        AlarmManager oldalarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        if(oldalarm!=null){
            Log.d("OldService", "Stopping an old alarm!");
            oldalarm.cancel(oldpintent);
        }

        //Find time specified in preferences and converts it to hour and minute
        String notificationPref = PreferenceManager.getDefaultSharedPreferences(this).getString("notificationPref", "08:00");
        int alertHour = Integer.parseInt(notificationPref.substring(0, 2));
        int alertMinute = Integer.parseInt(notificationPref.substring(3, 5));

        //Create a calender object and put the info in
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, alertHour);
        calendar.set(Calendar.MINUTE, alertMinute);
        calendar.set(Calendar.SECOND, 00);

        //Check if current time is past time specified in prefs, if so the alarm will start next day instead.
        long startUpTime = calendar.getTimeInMillis();
        if (System.currentTimeMillis() > startUpTime) {
            startUpTime = startUpTime + 24*60*60*1000;
        }
        Log.d("NyService", "I am a new periodic service!");
        Intent i = new Intent(this, NotificationService.class);
        PendingIntent pintent = PendingIntent.getService(this,0, i,0);
        AlarmManager alarm = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, startUpTime, AlarmManager.INTERVAL_DAY, pintent);
        return super.onStartCommand(intent, flags, startId);
    }


}
