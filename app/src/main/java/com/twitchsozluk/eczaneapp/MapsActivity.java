package com.twitchsozluk.eczaneapp;

import androidx.fragment.app.FragmentActivity;
import android.content.Intent;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private String eczane;
    private double coord1,coord2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        this.eczane = intent.getStringExtra("eczane");
        this.coord1 = intent.getDoubleExtra("coord1",0);
        this.coord2 = intent.getDoubleExtra("coord2",0);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng eczane = new LatLng(this.coord1,this.coord2);
        mMap.addMarker(new MarkerOptions().position(eczane).title(this.eczane));
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(eczane,18));
    }
}
