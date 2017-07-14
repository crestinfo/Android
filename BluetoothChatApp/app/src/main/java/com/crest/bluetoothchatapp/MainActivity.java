package com.crest.bluetoothchatapp;

import android.Manifest;
import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity {

    private static final int REQUEST_ENABLE_BT = 1;

    ListView listDevicesFound;
    Button btnScanDevice;

    BluetoothAdapter bluetoothAdapter;

    ArrayAdapter<String> btArrayAdapter;
    ProgressBar progressBar;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnScanDevice = (Button) findViewById(R.id.btnScan);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

        if (ContextCompat.checkSelfPermission(MainActivity.this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(MainActivity.this,
                        Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    100);
        }


        listDevicesFound = (ListView) findViewById(R.id.listDevices);
        btArrayAdapter = new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1);
        listDevicesFound.setAdapter(btArrayAdapter);

        CheckBlueToothState();

        btnScanDevice.setOnClickListener(btnScanDeviceOnClickListener);

        registerReceiver(ActionFoundReceiver,
                new IntentFilter(BluetoothDevice.ACTION_FOUND));

    }

    @Override
    protected void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        unregisterReceiver(ActionFoundReceiver);
    }

    private void CheckBlueToothState() {
        if (bluetoothAdapter == null) {
            Toast.makeText(this, "Bluetooth NOT support", Toast.LENGTH_SHORT).show();
        } else {
            if (bluetoothAdapter.isEnabled()) {
                if (bluetoothAdapter.isDiscovering()) {
                    Log.e("Discovering", "CheckBlueToothState: Bluetooth is currently in device discovery process.");
                } else {
                    Log.e("Enabled", "CheckBlueToothState: Bluetooth is Enabled.");
                    btnScanDevice.setEnabled(true);
                }
            } else {
                Log.e("Enabled 2", "CheckBlueToothState: Bluetooth is NOT Enabled!");
                Intent enableBtIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBtIntent, REQUEST_ENABLE_BT);
            }
        }
    }

    private Button.OnClickListener btnScanDeviceOnClickListener
            = new Button.OnClickListener() {

        @Override
        public void onClick(View arg0) {
            // TODO Auto-generated method stub
            btArrayAdapter.clear();
            bluetoothAdapter.startDiscovery();
            progressBar.setVisibility(View.VISIBLE);
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (requestCode == REQUEST_ENABLE_BT) {
            CheckBlueToothState();
        }
    }

    private final BroadcastReceiver ActionFoundReceiver = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            String action = intent.getAction();
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                btArrayAdapter.add(device.getName() + "\n" + device.getAddress());
                Log.e("Detected Device", "onReceive: " + device.getName());
                btArrayAdapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
            }
        }
    };
}
