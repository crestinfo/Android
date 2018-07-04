package com.example.server.googlemapdemo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    LatLngBounds.Builder builder = new LatLngBounds.Builder();
    ImageView imgClose;
    LatLng center;

    //LatLngBounds bounds = builder.build();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        imgClose = (ImageView) findViewById(R.id.imageViewClose);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        imgClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //center = bounds.getCenter();

    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng loc1 = new LatLng(51.4410378, 6.8759338);
        LatLng loc2 = new LatLng(51.4709935,7.1555176);
        LatLng loc3 = new LatLng(51.242023,7.0236823);

        Polygon polygon = mMap.addPolygon(new PolygonOptions()
                .add(loc1, loc2, loc3)
                .strokeColor(Color.RED)
                .strokeWidth(0.2f)
                .fillColor(getResources().getColor(R.color.colorRedTransperent)));

        //Log.e("Center", "onMapReady: " + center);
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc2, 15));

        /*CameraPosition cameraPosition = new CameraPosition.Builder()
                .target(crest)      // Sets the center of the map to location user
                .zoom(20)                   // Sets the zoom
                .bearing(90)                // Sets the orientation of the camera to east
                .tilt(40)                   // Sets the tilt of the camera to 30 degrees
                .build();                   // Creates a CameraPosition from the builder
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));*/

        polygon.setClickable(true);
        mMap.setOnPolygonClickListener(new GoogleMap.OnPolygonClickListener() {
            @Override
            public void onPolygonClick(Polygon polygon) {
                Toast.makeText(MapsActivity.this, "You Are Inside !", Toast.LENGTH_SHORT).show();
            }
        });
        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {
                Toast.makeText(MapsActivity.this, "You Are Outside !", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
