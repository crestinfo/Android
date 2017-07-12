package com.crest.database_demo;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;

public class ViewDetails extends AppCompatActivity {
    RecyclerView recyclerView;
    List<Contact> empDetailsList;
    DatabaseHandler databaseHandler;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);
        textView = (TextView) findViewById(R.id.txtNoData);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerEmpData);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        databaseHandler = new DatabaseHandler(this);

        empDetailsList = databaseHandler.getAllContacts();
        if (empDetailsList.size() == 0)
            textView.setVisibility(View.VISIBLE);
        else
            textView.setVisibility(View.GONE);


        CustomAdapter customAdapter = new CustomAdapter();
        recyclerView.setAdapter(customAdapter);

    }

    class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

        @Override
        public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(ViewDetails.this).inflate(R.layout.list_data, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.MyViewHolder holder, final int position) {
            holder.txtName.setText(empDetailsList.get(position).getName());
            holder.txtTech.setText(empDetailsList.get(position).getTech());
            holder.btnDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ViewDetails.this);
                    builder.setCancelable(false);
                    builder.setMessage("Are you sure want to delete ?");
                    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            databaseHandler.deleteContact(empDetailsList.get(position).getId());
                            empDetailsList = databaseHandler.getAllContacts();
                            if (empDetailsList.size() == 0)
                                textView.setVisibility(View.VISIBLE);
                            CustomAdapter customAdapter = new CustomAdapter();
                            recyclerView.setAdapter(customAdapter);

                        }
                    });
                    builder.show();
                }
            });
            holder.btnEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final Dialog dialog = new Dialog(ViewDetails.this);
                    dialog.setContentView(R.layout.dialog_edit);
                    final EditText edtName;
                    final Spinner spin;
                    Button btnUpdate, btnCancel;
                    final RadioButton rdEdtMale, rdEdtFemale;
                    dialog.setCancelable(false);
                    String[] tech = {"PHP", "Android", "BDE"};
                    edtName = (EditText) dialog.findViewById(R.id.edtEditName);
                    rdEdtMale = (RadioButton) dialog.findViewById(R.id.rdEditMale);
                    rdEdtFemale = (RadioButton) dialog.findViewById(R.id.rdEditFemale);
                    spin = (Spinner) dialog.findViewById(R.id.spinEditTech);
                    btnUpdate = (Button) dialog.findViewById(R.id.btnUpdate);
                    btnCancel = (Button) dialog.findViewById(R.id.btnCancle);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(ViewDetails.this, android.R.layout.simple_list_item_1, tech);
                    spin.setAdapter(adapter);

                    edtName.setText(empDetailsList.get(position).getName());
                    if (Objects.equals(empDetailsList.get(position).getGender(), "Male"))
                        rdEdtMale.setChecked(true);
                    else
                        rdEdtFemale.setChecked(true);

                    if (empDetailsList.get(position).getTech().equals("PHP"))
                        spin.setSelection(0);
                    else if (empDetailsList.get(position).getTech().equals("Android"))
                        spin.setSelection(1);
                    else
                        spin.setSelection(2);

                    btnCancel.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            dialog.dismiss();
                        }
                    });
                    btnUpdate.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Contact contact = new Contact();
                            contact.setName(edtName.getText().toString());
                            contact.setTech(spin.getSelectedItem().toString());
                            if (rdEdtFemale.isChecked())
                                contact.setGender("Female");
                            else
                                contact.setGender("Male");
                            if (databaseHandler.updateContact(contact,empDetailsList.get(position).getId()) != 0) {
                                Toast.makeText(ViewDetails.this, "Update Successful", Toast.LENGTH_SHORT).show();
                                empDetailsList = databaseHandler.getAllContacts();
                                CustomAdapter customAdapter = new CustomAdapter();
                                recyclerView.setAdapter(customAdapter);
                                dialog.dismiss();
                            } else {
                                Toast.makeText(ViewDetails.this, "Update Fail", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    dialog.show();
                }
            });
        }

        @Override
        public int getItemCount() {
            return empDetailsList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView txtName, txtTech, btnDelete, btnEdit;

            public MyViewHolder(View itemView) {
                super(itemView);
                txtName = itemView.findViewById(R.id.txtName);
                txtTech = itemView.findViewById(R.id.txtTech);
                btnDelete = itemView.findViewById(R.id.btnDelete);
                btnEdit = itemView.findViewById(R.id.btnEdit);
            }
        }
    }
}
