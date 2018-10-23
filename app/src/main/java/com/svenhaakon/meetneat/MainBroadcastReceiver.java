package com.svenhaakon.meetneat;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class MainBroadcastReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent){
        Log.d("Receiver", "Receiver");
        //Toast.makeText(context, "I BroadcastReceiver", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(context, SetPeriodicService.class);
        context.startService(i);

    }
}
