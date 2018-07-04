package com.utils;

import android.app.Service;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.util.Log;

import okhttp3.HttpUrl;
import okhttp3.Request;

/**
 * Created by Hector on 9/5/17.
 */

public class ForegroundService extends Service {
    private static final String LOG_TAG = "ForegroundService";
    SharedPreferences sharedPreferences;
    String TAG = "ForegroundService ";
    private final static int INTERVAL = 1500 * 60; //1.5 minutes
    Handler mHandler = new Handler();

    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ");
        mHandlerTask.run();
        return START_STICKY;
    }

    Runnable mHandlerTask = new Runnable() {
        @Override
        public void run() {
            CheckUserOnline();
            mHandler.postDelayed(mHandlerTask, INTERVAL);
        }
    };

    @Override
    public void onDestroy() {
        Log.e(TAG, "onDestroy: ");
        stopSelf();
        super.onDestroy();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // Used only in case if services are bound (Bound Services).
        return null;
    }

    private void CheckUserOnline() {
        Log.e("hector Service Call", "");
    }
}
