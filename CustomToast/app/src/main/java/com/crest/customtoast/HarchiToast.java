package com.crest.customtoast;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

/**
 * Created by Hector on 7/24/17.
 */

public class HarchiToast implements Animation.AnimationListener {

    private Animation animFadein, animFadeOut, animFadeUp, animFadeDown;
    private Context context;

    HarchiToast(Context context) {
        this.context = context;
        animFadein = AnimationUtils.loadAnimation(context,
                R.anim.fade_in);
        animFadeOut = AnimationUtils.loadAnimation(context,
                R.anim.fade_out);
        animFadeUp = AnimationUtils.loadAnimation(context,
                R.anim.fade_up);
        animFadeDown = AnimationUtils.loadAnimation(context,
                R.anim.fade_down);
        animFadein.setAnimationListener(this);
        animFadeOut.setAnimationListener(this);
        animFadeUp.setAnimationListener(this);
        animFadeDown.setAnimationListener(this);
    }

    void showToast(String msg, final TextView txtMessageToast) {
        txtMessageToast.setVisibility(View.VISIBLE);
        txtMessageToast.startAnimation(animFadein);
        txtMessageToast.setText(msg);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txtMessageToast.startAnimation(animFadeOut);
                txtMessageToast.setVisibility(View.GONE);
            }
        }, 2000);
    }

    void showToastUD(String msg, final TextView txtMessageToast) {
        txtMessageToast.setVisibility(View.VISIBLE);
        txtMessageToast.startAnimation(animFadeUp);
        txtMessageToast.setText(msg);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                txtMessageToast.startAnimation(animFadeDown);
                txtMessageToast.setVisibility(View.GONE);
            }
        }, 2000);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}
