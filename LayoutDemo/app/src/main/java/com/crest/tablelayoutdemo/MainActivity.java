package com.crest.tablelayoutdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.GridLayout;
import android.widget.RelativeLayout;
import android.widget.TableLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView txtLL, txtRl, txtFl, txtGl;
    TableLayout table_layout;
    RelativeLayout relativeLayout;
    FrameLayout frameLayout;
    GridLayout gridLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        table_layout = (TableLayout) findViewById(R.id.table_layout);
        relativeLayout = (RelativeLayout) findViewById(R.id.relativeLayout);
        frameLayout = (FrameLayout) findViewById(R.id.frameLayout);
        gridLayout = (GridLayout) findViewById(R.id.gridLayout);
        txtFl = (TextView) findViewById(R.id.txtFrameLayout);
        txtGl = (TextView) findViewById(R.id.txtGridLayout);
        txtLL = (TextView) findViewById(R.id.txtLinearLayout);
        txtRl = (TextView) findViewById(R.id.txtRelativeLayout);

        txtFl.setOnClickListener(this);
        txtLL.setOnClickListener(this);
        txtGl.setOnClickListener(this);
        txtRl.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.txtFrameLayout:
                if (table_layout.getVisibility() == View.VISIBLE ||
                        relativeLayout.getVisibility() == View.VISIBLE ||
                        gridLayout.getVisibility() == View.VISIBLE) {
                    table_layout.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.GONE);
                    gridLayout.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.txtGridLayout:
                if (table_layout.getVisibility() == View.VISIBLE ||
                        relativeLayout.getVisibility() == View.VISIBLE ||
                        frameLayout.getVisibility() == View.VISIBLE) {
                    table_layout.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.GONE);
                    gridLayout.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.txtLinearLayout:
                Toast.makeText(this, "No Layout Avail", Toast.LENGTH_SHORT).show();

                break;
            case R.id.txtRelativeLayout:
                if (table_layout.getVisibility() == View.VISIBLE ||
                        frameLayout.getVisibility() == View.VISIBLE ||
                        gridLayout.getVisibility() == View.VISIBLE) {
                    table_layout.setVisibility(View.GONE);
                    relativeLayout.setVisibility(View.VISIBLE);
                    gridLayout.setVisibility(View.GONE);
                    frameLayout.setVisibility(View.GONE);
                }
                break;
        }
    }
}
