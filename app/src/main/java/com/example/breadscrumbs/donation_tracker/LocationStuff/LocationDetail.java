package com.example.breadscrumbs.donation_tracker.LocationStuff;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Button;

import com.example.breadscrumbs.donation_tracker.DonationStuff.DonationList;
import com.example.breadscrumbs.donation_tracker.MainActivity;
import com.example.breadscrumbs.donation_tracker.DonationStuff.NewDonation;
import com.example.breadscrumbs.donation_tracker.R;
import com.example.breadscrumbs.donation_tracker.SearchStuff.Search_Options;

import Model.Location;
import Model.LocationDetailModel;
import Model.LocationSQLiteDBHandler;
import Model.SQLiteDatabaseHandler;
import Model.User;

/**
 * Shows the information for the chosen location
 */
public class LocationDetail extends AppCompatActivity {

    private final LocationSQLiteDBHandler db = MainActivity.getLocationsDb();
    private final SQLiteDatabaseHandler usersDb = MainActivity.getDb();
    private TextView detailHolder;

    private Location currentLocation;
    private String userEmail;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_location_detail);

        //Get current location and set the instance variable
        Intent intent = getIntent();
        key = intent.getStringExtra("Location Key");
        userEmail = intent.getStringExtra("email");

        currentLocation = db.getLocation(key);

        Button view = findViewById(R.id.viewDonations);
        Button add = findViewById(R.id.addDonation);

        detailHolder = findViewById(R.id.DetailHolder);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        final WindowManager currentWM = getWindowManager();
        final Display currentDisplay = currentWM.getDefaultDisplay();
        currentDisplay.getMetrics(displayMetrics);
        final int height = displayMetrics.heightPixels; // gets height of display

        RelativeLayout topLayout = findViewById(R.id.topLayout);

        final int sizingConstantForSpacing = 20;
        int forTV = (height - ((topLayout.getLayoutParams().height * 2)
                + (add.getLayoutParams().height - sizingConstantForSpacing)));

        User currentUser = usersDb.getUser(userEmail);
        User.UserType userType = currentUser.getUserType();

        if (userType == User.UserType.USER) {
            view.setVisibility(View.GONE);
            add.setVisibility(View.GONE);
        } else {
            // 1.2 multipliers to approximately account for spacing between them
            final double sizingMultiplierForSpacing = 1.1;
            forTV -= (view.getLayoutParams().height
                    + add.getLayoutParams().height) * sizingMultiplierForSpacing;
        }


        detailHolder.getLayoutParams().height = forTV;

        loadTV();
    }

    /**
     * Fills the text view with all the attributes of the location;
     * basically formatted to have a space separating different attributes.
     * Acquires the key for the selected location from the intent as passed from
     * the previous Activity
     */
    private void loadTV()
    {
        String toShow = LocationDetailModel.returnContents(currentLocation);
        TextView detailHolder = findViewById(R.id.DetailHolder);
        detailHolder.setMovementMethod(new ScrollingMovementMethod());
        detailHolder.setText(toShow);
    }

    /**
     * Responsible for starting the NewDonation activity; loads up intents
     *
     * @param view Automatic parameter for user interaction
     */
    public void AddDonation(View view) {

        Intent newIntent = new Intent(this, NewDonation.class);
        newIntent.putExtra("Location", currentLocation.getName());
        newIntent.putExtra("Location Key", key);
        startActivity(newIntent);

    }

    /**
     * Responsible for starting the DonationList activity; loads up intents
     *
     * @param view Automatic parameter for user interaction
     */
    public void ViewDonations(View view) {
        Intent newIntent = new Intent(this, DonationList.class);
        newIntent.putExtra("LocationKey", currentLocation.getKey());
        startActivity(newIntent);
    }

    /**
     * Starts the activity for the search functionality
     *
     * @param view Automatic parameter for user interaction
     */
    public void ClickedSearchButton(View view) {
        Intent newIntent = new Intent(this, Search_Options.class);
        newIntent.putExtra("email", userEmail);
        newIntent.putExtra("Location Key", key);
        startActivity(newIntent);
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
