package com.example.breadscrumbs.donation_tracker;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.breadscrumbs.donation_tracker.LocationStuff.location;
import com.example.breadscrumbs.donation_tracker.SearchStuff.Search_Options;

public class MainMenu extends AppCompatActivity {
    String userEmail;

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
     * Handles back press click; takes user back to LogIn
     */
    public void ClickedBackButton(View view) {
        onBackPressed();
    }

    /**
     * Handles location button
     */
    public void ClickedLocationButton(View view) {
        Intent newIntent = new Intent(this, location.class);
        newIntent.putExtra("email", userEmail);
        startActivity(newIntent);
    }

    /**
     * Handles search button
     * @param view
     */
    public void ClickedSearchButton(View view) {
        Intent newIntent = new Intent(this, Search_Options.class);
        newIntent.putExtra("email", userEmail);
        newIntent.putExtra("Location Key", "");
        startActivity(newIntent);
    }


}
