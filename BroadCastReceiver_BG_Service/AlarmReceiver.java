package com.hector.multiscreensupportdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by Hector on 11/29/17.
 */

public class AlarmReceiver extends BroadcastReceiver {
    String TAG = "AlarmReceiver";

    @Override
    public void onReceive(Context arg0, Intent arg1) {
        // For our recurring task, we'll just display a message
        Log.e(TAG, "onReceive: I'm running ");

        DateFormat df = new SimpleDateFormat("HH:mm");
        String date = df.format(Calendar.getInstance().getTime());

        if (date.equals("18:31")) {
            Log.e(TAG, "onReceive: true" + date);
        } else {
            Log.e(TAG, "onReceive: else" + date);
        }

    }
}