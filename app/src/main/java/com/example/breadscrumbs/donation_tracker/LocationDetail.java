package com.example.breadscrumbs.donation_tracker;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
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
    TextView detailHolder;

    Location currentLocation = null;
    String userEmail;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_location_detail);

        //Get current location and set the instance variable
        Intent intent = getIntent();
        key = intent.getStringExtra("Location Key");
        userEmail = intent.getStringExtra("email");
        System.out.println("Location detail email: " + userEmail);
        Log.d("Key", key);
        currentLocation = db.getLocation(key);

        Button view = findViewById(R.id.viewDonations);
        Button add = findViewById(R.id.addDonation);

        System.out.println("user type" + usersDb.getUser(userEmail).getUserType());

        detailHolder = (TextView) findViewById(R.id.DetailHolder);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels; // gets height of display

        RelativeLayout topLayout = (RelativeLayout) findViewById(R.id.topLayout);

        int forTV = height - (topLayout.getLayoutParams().height * 2);

        if (usersDb.getUser(userEmail).getUserType() == User.UserType.USER) {
            view.setVisibility(View.GONE);
            add.setVisibility(View.GONE);
        } else {
            // 1.2 multipliers to approximately account for spacing between them
            forTV -= (view.getLayoutParams().height + add.getLayoutParams().height);
        }


        detailHolder.getLayoutParams().height = forTV;


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
        newIntent.putExtra("Location Key", key);
        startActivity(newIntent);

    }
    public void ViewDonations(View view) {
        Intent newIntent = new Intent(this, DonationList.class);
        newIntent.putExtra("LocationKey", currentLocation.getKey());
        startActivity(newIntent);

    }

    public void ClickedSearchButton(View view) {
        Intent newIntent = new Intent(this, Search_Options.class);
        newIntent.putExtra("email", userEmail);
        newIntent.putExtra("Location Key", key);
        startActivity(newIntent);
    }

    /**
     * Handles back press click; takes user back to MainActivity
     */
    public void ClickedBackButton(View view) {
        onBackPressed();
    }

}
