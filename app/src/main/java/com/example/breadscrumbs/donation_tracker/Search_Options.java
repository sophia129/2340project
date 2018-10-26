package com.example.breadscrumbs.donation_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import Model.DonationDatabaseHandler;

public class Search_Options extends AppCompatActivity {

    String userEmail;
    ListView search_inventory;
    ArrayAdapter<String> adapter;
    String locationKey;
    String key;
    ListView DonationsList;
    DonationDatabaseHandler db;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_options);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent = getIntent();
        userEmail = intent.getStringExtra("email");
        System.out.println("email in location: " + userEmail);

    }

//    private void loadLV()
//    {
//        final String[] names = Model.locationModel.returnLocationNames();
//
//        /*
//        int index = 0;
//        for (String name: names) {
//            Log.d("Name" + index, name);
//            ++index;
//        }
//        */
//
//        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
//                this,
//                android.R.layout.simple_list_item_1, android.R.id.text1,
//                names);
//
//        locationsLV.setAdapter(arrayAdapter);
//
//        final Context outerContext = this;
//
//        locationsLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view,
//                                    int position, long id) {
//
//                String key = Integer.toString(position + 1);
//
//                Intent newIntent = new Intent(outerContext, LocationDetail.class);
//                newIntent.putExtra("Location Key", key);
//                newIntent.putExtra("email", userEmail);
//                startActivity(newIntent);
//            }
//        });
//
//    }

    public void ClickedCategoryButton(View view) {
        Intent newIntent = new Intent(this, Search_Category.class);
        newIntent.putExtra("email", userEmail);
        startActivity(newIntent);
    }


    public void ClickedNameButton(View view) {
        Intent newIntent = new Intent(this, Search_Name.class);
        newIntent.putExtra("email", userEmail);
        newIntent.putExtra("Location Key", key);
        startActivity(newIntent);
    }

    public void ClickedBackButton(View view) {
        onBackPressed();
    }
}
