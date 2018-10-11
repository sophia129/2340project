package com.example.breadscrumbs.donation_tracker;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import Model.LocationSQLiteDBHandler;
import Model.SQLiteDatabaseHandler;

public class MainActivity extends AppCompatActivity {

    public static SQLiteDatabaseHandler db;
    public static LocationSQLiteDBHandler dbLocations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use these lines to hide the action bar for each page
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        db = new SQLiteDatabaseHandler(this);

        dbLocations = new LocationSQLiteDBHandler(this);

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

    /**
     * Handles the press of the New Account button, sending the user to NewAccount
     */
    public void ClickNewAccount(View view) {
        Intent newIntent = new Intent(this, NewAccount.class);
        startActivity(newIntent);
    }

    public static SQLiteDatabaseHandler getDb() {
        return db;
    }

    public static LocationSQLiteDBHandler getLocationsDb() {
        return dbLocations;
    }

}
