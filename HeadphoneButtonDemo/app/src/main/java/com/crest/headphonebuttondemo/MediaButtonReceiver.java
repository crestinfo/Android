package com.crest.headphonebuttondemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.widget.Toast;

/*
 * Created by Hector on 7/28/17.
*/

public class MediaButtonReceiver extends BroadcastReceiver {

    public MediaButtonReceiver() {
        super();
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String intentAction = intent.getAction();
        if (!Intent.ACTION_MEDIA_BUTTON.equals(intentAction)) {
            return;
        }
        KeyEvent event = (KeyEvent) intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
        if (event == null) {
            return;
        }
        int action = event.getAction();
        if (action == KeyEvent.ACTION_DOWN) {
            Toast.makeText(context, "Action Received!", Toast.LENGTH_SHORT).show();
        }
        abortBroadcast();
    }
}
