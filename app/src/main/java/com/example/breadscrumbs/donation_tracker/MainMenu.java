package com.example.breadscrumbs.donation_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.breadscrumbs.donation_tracker.LocationStuff.location;
import com.example.breadscrumbs.donation_tracker.SearchStuff.Search_Options;

/**
 * Controller that handles actions taken on the main menu of the application
 */
public class MainMenu extends AppCompatActivity {
    private String userEmail;

    /**
     * Gets the extras from the LogIn activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main_menu);

        Intent intent = getIntent();
        userEmail = intent.getStringExtra("email");
    }

    /**
     * Handles back press click; takes user back to previous activity
     *
     * @param view Automatic parameter for user interaction
     */
    public void ClickedBackButton(View view) {
        onBackPressed();
    }

    /**
     * Handles location button
     *
     * @param view Automatic parameter for user interaction
     */
    public void ClickedLocationButton(View view) {
        Intent newIntent = new Intent(this, location.class);
        newIntent.putExtra("email", userEmail);
        startActivity(newIntent);
    }

    /**
     * Handles search button
     *
     * @param view Automatic parameter for user interaction
     */
    public void ClickedSearchButton(View view) {
        Intent newIntent = new Intent(this, Search_Options.class);
        newIntent.putExtra("email", userEmail);
        newIntent.putExtra("Location Key", "");
        startActivity(newIntent);
    }

    /**
     * Launches the Maps activity
     *
     * @param view Automatic parameter for user interaction
     */
    public void ClickedMapsButton(View view) {
        Intent newIntent = new Intent(this, MapsActivity.class);
        startActivity(newIntent);
    }
}
