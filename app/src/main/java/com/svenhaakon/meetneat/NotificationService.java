package com.svenhaakon.meetneat;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.telephony.SmsManager;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class NotificationService extends Service {
    DbHandler db;
    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        db = new DbHandler(this);

        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();



        //Find all reservations for today and add them to todayList
        List<Reservation> list = db.getAllReservations();
        List<Reservation> todaylist = new ArrayList<>();
        for(Reservation res : list){
            if(res.getDate().equals(formatter.format(date))){
                todaylist.add(res);
            }
        }

        //Checks if SMS service is on/off in preferences
        //If it is on gets default sms message from sharedpreferences and sends to all friends who have a reservation today
        if(PreferenceManager.getDefaultSharedPreferences(this).getBoolean("onOffPref", false)){
            SmsManager sms= SmsManager.getDefault();

            for(Reservation res : todaylist){
                sms.sendTextMessage(db.findPerson((int)res.getPerson_ID()).getPhone(), null, PreferenceManager.getDefaultSharedPreferences(this).getString("SMS", ""), null, null);
                Toast.makeText(this, "Sent SMS to: " + db.findPerson((int)res.getPerson_ID()).getPhone(), Toast.LENGTH_SHORT).show();
                Log.d("Sending SMS to", db.findPerson((int)res.getPerson_ID()).getPhone());
            }
        }

        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        for(Reservation res : todaylist){
            Notification notification = new Notification.Builder(this)
                    .setContentTitle("Appointment today!")
                    .setContentText("Appointment today at " + db.findRestaurant((int)res.getRestaurant_ID()).getName()
                    + " at " + res.getTime() + "with " + db.findPerson((int) res.getPerson_ID()).getName() + ".")
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentIntent(PendingIntent.getActivity(this, 0,
                            new Intent(this, PersonHandler.class), 0))
                    .build();
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            notificationManager.notify(0,notification);
        }








        return super.onStartCommand(intent, flags, startId);
    }

}
