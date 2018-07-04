package com.crest.headphonebuttondemo;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    // MusicIntentReceiver myReceiver;
    MediaButtonReceiver intentReceiver;
    Button btnShowDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShowDialog = (Button) findViewById(R.id.btnShowDialog);
        btnShowDialog.setOnClickListener(this);
        IntentFilter filter = new IntentFilter(Intent.ACTION_MEDIA_BUTTON);//"android.intent.action.MEDIA_BUTTON"
        intentReceiver = new MediaButtonReceiver();
        filter.setPriority(1000);
        registerReceiver(intentReceiver, filter);
        //   myReceiver = new MusicIntentReceiver();
    }

    @Override
    protected void onResume() {
        super.onResume();
       /* IntentFilter filter = new IntentFilter(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(myReceiver, filter);*/
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnShowDialog:
                ShowDialogFrag();
                break;
        }
    }

    private void ShowDialogFrag() {
        MyDialog myDialog = new MyDialog();
        myDialog.show(getFragmentManager(), "MyDialog");
    }
    //Encode the Block to get the Receiver  to get the state of Headset Attached or Not
    /*private class MusicIntentReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(Intent.ACTION_HEADSET_PLUG)) {
                int state = intent.getIntExtra("state", -1);
                switch (state) {
                    case 0:
                        Toast.makeText(context, "Headset is unplugged", Toast.LENGTH_SHORT).show();
                        break;
                    case 1:
                        Toast.makeText(context, "Headset is plugged", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        Toast.makeText(context, "I have no idea what the headset state is", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }*/

}
