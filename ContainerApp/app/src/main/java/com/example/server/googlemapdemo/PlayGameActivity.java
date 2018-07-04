package com.example.server.googlemapdemo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PlayGameActivity extends AppCompatActivity {

    int flag = 0;
    boolean won = false;
    Button btnStopPlay;
    Button btn[][] = new Button[3][3];
    int winCondition[][] = new int[3][3];
    int width, height;
    int count = 0;
    LinearLayout layoutGame, firstLayout, secondLayout, thirdLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_game);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Enjoy Playing Game");

        btnStopPlay = (Button) findViewById(R.id.btnStopPlay);
        layoutGame = (LinearLayout) findViewById(R.id.layout_game);
        firstLayout = (LinearLayout) findViewById(R.id.layoutFirstRow);
        secondLayout = (LinearLayout) findViewById(R.id.layoutSecondRow);
        thirdLayout = (LinearLayout) findViewById(R.id.layoutThirdRow);

        width = getWindowManager().getDefaultDisplay().getWidth();
        height = width / 3;
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        firstLayout.setLayoutParams(params);
        secondLayout.setLayoutParams(params);
        thirdLayout.setLayoutParams(params);
        layoutGame.setGravity(Gravity.CENTER);

        btn[0][0] = (Button) findViewById(R.id.btnOne);
        btn[0][1] = (Button) findViewById(R.id.btnTwo);
        btn[0][2] = (Button) findViewById(R.id.btnThree);

        btn[1][0] = (Button) findViewById(R.id.btnFour);
        btn[1][1] = (Button) findViewById(R.id.btnFive);
        btn[1][2] = (Button) findViewById(R.id.btnSix);

        btn[2][0] = (Button) findViewById(R.id.btnSeven);
        btn[2][1] = (Button) findViewById(R.id.btnEight);
        btn[2][2] = (Button) findViewById(R.id.btnNine);


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                final int finalI = i;
                final int finalJ = j;
                btn[i][j].setBackgroundResource(0);
                btn[i][j].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (flag == 0) {
                            btn[finalI][finalJ].setTextColor(Color.BLACK);
                            btn[finalI][finalJ].setText("X");
                            btn[finalI][finalJ].setBackgroundResource(R.drawable.ic_multiply);
                            winCondition[finalI][finalJ] = 0;
                            Log.e("posotion", "onClick: " + finalI + ":" + finalJ);
                            if (btn[0][0].getText().toString().equals("X") && btn[0][1].getText().toString().equals("X") && btn[0][2].getText().toString().equals("X") ||
                                    btn[1][0].getText().toString().equals("X") && btn[1][1].getText().toString().equals("X") && btn[1][2].getText().toString().equals("X") ||
                                    btn[2][0].getText().toString().equals("X") && btn[2][1].getText().toString().equals("X") && btn[2][2].getText().toString().equals("X") ||
                                    btn[0][0].getText().toString().equals("X") && btn[1][0].getText().toString().equals("X") && btn[2][0].getText().toString().equals("X") ||
                                    btn[0][1].getText().toString().equals("X") && btn[1][1].getText().toString().equals("X") && btn[2][1].getText().toString().equals("X") ||
                                    btn[0][2].getText().toString().equals("X") && btn[1][2].getText().toString().equals("X") && btn[2][2].getText().toString().equals("X") ||
                                    btn[0][0].getText().toString().equals("X") && btn[1][1].getText().toString().equals("X") && btn[2][2].getText().toString().equals("X") ||
                                    btn[0][2].getText().toString().equals("X") && btn[1][1].getText().toString().equals("X") && btn[2][0].getText().toString().equals("X")) {
                                checkWhoWon(flag);
                            }
                            btn[finalI][finalJ].setEnabled(false);
                            flag = 1;
                            count++;
                        } else if (flag == 1) {
                            btn[finalI][finalJ].setTextColor(Color.WHITE);
                            btn[finalI][finalJ].setText("0");
                            btn[finalI][finalJ].setBackgroundResource(R.drawable.ic_plain_circle);
                            //winCondition[finalI][finalJ] = 1;
                            if (btn[0][0].getText().toString().equals("0") && btn[0][1].getText().toString().equals("0") && btn[0][2].getText().toString().equals("0") ||
                                    btn[1][0].getText().toString().equals("0") && btn[1][1].getText().toString().equals("0") && btn[1][2].getText().toString().equals("0") ||
                                    btn[2][0].getText().toString().equals("0") && btn[2][1].getText().toString().equals("0") && btn[2][2].getText().toString().equals("0") ||
                                    btn[0][0].getText().toString().equals("0") && btn[1][0].getText().toString().equals("0") && btn[2][0].getText().toString().equals("0") ||
                                    btn[0][1].getText().toString().equals("0") && btn[1][1].getText().toString().equals("0") && btn[2][1].getText().toString().equals("0") ||
                                    btn[0][2].getText().toString().equals("0") && btn[1][2].getText().toString().equals("0") && btn[2][2].getText().toString().equals("0") ||
                                    btn[0][0].getText().toString().equals("0") && btn[1][1].getText().toString().equals("0") && btn[2][2].getText().toString().equals("0") ||
                                    btn[0][2].getText().toString().equals("0") && btn[1][1].getText().toString().equals("0") && btn[2][0].getText().toString().equals("0")) {
                                checkWhoWon(flag);
                            }
                            btn[finalI][finalJ].setEnabled(false);
                            flag = 0;
                            count++;
                        }
                        if (count == 9 && !won) {
                            gameOver();
                        }
                    }
                });
            }
        }
        btnStopPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void checkWhoWon(final int flagIn) {
        won = true;
        final Dialog dialog = new Dialog(PlayGameActivity.this);
        dialog.setContentView(R.layout.layout_game_winner_dialog);
        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        ImageView image = (ImageView) dialog.findViewById(R.id.imageWinnerDrawable);
        if (flagIn == 0) {
            image.setImageResource(R.drawable.ic_multiply);
        } else {
            image.setImageResource(R.drawable.ic_plain_circle);
        }

        Button btnPlayMore = (Button) dialog.findViewById(R.id.btnPlayMore);
        Button btnExitGame = (Button) dialog.findViewById(R.id.btnExitGame);
        btnPlayMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        btn[i][j].setText("");
                        btn[i][j].setEnabled(true);
                        btn[i][j].setBackgroundResource(0);
                    }
                }
                flag = 0;
                count = 0;
                dialog.dismiss();
            }
        });
        dialog.show();
        btnExitGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void gameOver() {

        AlertDialog.Builder builder = new AlertDialog.Builder(PlayGameActivity.this);
        builder.setMessage("Game Over No One Won !");
        builder.setPositiveButton("Play More", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        btn[i][j].setText("");
                        btn[i][j].setEnabled(true);
                        btn[i][j].setBackgroundResource(0);
                    }
                }
                dialog.dismiss();
            }
        });
        builder.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });
        builder.show();
    }
}
