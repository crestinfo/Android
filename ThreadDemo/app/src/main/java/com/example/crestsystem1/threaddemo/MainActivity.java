package com.example.crestsystem1.threaddemo;

import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends Activity implements View.OnClickListener {

    private ProgressBar progressBar;
    private TextView statusText;
    private int completed;
    private Handler handler;

    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler();
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        statusText = (TextView) findViewById(R.id.status_text);
        progressBar.setMax(100);
        Button startButton = (Button) findViewById(R.id.start_button);
        startButton.setOnClickListener(this);
        Button resetButton = (Button) findViewById(R.id.reset_button);
        resetButton.setOnClickListener(this);
    }

    public void onClick(View source) {

        if (source.getId() == R.id.start_button) {
            startProgress(source);
        } else if (source.getId() == R.id.reset_button) {
            progressBar.setProgress(0);
        }

    }

    public void startProgress(View view) {
        progressBar.setProgress(0);
        new Thread(new Task()).start();
    }

    private class Task implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                progressBar.setProgress(i + 4);
            }
        }
    }
}