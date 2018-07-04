package com.crest.multilanguagedemo;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    ListView listView_lang;
    ArrayAdapter adapter;
    String currentLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView_lang = findViewById(R.id.listView_lang);


        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.lang));
        listView_lang.setAdapter(adapter);


        listView_lang.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        changeLanguagePreference("sp");
                        break;
                    case 1:
                        changeLanguagePreference("en");
                        break;
                    case 2:
                        changeLanguagePreference("hi");
                        break;
                    case 3:
                        changeLanguagePreference("ch");
                        break;
                    case 4:
                        changeLanguagePreference("ar");
                        break;
                    case 5:
                        changeLanguagePreference("pt");
                        break;
                    case 6:
                        changeLanguagePreference("ru");
                        break;
                    case 7:
                        changeLanguagePreference("ja");
                        break;
                    case 8:
                        changeLanguagePreference("pa");
                        break;
                    case 9:
                        changeLanguagePreference("de");
                        break;
                    case 10:
                        changeLanguagePreference("mr");
                        break;
                    case 11:
                        changeLanguagePreference("it");
                        break;
                    case 12:
                        changeLanguagePreference("nl");
                        break;
                }
            }
        });
    }

    private void changeLanguagePreference(String langCode) {
        Locale locale = new Locale(langCode);
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        Configuration conf = res.getConfiguration();
        conf.locale = locale;
        res.updateConfiguration(conf, dm);
        Intent refresh = new Intent(this, MainActivity.class);
        refresh.putExtra(currentLang, langCode);
        startActivity(refresh);
    }
}
