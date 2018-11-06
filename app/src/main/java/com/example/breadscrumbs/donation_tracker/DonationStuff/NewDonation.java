package com.example.breadscrumbs.donation_tracker.DonationStuff;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.breadscrumbs.donation_tracker.MainActivity;
import com.example.breadscrumbs.donation_tracker.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Model.DonationDatabaseHandler;
import Model.Donation;
import Model.Location;
import Model.LocationSQLiteDBHandler;

/**
 * Handles the creation of a new donation by the user
 */
public class NewDonation extends AppCompatActivity {

    private EditText item;
    private EditText description;
    private EditText value;
    private Spinner categorySpinner;
    private Location currentLocation;
    private EditText comments;

    private final DonationDatabaseHandler db = MainActivity.getDonationsDb();
    private final LocationSQLiteDBHandler dbLocations = MainActivity.getLocationsDb();

    private String locationKey = "";

    /**
     * Gets the extras from the LocationDetail activity and loads the category
     * spinner with preset categories
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_add_donation);

        Intent intent = getIntent();
        locationKey = intent.getStringExtra("Location Key");

        currentLocation = dbLocations.getLocation(locationKey);

        item = findViewById(R.id.item);
        description = findViewById(R.id.description);
        value = findViewById(R.id.value);
        categorySpinner = findViewById(R.id.category);
        comments = findViewById(R.id.comments);

        ArrayList<String> typeList = new ArrayList<>();
        typeList.add("Clothing");
        typeList.add("Hat");
        typeList.add("Kitchen");
        typeList.add("Electronics");
        typeList.add("Household");
        typeList.add("Other");

        ArrayAdapter<String> adapter = (ArrayAdapter<String>) new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, typeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

    }
    @Override
    protected void onResume() {
        super.onResume();
        resetPage();
    }

    /**
     * This is responsible for creating a donation to add to the database from the filled
     * in fields on the screen
     *
     * @param view Automatic parameter for user interaction
     */
    public void createDonation(View view) {

        final Editable itemText = item.getText();
        final String itemString = itemText.toString();
        boolean itemEmpty = "".equals(itemString);
        final Editable descriptionText = description.getText();
        final String descriptionString = descriptionText.toString();
        boolean descriptionEmpty = "".equals(descriptionString);
        final Editable valueText = value.getText();
        final String valueString = valueText.toString();
        boolean valueEmpty = "".equals(valueString);
        //boolean categoryEmpty = "".equals(categorySpinner.getSelectedItem().toString());

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        final Date date = new Date();
        final String timeStamp = formatter.format(date);

        final Object selectedItem = categorySpinner.getSelectedItem();
        final String selectedItemString = selectedItem.toString();

        final Editable commentsText = comments.getText();
        final String commentsString = commentsText.toString();

        Donation toAdd = new Donation(itemString, descriptionString,
                timeStamp, valueString, currentLocation,
                selectedItemString, commentsString);

        if (itemEmpty || descriptionEmpty || valueEmpty) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Missing Entry!");
            dialog.setMessage("Please ensure all fields are filled");
            dialog.setPositiveButton("OK", null);
            dialog.show();
        } else {
            db.addDonation(toAdd, locationKey);
            db.addDonation(toAdd);
            onBackPressed();
        }

    }

    /**
     * Puts things back to the default state (meant for when the user returns from MainMenu).
     */
    private void resetPage() {
        item = findViewById(R.id.item);
        description = findViewById(R.id.description);
        value = findViewById(R.id.value);

        item.setText("");
        description.setText("");
        value.setText("");
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

