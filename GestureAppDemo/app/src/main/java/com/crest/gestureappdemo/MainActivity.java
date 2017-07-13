package com.crest.gestureappdemo;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity implements View.OnTouchListener {

    public static final String TAG = MainActivity.class.getSimpleName();

    int clickCount;
    private ViewGroup RootLayout;
    private int Position_X;
    private int Position_Y;
    long startTime = 0;
    Button btnAddImage;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RootLayout = (ViewGroup) findViewById(R.id.rootLayout);
        //new image
        btnAddImage = (Button) findViewById(R.id.new_image_button);
        imageView = (ImageView) findViewById(R.id.imageview);
        btnAddImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Add_Image();
            }
        });
        clickCount = 0;


        /*imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {

            public void onGlobalLayout() {
                imgPosX = imageView.getLeft();
                imgPosY = imageView.getTop();
                Log.e(TAG, "onGlobalLayout imgPosX : " + imgPosX);
                Log.e(TAG, "onGlobalLayout imgPosY : " + imgPosY);
                //don't forget to remove the listener to prevent being called again by future layout events:
                imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            }
        });*/


    }

    private void Add_Image() {
        final ImageView iv = new ImageView(this);
        iv.setImageResource(R.mipmap.ic_launcher);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(150, 150);
        iv.setLayoutParams(layoutParams);
        RootLayout.addView(iv, layoutParams);
        iv.setOnTouchListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public boolean onTouch(final View view, MotionEvent motionEvent) {
        final int X = (int) motionEvent.getRawX();
        final int Y = (int) motionEvent.getRawY();
        int pointerCount = motionEvent.getPointerCount();
        switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) view.getLayoutParams();
                Position_X = X - layoutParams.leftMargin;
                Position_Y = Y - layoutParams.topMargin;
                break;
            case MotionEvent.ACTION_UP:
                if (startTime == 0) {
                    startTime = System.currentTimeMillis();
                } else {
                    if (System.currentTimeMillis() - startTime < 200) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                        builder.setMessage("Are you sure you want to delete this?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                view.setVisibility(View.GONE);
                            }
                        });
                        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                    startTime = System.currentTimeMillis();
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (pointerCount == 1) {
                    RelativeLayout.LayoutParams Params = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    Params.leftMargin = X - Position_X;
                    Params.topMargin = Y - Position_Y;
                    Params.rightMargin = -500;
                    Params.bottomMargin = -500;
                    view.setLayoutParams(Params);
                    if (Params.leftMargin > 550 && Params.topMargin > 850) {
                        view.setVisibility(View.GONE);
                    }


                    /*view.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                        public void onGlobalLayout() {
                            viewPosX = view.getLeft();
                            viewPosY = view.getTop();
                            Log.e(TAG, "onGlobalLayout viewPosX : " + viewPosX);
                            Log.e(TAG, "onGlobalLayout viewPosY : " + viewPosY);
                            view.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                        }
                    });

                    if (imgPosX == viewPosX && imgPosY == viewPosY) {
                        view.setVisibility(View.GONE);
                    }*/
                }

                if (pointerCount == 2) {
                    RelativeLayout.LayoutParams layoutParams1 = (RelativeLayout.LayoutParams) view.getLayoutParams();
                    layoutParams1.width = Position_X + (int) motionEvent.getX();
                    layoutParams1.height = Position_Y + (int) motionEvent.getY();
                    view.setLayoutParams(layoutParams1);
                }

                //Rotation
                if (pointerCount == 3) {
                    view.setRotation(view.getRotation() + 10.0f);
                }

                break;
        }
        RootLayout.invalidate();
        return true;
    }
}
