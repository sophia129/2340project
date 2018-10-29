package com.example.breadscrumbs.donation_tracker.DonationStuff;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Spinner;

import com.example.breadscrumbs.donation_tracker.MainActivity;
import com.example.breadscrumbs.donation_tracker.R;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

import Model.DonationDatabaseHandler;
import Model.Donation;
import Model.Location;
import Model.LocationSQLiteDBHandler;


public class NewDonation extends AppCompatActivity {

    private EditText item;
    private EditText description;
    private EditText value;
    private TextView location;
    private Spinner categorySpinner;
    private Location currentLocation;
    private EditText comments;

    DonationDatabaseHandler db = MainActivity.getDonationsDb();
    LocationSQLiteDBHandler dbLocations = MainActivity.getLocationsDb();

    String locationName = "";
    String locationKey = "";

    /**
     *
     * @param savedInstanceState
     **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);;

        setContentView(R.layout.activity_add_donation);

        Intent intent = getIntent();
        locationKey = intent.getStringExtra("Location Key");

        Log.d("Key", locationKey);

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

        ArrayAdapter<String> adapter = new ArrayAdapter(this,
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
     * This is responsible for creating a donation to add to the database from the filled in fields on the screen.
     */
    public void createDonation(View view) {

        boolean itemEmpty = item.getText().toString().equals("");
        boolean descriptionEmpty = description.getText().toString().equals("");
        boolean valueEmpty = value.getText().toString().equals("");
        boolean categoryEmpty = categorySpinner.getSelectedItem().toString().equals("");

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

        Date date = new Date();
        String timeStamp = formatter.format(date);


        Donation toAdd = new Donation(item.getText().toString(), description.getText().toString(),
                timeStamp, value.getText().toString(), currentLocation, categorySpinner.getSelectedItem().toString(), comments.getText().toString());


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
    public void resetPage() {
        item = findViewById(R.id.item);
        description = findViewById(R.id.description);
        value = findViewById(R.id.value);

        item.setText("");
        description.setText("");
        value.setText("");
    }

    /**
     * Returns user to the LocationDetail view
     */
    public void ClickedBackButton(View view) {
        onBackPressed();
    }
}

