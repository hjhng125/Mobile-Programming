package com.example.user.myapplication;

import android.app.AlarmManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class TimeReceiver extends BroadcastReceiver {
    Intent myIntent;
    public void onReceive(Context context, Intent intent) {
        // TODO: This method is called when the BroadcastReceiver is receiving
        // an Intent broadcast.
        Log.d("test1","test1");
        myIntent = new Intent(context, AlarmService.class);
        context.startService(myIntent); //인텐트값으로 서비스를 콜
    }
}
