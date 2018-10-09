package com.example.breadscrumbs.donation_tracker;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use these lines to hide the action bar for each page
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();


        setContentView(R.layout.activity_main);
    }

    /**
     * Handles the press of the Log In button, sending the user to LogIn.
     */
    public void ClickLogIn(View view)
    {
        Intent newIntent = new Intent(this, LogIn.class);
        startActivity(newIntent);
    }

}