package com.example.breadscrumbs.donation_tracker;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import Model.Location;
import Model.LocationDetailModel;
import Model.LocationSQLiteDBHandler;

public class LocationDetail extends AppCompatActivity {

    LocationSQLiteDBHandler db = MainActivity.getLocationsDb();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        // Use these lines to hide the action bar for each page
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        loadTV();
    }

    /**
     * Fills the text view with all the attributes of the location;
     * basically formatted to have a space separating different attributes.
     * Acquires the key for the selected location from the intent as passed from
     * the previous Activity
     */
    public void loadTV()
    {
        Intent intent = getIntent();
        String key = intent.getStringExtra("Location Key");
        Location currentLocation = db.getLocation(key);
        String toShow = LocationDetailModel.returnContents(currentLocation);
        TextView detailHolder = (TextView) findViewById(R.id.DetailHolder);
        detailHolder.setMovementMethod(new ScrollingMovementMethod());
        detailHolder.setText(toShow);
    }
    /* M7 handlers */
    public void AddDonation(View view) {
        Intent newIntent = new Intent(this, NewDonation.class);
        startActivity(newIntent);

    }
    public void ViewDonations(View view) {

    }

    /**
     * Handles back press click; takes user back to MainActivity
     */
    public void ClickedBackButton(View view) {
        onBackPressed();
    }

}
