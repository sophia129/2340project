package com.example.breadscrumbs.donation_tracker;

import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ListView;

import com.example.breadscrumbs.donation_tracker.Adapter.SearchAdapter;
import com.mancj.materialsearchbar.MaterialSearchBar;

import java.util.ArrayList;
import java.util.List;

import Model.Donation;
import Model.DonationDatabaseHandler;

public class Search_Name extends AppCompatActivity {

    DonationDatabaseHandler db = MainActivity.getDonationsDb();
    String key;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter adapter;

    MaterialSearchBar materialSearchBar;
    List<String> suggestList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__name);

        //init view
        recyclerView = (RecyclerView)findViewById(R.id.recycler_search);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        materialSearchBar  = (MaterialSearchBar)findViewById(R.id.search_bar);

        //init DB for donations
        db = new DonationDatabaseHandler(this);
        System.out.println("Initalized database");


        //Setup search bar
        materialSearchBar.setHint("Serach");
        materialSearchBar.setCardViewElevation(10);
        loadSuggestList();
        materialSearchBar.addTextChangeListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                List<String> suggest = new ArrayList<>();
                for(String search: suggestList) {
                    if (search.toLowerCase().contains(materialSearchBar.getText().toLowerCase())) {
                        suggest.add(search);
                    }

                }
                materialSearchBar.setLastSuggestions(suggest);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        materialSearchBar.setOnSearchActionListener(new MaterialSearchBar.OnSearchActionListener() {
            @Override
            public void onSearchStateChanged(boolean enabled) {
                if(!enabled) {
                   adapter = new SearchAdapter(getBaseContext(), db.getAllDonationItems());
                   recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {
                switch (buttonCode){

                }

            }
        });

        materialSearchBar.OnItemClickListener(int position,) {

        }


        //Init Adapter default set all result
        adapter = new SearchAdapter(this, db.getAllDonationItems());
        recyclerView.setAdapter(adapter);
    }


    private void startSearch(String text) {
       adapter = new SearchAdapter(this, db.getPotentialItem(text));
       recyclerView.setAdapter(adapter);
    }

    public void loadSuggestList() {
        suggestList = db.getAllDonationItemNames();
        materialSearchBar.setLastSuggestions(suggestList);
    }

    public void ClickedBackButton(View view) {
        onBackPressed();
    }
}
