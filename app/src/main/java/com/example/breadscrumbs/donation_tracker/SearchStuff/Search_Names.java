package com.example.breadscrumbs.donation_tracker.SearchStuff;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;


import com.example.breadscrumbs.donation_tracker.DonationStuff.DonationDetail;
import com.example.breadscrumbs.donation_tracker.MainActivity;
import com.example.breadscrumbs.donation_tracker.R;

import java.util.List;

import Model.Donation;
import Model.DonationDatabaseHandler;
//import Model.Location;
import Model.LocationSQLiteDBHandler;

/**
 * Controller for searching through entries by their names/short descriptions
 */
public class Search_Names extends AppCompatActivity {

    private final DonationDatabaseHandler db = MainActivity.getDonationsDb();
    private final LocationSQLiteDBHandler locationsDB = MainActivity.getLocationsDb();
    private ListView inventory;
    private String locationKey = "";
    private List<Donation> donationArray;

    /**
     * Gets the extras from the Search_Options activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__names);

        inventory = findViewById(R.id.inventory);

        Intent intent = getIntent();
        locationKey = intent.getStringExtra("Location Key");
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
     * Pulls teh contents of the search bar and calls a method to evaluate what inventory
     * meets the criteria
     *
     * @param view Automatic parameter for user interaction
     */
    public void ClickedCheckInventory(View view) {
        EditText searchBar = findViewById(R.id.search_bar);
        final Editable searchBarContents = searchBar.getText();
        final String searchBarString = searchBarContents.toString();
        final String lowerCaseSearchBarString = searchBarString.toLowerCase();
        loadInventory(lowerCaseSearchBarString);
    }

    /**
     * Gets the list of items that contain the searched name and sets
     * the adapter of the listView (inventory) to these contents
     */
    private void loadInventory(String name) {
        final String[] items = itemsAsList(name);

        ListAdapter arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                items);

        inventory.setAdapter(arrayAdapter);

        if (items.length == 0) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("No Results Detected");
            dialog.setMessage("Please try another search.");
            dialog.setPositiveButton("OK", null);
            dialog.show();
        }

        final Context outerContext = this;

        inventory.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                final Donation current = donationArray.get(position);
                //final Location currentLocation = current.getLocation();
                final String currentLocationKey = current.getLocationKey();
                Intent newIntent = new Intent(outerContext, DonationDetail.class);
                newIntent.putExtra("Location Key",
                        currentLocationKey);
                newIntent.putExtra("Item", (String) parent.getItemAtPosition(position));
                startActivity(newIntent);
            }
        });
    }

    /**
     * Acquires the list of items that contain the searched name
     * from the database and returns the names of them
     */
    private String[] itemsAsList(String name) {
        Model.Location location = null;
        if (!"".equals(locationKey)) {
            location = locationsDB.getLocation(locationKey);
        }
        donationArray = db.getPotentialItem(name, location);
        final int donationArraySize = donationArray.size();
        String[] toReturn = new String[donationArraySize];
        int index = 0;
        for (Donation donation : db.getPotentialItem(name, location)) {
            toReturn[index] = donation.getItem();
            ++index;
        }

        return toReturn;
    }
}


