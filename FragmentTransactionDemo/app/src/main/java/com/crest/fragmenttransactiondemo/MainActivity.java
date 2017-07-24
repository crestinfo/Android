package com.crest.fragmenttransactiondemo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.crest.fragmenttransactiondemo.Fragments.GalleryFragment;
import com.crest.fragmenttransactiondemo.Fragments.ImportFragment;
import com.crest.fragmenttransactiondemo.Fragments.SlideshowFragment;
import com.crest.fragmenttransactiondemo.Fragments.TabsFragment;
import com.crest.fragmenttransactiondemo.Fragments.ToolsFragment;

import static com.crest.fragmenttransactiondemo.Constants.GALLEY_FRAGMENT;
import static com.crest.fragmenttransactiondemo.Constants.IMPORT_FRAGMENT;
import static com.crest.fragmenttransactiondemo.Constants.SELECTED;
import static com.crest.fragmenttransactiondemo.Constants.SLIDESHOW_FRAGMENT;
import static com.crest.fragmenttransactiondemo.Constants.TOOLS_FRAGMENT;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        ViewPagerAdapter pagerAdapter =new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
        if (savedInstanceState != null)
        {
            SELECTED = IMPORT_FRAGMENT;
            loadFragment();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
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

    String[] arrTabs = {"query", "page", "country"};
    class ViewPagerAdapter extends FragmentPagerAdapter {

        int NUM_ITEMS = 3;

        ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            return TabsFragment.newInstance(arrTabs[position]);
        }

        @Override
        public int getItemPosition(Object object) {
            return POSITION_NONE;
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Queries";
                case 1:
                    return "Pages";
                case 2:
                    return "Countries";
            }
            return "";
        }

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            SELECTED = IMPORT_FRAGMENT;
            loadFragment();
        } else if (id == R.id.nav_gallery) {
            SELECTED = GALLEY_FRAGMENT;
            loadFragment();
        } else if (id == R.id.nav_slideshow) {
            SELECTED = SLIDESHOW_FRAGMENT;
            loadFragment();
        } else if (id == R.id.nav_manage) {
            SELECTED = TOOLS_FRAGMENT;
            loadFragment();
        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void loadFragment() {
        switch (SELECTED) {
            case IMPORT_FRAGMENT:
                toolbar.setTitle("Import");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_home, new ImportFragment())
                        .commit();
                break;
            case SLIDESHOW_FRAGMENT:
                toolbar.setTitle("SlideShow");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_home, new SlideshowFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case TOOLS_FRAGMENT:
                toolbar.setTitle("Tools");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_home, new ToolsFragment())
                        .addToBackStack(null)
                        .commit();
                break;
            case GALLEY_FRAGMENT:
                toolbar.setTitle("Gallery");
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.content_home, new GalleryFragment())
                        .addToBackStack(null)
                        .commit();
                break;
        }
    }
}
