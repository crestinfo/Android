package com.crest.rclapp;

import android.Manifest;
import android.app.Dialog;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    EditText edtMobile;
    Button btnRemove, btnAddLog;
    Dialog dialog;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtMobile = (EditText) findViewById(R.id.edtMobile);
        btnRemove = (Button) findViewById(R.id.btnRemove);
        btnAddLog = (Button) findViewById(R.id.btnAddLog);
        btnRemove.setOnClickListener(this);
        btnAddLog.setOnClickListener(this);


        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_CALL_LOG, Manifest.permission.READ_CALL_LOG, Manifest.permission.READ_CONTACTS, Manifest.permission.WRITE_CONTACTS}, 100);
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnRemove:
                if (TextUtils.isEmpty(edtMobile.getText().toString()))
                    Toast.makeText(this, "Please Enter Mobile Number", Toast.LENGTH_SHORT).show();
                else
                    DeleteNumFromCallLog(edtMobile.getText().toString());
                break;
            case R.id.btnAddLog:
                showAddLogDialog();
                break;
        }
    }

    private void showAddLogDialog() {
        dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setLayout(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
        dialog.setContentView(R.layout.dialog_add_log);
        final EditText edtMobile = (EditText) dialog.findViewById(R.id.edtMobileNumber);
        Button btnAddLog = (Button) dialog.findViewById(R.id.btnAddLogDialog);
        Button btnCancel = (Button) dialog.findViewById(R.id.btnCancelDialog);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        btnAddLog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (TextUtils.isEmpty(edtMobile.getText().toString())) {
                    Toast.makeText(MainActivity.this, "Please enter mobile number", Toast.LENGTH_SHORT).show();
                } else {
                    AddNumToCallLog(edtMobile.getText().toString(), CallLog.Calls.INCOMING_TYPE);
                }
            }
        });
        dialog.show();
    }


    public void DeleteNumFromCallLog(String strNum) {
        try {
            ContentResolver resolver = getContentResolver();
            String strUriCalls = "content://call_log/calls";
            Uri UriCalls = Uri.parse(strUriCalls);
            if (null != resolver) {
                resolver.delete(UriCalls, CallLog.Calls.NUMBER + "=?", new String[]{strNum});
            }
            Toast.makeText(this, "Number deleted from Log", Toast.LENGTH_SHORT).show();
            View view = this.getCurrentFocus();
            if (view != null) {
                InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void AddNumToCallLog(String strNum, int type) {
        long callTimeInMiliSecond = System.currentTimeMillis(); //time stamp
        ContentResolver resolver = getContentResolver();
        while (strNum.contains("-")) {
            strNum = strNum.substring(0, strNum.indexOf('-')) + strNum.substring(strNum.indexOf('-') + 1, strNum.length());
        }
        ContentValues values = new ContentValues();
        values.put(CallLog.Calls.NUMBER, strNum);
        values.put(CallLog.Calls.DATE, callTimeInMiliSecond);
        values.put(CallLog.Calls.DURATION, 0);
        values.put(CallLog.Calls.TYPE, type);
        values.put(CallLog.Calls.NEW, 1);
        values.put(CallLog.Calls.CACHED_NAME, "");
        values.put(CallLog.Calls.CACHED_NUMBER_TYPE, 0);
        values.put(CallLog.Calls.CACHED_NUMBER_LABEL, "");
        if (null != resolver) {
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
                return;
            }
            resolver.insert(CallLog.Calls.CONTENT_URI, values);
            dialog.dismiss();
            Log.e("Values", "AddNumToCallLog: " + values);
        }
        Toast.makeText(this, "Number added to log.", Toast.LENGTH_SHORT).show();
    }

}
