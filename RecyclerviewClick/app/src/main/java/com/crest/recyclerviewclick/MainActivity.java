package com.crest.recyclerviewclick;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    List<Integer> imgList = new ArrayList<>();
    List<String> stringList = new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager manager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(false);

        imgList.add(R.mipmap.ic_launcher);
        imgList.add(R.mipmap.ic_launcher_round);
        imgList.add(R.mipmap.ic_launcher);
        imgList.add(R.mipmap.ic_launcher_round);
        imgList.add(R.mipmap.ic_launcher);
        imgList.add(R.mipmap.ic_launcher_round);

        for (int i = 0; i < 6; i++) {
            stringList.add("position " + i);
        }

        CustomAdapter customAdapter = new CustomAdapter(this, imgList,stringList, new myInterface() {
            @Override
            public void count(int count) {
                Toast.makeText(MainActivity.this, "Selected: " + count, Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(customAdapter);
    }

    interface myInterface {
        void count(int count);
    }
}
