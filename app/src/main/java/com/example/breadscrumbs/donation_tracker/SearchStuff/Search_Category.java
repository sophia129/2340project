package com.example.breadscrumbs.donation_tracker.SearchStuff;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
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
 * Controller for searching based on a selected category
 */
public class Search_Category extends AppCompatActivity {

    private final DonationDatabaseHandler db = MainActivity.getDonationsDb();
    private final LocationSQLiteDBHandler locationsDB = MainActivity.getLocationsDb();

    private ListView category;
    private ListView donationItems;
    private Model.Location location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__category);

        category = findViewById(R.id.category);
        donationItems = findViewById(R.id.donationItems);

        Intent intent = getIntent();
        String locationKey = intent.getStringExtra("Location Key");

        if (!"".equals(locationKey)) {
            location = locationsDB.getLocation(locationKey);
        }

        loadCategories();
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
     * Loads default categories into the upper listView (category); sets listener for clicks
     */
    private void loadCategories() {
        final String[] categories = {"Clothing", "Hat", "Kitchen",
                "Electronics", "Household", "Other"};
        ListAdapter arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                categories);

        category.setAdapter(arrayAdapter);

        category.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                loadChildren(categories[position]);

            }
        });
    }

    /**
     * Loads donations into the lower listView (donationItems) for each category
     * after it has been clicked; sets listener for clicks
     */
    private void loadChildren(String category) {
        final String[] donationsItem = itemsAsList(category);
        final List<Donation> donations = db.getCategoryItems(category, location);

        ListAdapter arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                donationsItem);

        donationItems.setAdapter(arrayAdapter);

        if (donationsItem.length == 0) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("No Results Detected");
            dialog.setMessage("Please try another category.");
            dialog.setPositiveButton("OK", null);
            dialog.show();
        }

        final Context outerContext = this;

        donationItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String key;
                Donation toUse = donations.get(position);
                //Location currentLocation = toUse.getLocation();
                key = toUse.getLocationKey();

                Intent newIntent = new Intent(outerContext, DonationDetail.class);
                newIntent.putExtra("Location Key", key);
                newIntent.putExtra("Item", (String) parent.getItemAtPosition(position));
                startActivity(newIntent);
            }
        });

    }

    /**
     * Gets the list of items for a particular category as a string array (for adapter use)
     */
    private String[] itemsAsList(String category) {
        List<Donation> items = db.getCategoryItems(category, location);
        int itemsSize = items.size();
        String[] toReturn = new String[itemsSize];
        int index = 0;
        for (Donation donation : db.getCategoryItems(category, location)) {
            toReturn[index] = donation.getItem();
            ++index;
        }
        return toReturn;
    }
}
