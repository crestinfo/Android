package com.example.server.googlemapdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine;
    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        btnOne = (Button) findViewById(R.id.btnGameOne);
        btnTwo = (Button) findViewById(R.id.btnGameTwo);
        btnThree = (Button) findViewById(R.id.btnGameThree);
        btnFour = (Button) findViewById(R.id.btnGameFour);
        btnFive = (Button) findViewById(R.id.btnGameFive);
        btnSix = (Button) findViewById(R.id.btnGameSix);
        btnSeven = (Button) findViewById(R.id.btnGameSeven);
        btnEight = (Button) findViewById(R.id.btnGameEight);
        btnNine = (Button) findViewById(R.id.btnGameNine);

        btnOne.setOnClickListener(this);
        btnTwo.setOnClickListener(this);
        btnThree.setOnClickListener(this);
        btnFour.setOnClickListener(this);
        btnFive.setOnClickListener(this);
        btnSix.setOnClickListener(this);
        btnSeven.setOnClickListener(this);
        btnEight.setOnClickListener(this);
        btnNine.setOnClickListener(this);

        if (!btnOne.isEnabled() && !btnTwo.isEnabled() && !btnThree.isEnabled() &&
                !btnFour.isEnabled() && !btnFive.isEnabled() && !btnSix.isEnabled() &&
                !btnSeven.isEnabled() && !btnEight.isEnabled() && !btnNine.isEnabled()) {
            Toast.makeText(this, "Game Over", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        if (flag == 0) {
            switch (v.getId()) {
                case R.id.btnGameOne:
                    btnOne.setText("X");
                    btnOne.setEnabled(false);
                    flag = 1;
                    break;
                case R.id.btnGameTwo:
                    btnTwo.setText("X");
                    btnTwo.setEnabled(false);
                    flag = 1;
                    break;
                case R.id.btnGameThree:
                    btnThree.setText("X");
                    btnThree.setEnabled(false);
                    flag = 1;
                    break;
                case R.id.btnGameFour:
                    btnFour.setText("X");
                    btnFour.setEnabled(false);
                    flag = 1;
                    break;
                case R.id.btnGameFive:
                    btnFive.setText("X");
                    btnFive.setEnabled(false);
                    flag = 1;
                    break;
                case R.id.btnGameSix:
                    btnSix.setText("X");
                    btnSix.setEnabled(false);
                    flag = 1;
                    break;
                case R.id.btnGameSeven:
                    btnSeven.setText("X");
                    btnSeven.setEnabled(false);
                    flag = 1;
                    break;
                case R.id.btnGameEight:
                    btnEight.setText("X");
                    btnEight.setEnabled(false);
                    flag = 1;
                    break;
                case R.id.btnGameNine:
                    btnNine.setText("X");
                    btnNine.setEnabled(false);
                    flag = 1;
                    break;
            }
            if (!btnOne.isEnabled() && !btnTwo.isEnabled() && !btnThree.isEnabled() ||
                    !btnFour.isEnabled() && !btnFive.isEnabled() && !btnSix.isEnabled() ||
                    !btnSeven.isEnabled() && !btnEight.isEnabled() && !btnNine.isEnabled() ||
                    !btnOne.isEnabled() && !btnFour.isEnabled() && !btnSeven.isEnabled() ||
                    !btnTwo.isEnabled() && !btnFive.isEnabled() && !btnEight.isEnabled() ||
                    !btnThree.isEnabled() && !btnSix.isEnabled() && !btnNine.isEnabled() ||
                    !btnOne.isEnabled() && !btnFive.isEnabled() && !btnNine.isEnabled() ||
                    !btnThree.isEnabled() && !btnFive.isEnabled() && !btnSeven.isEnabled()) {
                Toast.makeText(this, "Winner X", Toast.LENGTH_SHORT).show();
            }
        } else {
            switch (v.getId()) {
                case R.id.btnGameOne:
                    btnOne.setText("0");
                    btnOne.setEnabled(false);
                    flag = 0;
                    break;
                case R.id.btnGameTwo:
                    btnTwo.setText("0");
                    btnTwo.setEnabled(false);
                    flag = 0;
                    break;
                case R.id.btnGameThree:
                    btnThree.setText("0");
                    btnThree.setEnabled(false);
                    flag = 0;
                    break;
                case R.id.btnGameFour:
                    btnFour.setText("0");
                    btnFour.setEnabled(false);
                    flag = 0;
                    break;
                case R.id.btnGameFive:
                    btnFive.setText("0");
                    btnFive.setEnabled(false);
                    flag = 0;
                    break;
                case R.id.btnGameSix:
                    btnSix.setText("0");
                    btnSix.setEnabled(false);
                    flag = 0;
                    break;
                case R.id.btnGameSeven:
                    btnSeven.setText("0");
                    btnSeven.setEnabled(false);
                    flag = 0;
                    break;
                case R.id.btnGameEight:
                    btnEight.setText("0");
                    btnEight.setEnabled(false);
                    flag = 0;
                    break;
                case R.id.btnGameNine:
                    btnNine.setText("0");
                    btnNine.setEnabled(false);
                    flag = 0;
                    break;
            }
            if (!btnOne.isEnabled() && !btnTwo.isEnabled() && !btnThree.isEnabled() ||
                    !btnFour.isEnabled() && !btnFive.isEnabled() && !btnSix.isEnabled() ||
                    !btnSeven.isEnabled() && !btnEight.isEnabled() && !btnNine.isEnabled() ||
                    !btnOne.isEnabled() && !btnFour.isEnabled() && !btnSeven.isEnabled() ||
                    !btnTwo.isEnabled() && !btnFive.isEnabled() && !btnEight.isEnabled() ||
                    !btnThree.isEnabled() && !btnSix.isEnabled() && !btnNine.isEnabled() ||
                    !btnOne.isEnabled() && !btnFive.isEnabled() && !btnNine.isEnabled() ||
                    !btnThree.isEnabled() && !btnFive.isEnabled() && !btnSeven.isEnabled()) {
                Toast.makeText(this, "Winner 0", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
