package com.example.breadscrumbs.donation_tracker;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {
    String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use these lines to hide the action bar for each page
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_main_menu);

        Intent intent = getIntent();
        userEmail = intent.getStringExtra("email");
        System.out.println("Email in main menu: " + userEmail);
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
        startActivity(newIntent);
    }


}
