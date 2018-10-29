package com.example.breadscrumbs.donation_tracker.SearchStuff;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;

import com.example.breadscrumbs.donation_tracker.MainActivity;
import com.example.breadscrumbs.donation_tracker.R;

import Model.DonationDatabaseHandler;

public class Search_Category extends AppCompatActivity {

    String locationKey;
    DonationDatabaseHandler db = MainActivity.getDonationsDb();

    ExpandableListView expandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__category);

        Intent intent = getIntent();
        locationKey = intent.getStringExtra("LocationKey");
    }

    public void ClickedBackButton(View view) {
        onBackPressed();
    }
}
