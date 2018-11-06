package com.example.breadscrumbs.donation_tracker;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.io.InputStream;

import Model.LocationSQLiteDBHandler;
import Model.SQLiteDatabaseHandler;
import Model.DonationDatabaseHandler;
import Model.locationModel;

/**
 * The controller for the starting view for the application
 */
public class MainActivity extends AppCompatActivity {

    private static SQLiteDatabaseHandler db;
    private static LocationSQLiteDBHandler dbLocations;
    private static DonationDatabaseHandler dbDonations;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db = new SQLiteDatabaseHandler(this);

        dbLocations = new LocationSQLiteDBHandler(this);

        dbDonations = new DonationDatabaseHandler(this);

        // Handles location loading on start up
        final Resources currentResources = getResources();
        final InputStream rawResource = currentResources.openRawResource(R.raw.locationdata);
        locationModel.readLocationData(rawResource);

        setContentView(R.layout.activity_main);
    }

    /**
     * Handles the press of the Log In button, sending the user to LogIn.
     *
     * @param view Automatic parameter for user interaction
     */
    public void ClickLogIn(View view)
    {
        Intent newIntent = new Intent(this, LogIn.class);
        startActivity(newIntent);
    }

    /**
     * Handles the press of the New Account button, sending the user to NewAccount
     *
     * @param view Automatic parameter for user interaction
     */
    public void ClickNewAccount(View view) {
        Intent newIntent = new Intent(this, NewAccount.class);
        startActivity(newIntent);
    }

    /**
     * Returns the database of users for other methods
     * @return the database of users
     */
    public static SQLiteDatabaseHandler getDb() {
        return db;
    }

    /**
     * Returns the database of locations for other methods
     * @return the database of locations
     */
    public static LocationSQLiteDBHandler getLocationsDb() {
        return dbLocations;
    }

    /**
     * Returns the database of donations for other methods
     * @return the database of donations
     */
    public static DonationDatabaseHandler getDonationsDb() {
        return dbDonations;
    }
}
