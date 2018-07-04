package com.crest.database_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends Activity {
    /**
     * Called when the activity is first created.
     */
    EditText edtName, edtPhone;
    Button btnSave, btnView;
    Spinner spinTech;
    RadioButton rdMale, rdFemale;
    String[] tech = {"PHP", "Android", "BDE"};

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtName = findViewById(R.id.edtName);
        spinTech = (Spinner) findViewById(R.id.spinTech);
        rdMale = (RadioButton) findViewById(R.id.rdMale);
        rdFemale = (RadioButton) findViewById(R.id.rdFemale);
        btnView = findViewById(R.id.btnViewList);
        btnSave = findViewById(R.id.btnSubmit);
        final DatabaseHandler db = new DatabaseHandler(this);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tech);
        spinTech.setAdapter(adapter);
        /**
         * CRUD Operations
         * */
        // Inserting Contacts
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (rdMale.isChecked()) {
                    db.addContact(new Contact(edtName.getText().toString(), spinTech.getSelectedItem().toString(), "Male"));
                    Toast.makeText(MainActivity.this, "Your Data Submited !", Toast.LENGTH_SHORT).show();
                } else {
                    db.addContact(new Contact(edtName.getText().toString(), spinTech.getSelectedItem().toString(), "Female"));
                    Toast.makeText(MainActivity.this, "Your Data Submited !", Toast.LENGTH_SHORT).show();
                }
            }
        });
        // Reading all contacts
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ViewDetails.class));
            }
        });
    }
}