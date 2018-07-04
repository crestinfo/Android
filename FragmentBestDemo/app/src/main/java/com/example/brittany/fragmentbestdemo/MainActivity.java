package com.example.brittany.fragmentbestdemo;

import android.media.Image;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.ListViewAutoScrollHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.brittany.fragmentbestdemo.Frags.FiveFragment;
import com.example.brittany.fragmentbestdemo.Frags.FourFragment;
import com.example.brittany.fragmentbestdemo.Frags.OneFragment;
import com.example.brittany.fragmentbestdemo.Frags.ThreeFragment;
import com.example.brittany.fragmentbestdemo.Frags.TwoFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    DrawerLayout drawerLayout;
    ListView navList;
    ArrayList<String> menuModelList;
    RelativeLayout action_bar_main;
    ImageView humberger;
    OneFragment oneFragment;
    TwoFragment twoFragment;
    ThreeFragment threeFragment;
    FourFragment fourFragment;
    FiveFragment fiveFragment;
    FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initFrags();


        fragmentManager = getSupportFragmentManager();


        initOneFrag();

        initDrawerItems();
        navList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                switch (position) {
                    case 0:
                        initOneFrag();
                        break;
                    case 1:
                        initTwoFrag();
                        break;
                    case 2:
                        initThreeFrag();
                        break;
                    case 3:
                        initFourFrag();
                        break;
                    case 4:
                        initFiveFrag();
                        break;
                }
                drawerLayout.closeDrawers();
            }
        });
    }

    public void initOneFrag() {
        if (!oneFragment.isVisible())
            fragmentManager.beginTransaction()
                    .setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                            android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                    .replace(R.id.container_frame, oneFragment).addToBackStack("oneFragment").commit();
    }

    public void initTwoFrag() {
        fragmentManager.popBackStackImmediate();
        twoFragment = TwoFragment.newInstance();
        fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .add(R.id.container_frame, twoFragment).addToBackStack("twoFragment").commit();
    }

    public void initThreeFrag() {
        fragmentManager.popBackStackImmediate();
        threeFragment = ThreeFragment.newInstance();
        fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_out, android.R.anim.fade_in,
                android.R.anim.fade_out, android.R.anim.fade_in)
                .add(R.id.container_frame, threeFragment).addToBackStack("threeFragment").commit();
    }

    public void initFourFrag() {
        fragmentManager.popBackStackImmediate();
        fourFragment = FourFragment.newInstance();
        fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .add(R.id.container_frame, fourFragment).addToBackStack("fourFragment").commit();
    }

    public void initFiveFrag() {
        fragmentManager.popBackStackImmediate();
        fiveFragment = FiveFragment.newInstance();
        fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.slide_in_left, android.R.anim.slide_out_right,
                android.R.anim.slide_in_left, android.R.anim.slide_out_right)
                .add(R.id.container_frame, fiveFragment).addToBackStack("fiveFragment").commit();
    }


    private void initFrags() {
        oneFragment = new OneFragment();
        twoFragment = new TwoFragment();
        threeFragment = new ThreeFragment();
        fourFragment = new FourFragment();
        fiveFragment = new FiveFragment();
    }

    private void initDrawerItems() {
        menuModelList = new ArrayList<>();
        menuModelList.clear();
        menuModelList.add("One");
        menuModelList.add("Two");
        menuModelList.add("Three");
        menuModelList.add("Four");
        menuModelList.add("Five");
        ArrayAdapter adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, menuModelList);
        navList.setAdapter(adapter);
    }

    private void initView() {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navList = (ListView) findViewById(R.id.navList);
        action_bar_main = (RelativeLayout) findViewById(R.id.action_bar_main);
        humberger = (ImageView) action_bar_main.findViewById(R.id.humburger);
        humberger.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
                    drawerLayout.closeDrawers();
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawers();
        } else if (oneFragment.isVisible()) {
            finish();
        } else if (fragmentManager.getBackStackEntryCount() >= 1) {
            initOneFrag();
        } else {
            super.onBackPressed();
        }
    }
}
