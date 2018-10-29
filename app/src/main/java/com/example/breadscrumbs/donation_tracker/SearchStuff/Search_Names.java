package com.example.breadscrumbs.donation_tracker.SearchStuff;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


import com.example.breadscrumbs.donation_tracker.DonationStuff.DonationDetail;
import com.example.breadscrumbs.donation_tracker.MainActivity;
import com.example.breadscrumbs.donation_tracker.R;

import Model.Donation;
import Model.DonationDatabaseHandler;

public class Search_Names extends AppCompatActivity {

    DonationDatabaseHandler db = MainActivity.getDonationsDb();
    ListView inventory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__names);

        Intent intent = getIntent();
        inventory = findViewById(R.id.inventory);
    }


    public void ClickedBackButton(View view) {
        onBackPressed();
    }

    public void ClickedCheckInventory(View view) {
        EditText searchBar = findViewById(R.id.search_bar);
        loadInventory(searchBar.getText().toString().toLowerCase());
    }

    private void loadInventory(String item) {

        final String[] items = itemsAsList(item);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                items);
        inventory.setAdapter(arrayAdapter);

        final Context outerContext = this;

        inventory.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {

                int key = position + 1;
                Intent newIntent = new Intent(outerContext, DonationDetail.class);

                newIntent.putExtra("Item", (String) parent.getItemAtPosition(position));
                startActivity(newIntent);
            }
        });
    }

    private String[] itemsAsList(String item) {
        Log.d("Array items","NOt calledx");
        String[] toReturn = new String[db.getPotentialItem(item).size()];
        Log.d("Array items", toReturn.toString());
        int index = 0;
        for (Donation donation : db.getPotentialItem(item)) {
            toReturn[index] = donation.getItem();
            ++index;
        }
        return toReturn;
    }
}


