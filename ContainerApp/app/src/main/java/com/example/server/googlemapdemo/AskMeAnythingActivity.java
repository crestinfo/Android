package com.example.server.googlemapdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class AskMeAnythingActivity extends AppCompatActivity {
    EditText edtQue;
    Button btnSubmit;
    TextView txtAnswer;
    String[] ansArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ask_me_anything);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        edtQue = (EditText) findViewById(R.id.edtQuestion);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        txtAnswer = (TextView) findViewById(R.id.txtAnswer);
        ansArray = getResources().getStringArray(R.array.answers);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtQue.getText().toString().equals("")) {
                    txtAnswer.setText(getResources().getString(R.string.edittextnull_error));
                } else {
                    String randomStr = ansArray[new Random().nextInt(ansArray.length)];
                    txtAnswer.setText(randomStr);
                    edtQue.setText("");
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(edtQue.getWindowToken(), 0);
                }
            }
        });
    }
}
