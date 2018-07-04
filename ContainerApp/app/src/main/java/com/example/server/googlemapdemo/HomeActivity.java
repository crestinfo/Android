package com.example.server.googlemapdemo;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Vibrator;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v4.app.NotificationCompat;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

import com.example.server.googlemapdemo.Fragments.ImageUploadFragment;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    Button btnGoogle, btnApi, btnNotification, btnCamera, btnAskMe, btnGame, btnImageUpload;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btnGoogle = (Button) findViewById(R.id.btnGoogleAPP);
        btnApi = (Button) findViewById(R.id.btnAPICallAPP);
        btnNotification = (Button) findViewById(R.id.btnNotificationAPP);
        btnCamera = (Button) findViewById(R.id.btnCameraAPP);
        btnAskMe = (Button) findViewById(R.id.btnAskAnythingAPP);
        btnGame = (Button) findViewById(R.id.btnPlayGame);
        btnImageUpload = (Button) findViewById(R.id.btnImageUploadAPP);


        btnGoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomeActivity.this, MapsActivity.class);
                startActivity(in);
            }
        });
        btnAskMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomeActivity.this, AskMeAnythingActivity.class);
                startActivity(in);
            }
        });
        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomeActivity.this, CameraActivity.class);
                startActivity(in);
            }
        });
        btnNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler().postDelayed(new Runnable() {
                    /*
                     * Showing splash screen with a timer. This will be useful when you
                     * want to show case your app logo / company
                     */
                    @Override
                    public void run() {
                        sendNotification();
                    }
                }, 3000);
            }
        });
        btnApi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomeActivity.this, RetrofitApiCall.class);
                startActivity(in);
            }
        });
        btnGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(HomeActivity.this, PlayGameActivity.class);
                startActivity(in);
            }
        });
        btnImageUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.layout_replace, new ImageUploadFragment())
                        .commit();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        if (id == android.R.id.home) {
            NavUtils.navigateUpFromSameTask(this);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        if (id == R.id.nav_map) {
            Intent in = new Intent(HomeActivity.this, MapsActivity.class);
            startActivity(in);
        } else if (id == R.id.nav_apicall) {
            Intent in = new Intent(HomeActivity.this, RetrofitApiCall.class);
            startActivity(in);
        } else if (id == R.id.nav_notify) {
            Intent in = new Intent(HomeActivity.this, NotificationActivity.class);
            startActivity(in);
        } else if (id == R.id.nav_camera) {
            Intent in = new Intent(HomeActivity.this, CameraActivity.class);
            startActivity(in);
        } else if (id == R.id.nav_allAPPS) {
            Intent in = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(in);
        } else if (id == R.id.nav_askanything) {
            Intent in = new Intent(HomeActivity.this, AskMeAnythingActivity.class);
            startActivity(in);
        } else if (id == R.id.nav_palygame) {
            Intent in = new Intent(HomeActivity.this, PlayGameActivity.class);
            startActivity(in);
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_error_outline_black_24dp)
                .setContentTitle("Notifications Example")
                .setContentText("Congratulations");

        Intent notificationIntent = new Intent(this, MainActivity.class);
        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        builder.setContentIntent(contentIntent);
        // Add as notification
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(0, builder.build());
        // builder.setOnlyAlertOnce(true);
        //builder.setDefaults(Notification.DEFAULT_VIBRATE);
        Vibrator v = (Vibrator) this.getSystemService(Context.VIBRATOR_SERVICE);
        v.vibrate(500);
    }
}
