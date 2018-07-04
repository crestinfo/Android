package com.Hector;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.Hector.Fragments.ActivityFragment;
import com.Hector.Fragments.SportsFragment;
import com.Hector.Fragments.DramaFragment;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {
    String TAG = "MainActivity";
    DrawerLayout drawer;
    
    ActivityFragment activityFragment = new ActivityFragment();
    SportsFragment sportsFragment = new SportsFragment();
    DramaFragment dramaFragment = new DramaFragment();
    FragmentManager mfragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mfragmentManager = getSupportFragmentManager();


        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setItemIconTintList(null);
        navigationView.setNavigationItemSelectedListener(this);

        if (savedInstanceState == null) {
            initHomeFragment();
        }

        CustomActionBar();
    }

    public void CustomActionBar() {
        txtActionbarTitle = (TextView) findViewById(R.id.txtActionbarTitle);
        txtActionbarTitle.setText(getResources().getString(R.string.app_name));
        icon_navigation = (ImageView) findViewById(R.id.icon_navigation);
        icon_navigation.setOnClickListener(this);
    }

    @Override
    public void onBackPressed() {
        Log.e(TAG, "onBackPressed: count" + getSupportFragmentManager().getBackStackEntryCount());
        if (drawer.isDrawerOpen(GravityCompat.END)) {  /*Closes the Appropriate Drawer*/
            drawer.closeDrawer(GravityCompat.END);
        } else if (getSupportFragmentManager().getBackStackEntryCount() > 1) {
            getSupportFragmentManager().popBackStack();
        } else if (getSupportFragmentManager().getBackStackEntryCount() == 1) {
            finish();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();

         if (id == R.id.nav_Activity) {
            initActivityFragment();
        } else if (id == R.id.nav_Sports) {
            initSportFragment();
        } else if (id == R.id.nav_Drama) {
            initDramaFragment();
        } 
        drawer.closeDrawer(GravityCompat.END);
        return true;
    }

    private void initSportsFragment() {
        if (sportsFragment == null) {
            sportsFragment = SportsFragment.newInstance();
        }
        if (!sportsFragment.isAdded()) {
            mfragmentManager.beginTransaction().replace(R.id.content_main, sportsFragment).setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                    android.R.anim.slide_in_left, android.R.anim.slide_out_right).addToBackStack(null).commit();
        }
    }
    private void initActivityFragment() {
        if (activityFragment == null) {
            activityFragment = ActivityFragment.newInstance();
        }
        if (!activityFragment.isAdded()) {
            mfragmentManager.beginTransaction().replace(R.id.content_main, activityFragment).setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                    android.R.anim.slide_in_left, android.R.anim.slide_out_right).addToBackStack(null).commit();
        }
    }
    private void initDramaFragment() {
        if (dramaFragment == null) {
            dramaFragment = DramaFragment.newInstance();
        }
        if (!dramaFragment.isAdded()) {
            mfragmentManager.beginTransaction().replace(R.id.content_main, dramaFragment).setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                    android.R.anim.slide_in_left, android.R.anim.slide_out_right).addToBackStack(null).commit();
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.icon_navigation:
                drawer.openDrawer(GravityCompat.END);
                break;
        }
    }
}
