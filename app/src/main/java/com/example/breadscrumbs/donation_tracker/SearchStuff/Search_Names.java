package com.example.breadscrumbs.donation_tracker.SearchStuff;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


import com.example.breadscrumbs.donation_tracker.DonationStuff.DonationDetail;
import com.example.breadscrumbs.donation_tracker.MainActivity;
import com.example.breadscrumbs.donation_tracker.R;

import java.util.List;

import Model.Donation;
import Model.DonationDatabaseHandler;
import Model.LocationSQLiteDBHandler;

public class Search_Names extends AppCompatActivity {

    DonationDatabaseHandler db = MainActivity.getDonationsDb();
    LocationSQLiteDBHandler locationsDB = MainActivity.getLocationsDb();
    ListView inventory;
    String locationKey = "";
    List<Donation> donationArray;

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
     * Returns user to the Search_Options view
     */
    public void ClickedBackButton(View view) {
        onBackPressed();
    }

    /**
     * Pulls the contents of the search bar and calls a method to evaluate what inventory meets the criteria
     */
    public void ClickedCheckInventory(View view) {
        EditText searchBar = findViewById(R.id.search_bar);
        loadInventory(searchBar.getText().toString().toLowerCase());
    }

    /**
     * Gets the list of items that contain the searched name and sets the adapter of the listView (inventory) to these contents
     */
    private void loadInventory(String name) {
        final String[] items = itemsAsList(name);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
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

                Intent newIntent = new Intent(outerContext, DonationDetail.class);
                newIntent.putExtra("Location Key", donationArray.get(position).getLocation().getKey());
                newIntent.putExtra("Item", (String) parent.getItemAtPosition(position));
                startActivity(newIntent);
            }
        });
    }

    /**
     * Acquires the list of items that contain the searched name from the database and returns the names of them
     */
    private String[] itemsAsList(String name) {
        Model.Location location = null;
        if (!locationKey.equals("")) {
            location = locationsDB.getLocation(locationKey);
        }
        donationArray = db.getPotentialItem(name, location);
        String[] toReturn = new String[db.getPotentialItem(name, location).size()];
        int index = 0;
        for (Donation donation : db.getPotentialItem(name, location)) {
            toReturn[index] = donation.getItem();
            ++index;
        }

        return toReturn;
    }
}


