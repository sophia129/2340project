package com.example.breadscrumbs.donation_tracker;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import Model.Location;
import Model.LocationSQLiteDBHandler;
import Model.locationModel;

public class location extends AppCompatActivity {

    // Variables/Constants
    public static String TAG = "MY_APP";
    public ListView locationsLV;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        // Use these lines to hide the action bar for each page
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

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

        /*
        int index = 0;
        for (String name: names) {
            Log.d("Name" + index, name);
            ++index;
        }
        */

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