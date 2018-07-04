package com.example.brittany.broadcastdemoapp;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.NotificationCompat;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    String TAG = "MainActivity";
    BroadcastReceiver broadcastReceiver;
    ProgressBar progressBar;
    TextView txtPercent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        txtPercent = (TextView) findViewById(R.id.txtPercent);
        broadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                Bundle extras = intent.getExtras();
                if (extras != null) {

                    int level = intent.getIntExtra("level", 0);
                    progressBar.setProgress(level);
                    txtPercent.setText(Integer.toString(level) + "/100");
                    NotificationCompat.Builder builder = (NotificationCompat.Builder) new NotificationCompat.Builder(MainActivity.this)
                            .setContentTitle("Battery").setSmallIcon(R.mipmap.ic_launcher_round)
                            .setContentText("Battery % Changed to" + Integer.toString(level) + " %");

                    Intent notificationIntent = new Intent(MainActivity.this, MainActivity.class);
                    PendingIntent contentIntent = PendingIntent.getActivity(MainActivity.this, 0, notificationIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT);
                    builder.setContentIntent(contentIntent);
                    NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                    manager.notify(0, builder.build());
                }
            }
        };
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(broadcastReceiver, new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
    }

    /*@Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "onPause: Receiver Unregister");
        unregisterReceiver(broadcastReceiver);
    }*/
}
