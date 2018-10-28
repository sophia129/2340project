package com.example.breadscrumbs.donation_tracker;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.MovementMethod;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import Model.Donation;
import Model.DonationDatabaseHandler;

public class DonationList extends AppCompatActivity {
    DonationDatabaseHandler db = MainActivity.getDonationsDb();
    String locationKey;
    ListView DonationsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_donations);

        Intent intent = getIntent();
        locationKey = intent.getStringExtra("LocationKey");

        //Get current location and set the instance variable
        DonationsList = (ListView) findViewById(R.id.DonationsList);

        loadLV();

    }

    /**
     * Fills the list view with the donation items for the designated location
     */
    private void loadLV()
    {
        final String[] names = itemsAsList();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                names);

        DonationsList.setAdapter(arrayAdapter);

        final Context outerContext = this;

        DonationsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String key = Integer.toString(position + 1);
                Intent newIntent = new Intent(outerContext, DonationDetail.class);
                newIntent.putExtra("Location Key", locationKey);
                newIntent.putExtra("Item", (String) parent.getItemAtPosition(position));
                startActivity(newIntent);
            }
        });

    }

    /**
     * This gets all the donations stored in the database and returns them as a string array
     */
    private String[] itemsAsList() {
        String[] toReturn = new String[db.allItems(locationKey).size()];
        int index = 0;
        for (Donation donation : db.allItems(locationKey)) {
            toReturn[index] = donation.getItem();
            ++index;
        }
        return toReturn;
    }

    /**
     * Handles back press click; takes user back to MainActivity
     */
    public void ClickedBackButton(View view) {
        onBackPressed();
    }

}
