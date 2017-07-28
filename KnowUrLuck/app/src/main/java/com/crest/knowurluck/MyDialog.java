package com.crest.knowurluck;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import static com.crest.knowurluck.MainActivity.btnNext;
import static com.crest.knowurluck.MainActivity.mViewPager;

/**
 * Created by Hector on 7/28/17.
 */

public class MyDialog extends DialogFragment {
    Button btnDone;
    TextView txtLuckCount;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.layoutdialogfragment, container, false);
        btnDone = (Button) rootView.findViewById(R.id.btnDone);
        int r = (int) (Math.random() * (100 - 50)) + 50;
        txtLuckCount = (TextView) rootView.findViewById(R.id.txtLuckCount);
        txtLuckCount.setText(String.valueOf(r) + "%");
        btnDone.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setMessage("Are you sure want to start again the Game!");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        getDialog().dismiss();
                        mViewPager.setCurrentItem(0);
                        btnNext.setBackgroundColor(getResources().getColor(R.color.colorbtn));
                        btnNext.setText("Next");
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        getDialog().dismiss();
                        getActivity().finish();
                    }
                });
                builder.show();
            }
        });
        return rootView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.MATCH_PARENT;
            dialog.getWindow().setLayout(width, height);
        }
    }
}
