package com.example.breadscrumbs.donation_tracker.DonationStuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.TextView;

import com.example.breadscrumbs.donation_tracker.MainActivity;
import com.example.breadscrumbs.donation_tracker.R;

import Model.Donation;
import Model.DonationDatabaseHandler;
//import Model.Location;

/**
 * Controller that shows all the information about a certain donation
 */
public class DonationDetail extends AppCompatActivity {

    private final DonationDatabaseHandler db = MainActivity.getDonationsDb();

    /**
     * Gets the extras from the DonationList activity and calls the load up method for the text view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_donation_detail);

        //Get current location and set the instance variable
        Intent intent = getIntent();
        final String locationKey = intent.getStringExtra("Location Key");
        final String item = intent.getStringExtra("Item");
        Donation donation = db.getItem(locationKey, item);

        loadTV(donation);
    }

    /**
     * Fills the text view with all the attributes of the location;
     * basically formatted to have a space separating different attributes.
     * Acquires the key for the selected location from the intent as passed from
     * the previous Activity
     * @param currentItem the donation to load the details of
     */
    private void loadTV(Donation currentItem)
    {
        String toShow = returnContents(currentItem);
        TextView detailHolder = findViewById(R.id.DetailHolder);
        detailHolder.setMovementMethod(new ScrollingMovementMethod());
        detailHolder.setText(toShow);
    }

    /**
     * This compiles all the text to be displayed on the screen into a string that is returned
     *
     * @param item the donation from which information is being extracted
     * @return the text to be placed in the text view
     */
    private static String returnContents(Donation item)
    {
        return item.toStringDonationDetail();
    }

    /**
     * Handles back press click; takes user back to previous activity
     *
     * @param view Automatic parameter for user interaction
     */
    public void ClickedBackButton(View view) {
        onBackPressed();
    }
}
