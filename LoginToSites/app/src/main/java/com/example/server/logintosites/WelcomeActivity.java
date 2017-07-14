package com.example.server.logintosites;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.MainThread;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.Button;
import android.widget.TextView;

import com.facebook.login.LoginManager;
import com.linkedin.platform.LISessionManager;
import com.twitter.sdk.android.Twitter;

import org.w3c.dom.Text;

import java.util.Objects;

public class WelcomeActivity extends AppCompatActivity {
    TextView txtWelComeName, txtWelComeEmail;
    Button btnLogout;
    String greeting, name, email;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        txtWelComeName = (TextView) findViewById(R.id.txtWelComeName);
        txtWelComeEmail = (TextView) findViewById(R.id.txtWelComeEmail);
        btnLogout = (Button) findViewById(R.id.btnLogout);

        greeting = getIntent().getStringExtra("tag");
        name = getIntent().getStringExtra("name");
        email = getIntent().getStringExtra("email");

        Log.e("Wel Come Intent Data ", "" + greeting + " " + name + " " + email);

        if (Objects.equals(greeting, "facebook")) {
            txtWelComeName.setText("Wel Come To " + greeting + " " + name);
            if (email != null)
                txtWelComeEmail.setText("Email :" + email);
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LoginManager.getInstance().logOut();
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        } else if (Objects.equals(greeting, "twitter")) {
            txtWelComeName.setText("Wel Come To " + greeting + " " + name);
            if (email != null)
                txtWelComeEmail.setText("Email :" + email);
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CookieSyncManager.createInstance(WelcomeActivity.this);
                    CookieManager cookieManager = CookieManager.getInstance();
                    cookieManager.removeSessionCookie();
                    Twitter.getSessionManager().clearActiveSession();
                    Twitter.logOut();
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        } else if (Objects.equals(greeting, "linkedin")) {
            txtWelComeName.setText("Wel Come To " + greeting + " " + name);
            if (email != null)
                txtWelComeEmail.setText("Email :" + email);
            btnLogout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    LISessionManager.getInstance(getApplicationContext()).clearSession();
                    Intent intent = new Intent(WelcomeActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
