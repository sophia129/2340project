package com.example.breadscrumbs.donation_tracker.LocationStuff;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.breadscrumbs.donation_tracker.R;

import Model.locationModel;

public class location extends AppCompatActivity {

    // Variables/Constants
    public static String TAG = "MY_APP";
    public ListView locationsLV;
    String userEmail;


    /**
     * Gets the extras from the main menu intent and calls the load up method for the list view
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        Intent intent = getIntent();
        userEmail = intent.getStringExtra("email");

        locationModel.readLocationData(getResources().openRawResource(R.raw.locationdata));

        locationsLV = (ListView) findViewById(R.id.LocationsLV);

        loadLV();
    }

    /**
     * Loads the list view with the names of the locations; upon being clicked, a new Activity
     * is started and the key of the chosen location is passed along
     */
    private void loadLV()
    {
        final String[] names = Model.locationModel.returnLocationNames();

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                names);

        locationsLV.setAdapter(arrayAdapter);

        final Context outerContext = this;

        locationsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                String key = Integer.toString(position + 1);

                Intent newIntent = new Intent(outerContext, LocationDetail.class);
                newIntent.putExtra("Location Key", key);
                newIntent.putExtra("email", userEmail);
                startActivity(newIntent);
            }
        });

    }

    /**
     * Handles back press click; takes user back to MainActivity
     */
    public void ClickedBackButton(View view) {
        onBackPressed();
    }
}