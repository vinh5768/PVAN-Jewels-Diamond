package com.example.pvanjewelsdiamond.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.View;

import com.example.pvanjewelsdiamond.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class StoreInformationActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    Toolbar toolBar_TT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_information);

        toolBar_TT = findViewById(R.id.actionBar_TT);
        ActionBar();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void ActionBar() {
//        setSupportActionBar(toolBar_TT);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolBar_TT.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
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

        LatLng myLocal = new LatLng(10.801905511286789, 106.714499747719);
        mMap.addMarker(new MarkerOptions()
                .position(myLocal)
                .title("475A Điện Biên Phủ,TP Hồ Chí Minh")
                .icon(BitmapDescriptorFactory.defaultMarker())
        );
        mMap.moveCamera(CameraUpdateFactory.newLatLng(myLocal));
    }
}