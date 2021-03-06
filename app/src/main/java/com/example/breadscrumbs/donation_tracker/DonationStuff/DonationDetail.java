package com.example.breadscrumbs.donation_tracker.DonationStuff;

import java.util.List;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.example.breadscrumbs.donation_tracker.MainActivity;
import com.example.breadscrumbs.donation_tracker.R;

import Model.Donation;
import Model.DonationDatabaseHandler;

public class DonationDetail extends AppCompatActivity {

    DonationDatabaseHandler db = MainActivity.getDonationsDb();
    String locationKey;
    String item;

    /**
     * Gets the extras from the DonationList activity and calls the load up method for the text view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_donation_detail);

        //Get current location and set the instance variable
        Intent intent = getIntent();
        locationKey = intent.getStringExtra("Location Key");
        item = intent.getStringExtra("Item");
        Donation donation = db.getItem(locationKey, item);

        loadTV(donation);
    }

    /**
     * Fills the text view with all the attributes of the location;
     * basically formatted to have a space separating different attributes.
     * Acquires the key for the selected location from the intent as passed from
     * the previous Activity
     */
    public void loadTV(Donation currentItem)
    {
        String toShow = returnContents(currentItem);
        TextView detailHolder = (TextView) findViewById(R.id.DetailHolder);
        detailHolder.setMovementMethod(new ScrollingMovementMethod());
        detailHolder.setText(toShow);
    }

    /**
     * This compiles all the text to be displayed on the screen into a string that is returned;
     */
    public static String returnContents(Donation item)
    {
        String forTV = "";

        forTV += "Item:\n" + item.getItem() + "\n\n";
        forTV += "Description:\n" + item.getDescription() + "\n\n";
        forTV += "Timestamp:\n" + item.getTimestamp() + "\n\n";
        forTV += "Value:\n" + item.getValue() + "\n\n";
        forTV += "Location:\n" + item.getLocation().getName() + "\n\n";
        forTV += "Category:\n" + item.getCategory() + "\n\n";

        return forTV;
    }

    /**
     * Handles back press click; takes user back to MainActivity
     */
    public void ClickedBackButton(View view) {
        onBackPressed();
    }

}
