package com.example.breadscrumbs.donation_tracker.SearchStuff;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;

import com.example.breadscrumbs.donation_tracker.DonationStuff.DonationDetail;
import com.example.breadscrumbs.donation_tracker.MainActivity;
import com.example.breadscrumbs.donation_tracker.R;

import java.util.List;

import Model.Donation;
import Model.DonationDatabaseHandler;

public class Search_Category extends AppCompatActivity {

    String locationKey;
    DonationDatabaseHandler db = MainActivity.getDonationsDb();

    ListView category;
    ListView donationItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__category);

        category = findViewById(R.id.category);
        donationItems = findViewById(R.id.donationItems);

        loadCategories();
    }

    public void ClickedBackButton(View view) {
        onBackPressed();
    }

    public void loadCategories() {
        final String[] categories = {"Clothing", "Hat", "Kitchen", "Electronics", "Household", "Other"};
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                categories);

        category.setAdapter(arrayAdapter);

        category.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Log.d("position", categories[position]);
                loadChildren(categories[position]);

            }
        });
    }

    public void loadChildren(String category) {
        Log.d("huzza", "got here");
        final String[] donationsItem = itemsAsList(category);
        Log.d("huzza2", "got here");
        final List<Donation> donations = db.getCategoryItems(category);

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1, android.R.id.text1,
                donationsItem);

        donationItems.setAdapter(arrayAdapter);

        final Context outerContext = this;

        donationItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                String key = "";
                for (Donation donation : donations) {
                    if (donation.getCategory() == donationsItem[position]) {
                        key = donation.getLocation().getKey();
                    }
                }
                Log.d("foudn key", key);
                Intent newIntent = new Intent(outerContext, DonationDetail.class);
                newIntent.putExtra("Location Key", key);
                newIntent.putExtra("Item", (String) parent.getItemAtPosition(position));
                startActivity(newIntent);
            }
        });

    }


    private String[] itemsAsList(String item) {
        Log.d("blah", item);
        String[] toReturn = new String[db.getCategoryItems(item).size()];
        int index = 0;
        for (Donation donation : db.getCategoryItems(item)) {
            toReturn[index] = donation.getItem();
            ++index;
        }
        return toReturn;
    }
}
