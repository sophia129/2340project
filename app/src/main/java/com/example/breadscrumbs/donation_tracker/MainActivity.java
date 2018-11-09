package com.example.breadscrumbs.donation_tracker;

import android.content.Intent;
import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.breadscrumbs.donation_tracker.LocationStuff.location;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import Model.Location;
import Model.LocationSQLiteDBHandler;
import Model.SQLiteDatabaseHandler;
import Model.DonationDatabaseHandler;

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
        readLocationData(rawResource);

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

    /**
     * Reads in the data from the CSV file by parsing it by its commas;
     * takes these separated components to create a location that is added to the
     * local database of locations
     *
     * @param locationInput the file data to be interpreted and split for the locations
     */
    private static void readLocationData(InputStream locationInput) {

        String line = null;
        try {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(locationInput, StandardCharsets.UTF_8)
            );

            line = reader.readLine();
            line = reader.readLine();
            while ((line) != null) {
                Log.d(location.TAG, line);
                //split by comma
                Log.d("Line", line);
                String[] tokens = line.split(",");

                Location newLocation = new Location(tokens[Location.SaveEquivalencies.key.value],
                        tokens[Location.SaveEquivalencies.name.value],
                        tokens[Location.SaveEquivalencies.latitude.value],
                        tokens[Location.SaveEquivalencies.longitude.value],
                        tokens[Location.SaveEquivalencies.streetAddress.value],
                        tokens[Location.SaveEquivalencies.city.value],
                        tokens[Location.SaveEquivalencies.state.value],
                        tokens[Location.SaveEquivalencies.zip.value],
                        tokens[Location.SaveEquivalencies.type.value],
                        tokens[Location.SaveEquivalencies.phone.value],
                        tokens[Location.SaveEquivalencies.website.value]);

                List<Location> allLocations = dbLocations.allLocations();
                boolean containsNewLocation = allLocations.contains(newLocation);

                if (!containsNewLocation) {
                    dbLocations.addLocation(newLocation);
                }

                line = reader.readLine();
            }
        } catch (IOException e) {
            Log.wtf("Activity: location", "Error reading data file on line" + line, e);
            e.printStackTrace();
        }
    }
}
