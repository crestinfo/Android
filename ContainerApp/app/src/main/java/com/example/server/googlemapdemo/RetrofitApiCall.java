package com.example.server.googlemapdemo;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitApiCall extends AppCompatActivity {
    Button btnPopup, btnAddAmount;
    EditText edtAmount;
    TextView txtDismiss;
    Spinner spinner;
    RecyclerView recyclerView;
    AmountData amountData = new AmountData();
    List<String> spinnerList;
    List<List<String>> amountDataList;
    List<RecyclerAmountData> dataList;
    RecyclerAmountData recyclerAmountData;
    public static final String url = "Your_server_url";

    public static Retrofit retrofit = null;

    public static Retrofit getClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofit_api_call);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnPopup = (Button) findViewById(R.id.btnPopup);


        final ApiCallInterface anInterface = getClient().create(ApiCallInterface.class);


        Call<AmountData> dataCall = anInterface.getTypeData(amountData);
        dataCall.enqueue(new Callback<AmountData>() {
            @Override
            public void onResponse(Call<AmountData> call, Response<AmountData> response) {
                amountDataList = response.body().getData();
                spinnerList = new ArrayList<>(amountDataList.size());
                dataList = new ArrayList<>(amountDataList.size());
                for (int i = 0; i < amountDataList.size(); i++) {
                    spinnerList.add(amountDataList.get(i).get(1));
                }
            }

            @Override
            public void onFailure(Call<AmountData> call, Throwable t) {
                Log.e("Error On onFailure: ", t.toString());
            }
        });


        btnPopup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(RetrofitApiCall.this);
                dialog.setContentView(R.layout.layout_dialog);
                dialog.setTitle("Add Extra Amount");
                //dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
                btnAddAmount = (Button) dialog.findViewById(R.id.btnAddAmount);
                txtDismiss = (TextView) dialog.findViewById(R.id.txtDismissDialog);
                edtAmount = (EditText) dialog.findViewById(R.id.edtAmount);
                spinner = (Spinner) dialog.findViewById(R.id.spinner);

                recyclerView = (RecyclerView) dialog.findViewById(R.id.recyclerData);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(RetrofitApiCall.this);
                recyclerView.setLayoutManager(layoutManager);
                recyclerView.setItemAnimator(new DefaultItemAnimator());

                ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(RetrofitApiCall.this, android.R.layout.simple_spinner_item, spinnerList);
                dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(dataAdapter);

                txtDismiss.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                btnAddAmount.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        recyclerAmountData = new RecyclerAmountData();
                        if (edtAmount.getText().toString().equals("")) {
                            edtAmount.setError("Please Enter Amount");
                        } else {
                            recyclerAmountData.setFinalAmount(edtAmount.getText().toString());
                            recyclerAmountData.setFinalType(spinner.getSelectedItem().toString());
                            dataList.add(recyclerAmountData);
                            CustomAdapter customAdapter = new CustomAdapter(dataList);
                            recyclerView.setAdapter(customAdapter);
                            customAdapter.notifyDataSetChanged();
                        }

                    }
                });
                dialog.show();
            }
        });


    }

    class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {
        List<RecyclerAmountData> list;

        CustomAdapter(List<RecyclerAmountData> dataList) {
            list = dataList;
        }

        @Override
        public CustomAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(RetrofitApiCall.this).inflate(R.layout.layout_list, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(CustomAdapter.MyViewHolder holder, int position) {
            holder.txtAmount.setText(list.get(position).getFinalAmount());
            holder.txtType.setText(list.get(position).getFinalType());
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView txtAmount, txtType;

            MyViewHolder(View itemView) {
                super(itemView);
                txtAmount = (TextView) itemView.findViewById(R.id.txtAmount);
                txtType = (TextView) itemView.findViewById(R.id.txtType);
            }
        }
    }
}
