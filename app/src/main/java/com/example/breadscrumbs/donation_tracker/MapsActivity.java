package com.example.breadscrumbs.donation_tracker;

import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import Model.Location;

/**
 * Controller that displays a Google Map with locations and their details
 */
public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    /**
     * Automatically generated code by Android to support integration of Google Maps.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        FragmentManager currentFM = getSupportFragmentManager();
        SupportMapFragment mapFragment = (SupportMapFragment) currentFM.findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     *
     * For our purposes, setting a marker on the map for each location. The title is set to the name
     * of the donation center, and the snippet underneath is the center's phone number.
     * Zoom controls are also integrated here.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Model.Location[] locationList = Model.locationModel.returnLocations();

        UiSettings currentSettings = mMap.getUiSettings();
        currentSettings.setZoomControlsEnabled(true);

        for (Location location : locationList) {
            LatLng temp = new LatLng(Double.parseDouble(location.getLatitude()),
                    Double.parseDouble(location.getLongitude()));
            MarkerOptions marker = new MarkerOptions();
            marker.position(temp);
            marker.title(location.getName());
            marker.snippet(location.getPhone());

            mMap.addMarker(marker);
            mMap.moveCamera(CameraUpdateFactory.newLatLng(temp));
        }

        mMap.moveCamera(CameraUpdateFactory.zoomTo(8));

    }

    /**
     * Handles back press click; takes user back to previous activity
     *
     * @param view Automatic parameter for user interaction
     */
    public void ClickedBackButton(View view) {
        onBackPressed();
    }

}
