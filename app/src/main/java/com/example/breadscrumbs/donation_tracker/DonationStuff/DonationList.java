package com.example.breadscrumbs.donation_tracker.DonationStuff;

import android.content.Context;
import android.content.Intent;
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

/**
 * Controller that displays the donations for a particular location
 */
public class DonationList extends AppCompatActivity {
    private final DonationDatabaseHandler db = MainActivity.getDonationsDb();
    private String locationKey;
    private ListView DonationsList;

    /**
     * Gets the extras from the LocationDetail activity and calls the
     * load up method for the list view
     *
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_donations);

        Intent intent = getIntent();
        locationKey = intent.getStringExtra("LocationKey");

        //Get current location and set the instance variable
        DonationsList = findViewById(R.id.DonationsList);

        loadLV();
    }

    /**
     * Fills the list view with the donation items for the designated location
     */
    private void loadLV()
    {
        final String[] names = itemsAsList();

        ListAdapter arrayAdapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                names);

        DonationsList.setAdapter(arrayAdapter);

        final Context outerContext = this;

        DonationsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

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
        final List<Donation> allItems = db.allItems(locationKey);
        final int allItemsSize = allItems.size();
        String[] toReturn = new String[allItemsSize];
        int index = 0;
        for (Donation donation : db.allItems(locationKey)) {
            toReturn[index] = donation.getItem();
            ++index;
        }
        return toReturn;
    }

    /**
     * Handles back press click; takes user back to previous activity
     *
     * @param view the automatic parameter for the current view
     */
    public void ClickedBackButton(View view) {
        onBackPressed();
    }

}
