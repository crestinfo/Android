package com.crest.customtoast;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button btnShowToast, btnShowToastUD;
    TextView txtMessageToast;
    HarchiToast harchiToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnShowToast = (Button) findViewById(R.id.btnShowToastLR);
        btnShowToastUD = (Button) findViewById(R.id.btnShowToastUD);
        txtMessageToast = (TextView) findViewById(R.id.txtMessageToast);
        harchiToast = new HarchiToast(getApplicationContext());

        btnShowToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                harchiToast.showToast("You Toast", txtMessageToast);
            }
        });
        btnShowToastUD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                harchiToast.showToastUD("You Toast Up Down", txtMessageToast);
            }
        });
    }
}
