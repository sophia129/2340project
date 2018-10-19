package com.example.breadscrumbs.donation_tracker;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;

import Model.Location;
import Model.LocationDetailModel;
import Model.LocationSQLiteDBHandler;
import Model.SQLiteDatabaseHandler;
import Model.User;

public class LocationDetail extends AppCompatActivity {

    LocationSQLiteDBHandler db = MainActivity.getLocationsDb();
    SQLiteDatabaseHandler usersDb = MainActivity.getDb();

    Location currentLocation = null;
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        // Use these lines to hide the action bar for each page
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_location_detail);

        //Get current location and set the instance variable
        Intent intent = getIntent();
        String key = intent.getStringExtra("Location Key");
        userEmail = intent.getStringExtra("email");
        System.out.println("Location detail eamil: " + userEmail);
        currentLocation = db.getLocation(key);

        Button view = findViewById(R.id.viewDonations);
        Button add = findViewById(R.id.addDonation);

        System.out.println("user type" + usersDb.getUser(userEmail).getUserType());

        if (usersDb.getUser(userEmail).getUserType() == User.UserType.USER) {
            view.setVisibility(View.GONE);
            add.setVisibility(View.GONE);
        }

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
        String toShow = LocationDetailModel.returnContents(currentLocation);
        TextView detailHolder = (TextView) findViewById(R.id.DetailHolder);
        detailHolder.setMovementMethod(new ScrollingMovementMethod());
        detailHolder.setText(toShow);
    }
    /* M7 handlers */
    public void AddDonation(View view) {

        Intent newIntent = new Intent(this, NewDonation.class);
        newIntent.putExtra("Location", currentLocation.getName());
        newIntent.putExtra("LocationKey", currentLocation.getKey());
        startActivity(newIntent);

    }
    public void ViewDonations(View view) {
        Intent newIntent = new Intent(this, DonationList.class);
        newIntent.putExtra("LocationKey", currentLocation.getKey());
        startActivity(newIntent);

    }

    /**
     * Handles back press click; takes user back to MainActivity
     */
    public void ClickedBackButton(View view) {
        onBackPressed();
    }

}
