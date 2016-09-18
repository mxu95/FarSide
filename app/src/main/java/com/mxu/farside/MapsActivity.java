package com.mxu.farside;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
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
        Intent intent = getIntent();
        double[] latLong = intent.getDoubleArrayExtra("ll");
        double latitude = -latLong[0];
        double longitude = latLong[1];

        if(longitude < 0) {
            longitude += 180;
        } else {
            longitude -= 180;
        }
        Log.i(MainActivity.TAG, "Antipode latitude: " + latitude + " longitude: " + longitude);
        LatLng current = new LatLng(latLong[0], latLong[1]);
        LatLng antipode = new LatLng(latitude, longitude);
        mMap.addMarker(new MarkerOptions().position(antipode).title("Opposite Side of the World"));
        mMap.addMarker(new MarkerOptions().position(current).title("Your Position"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(antipode));
    }
}
