package com.example.breadscrumbs.donation_tracker;

import android.os.Bundle;
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

    DonationDatabaseHandler db;

    String locationKey;
    String userEmail;
    ListView DonationsList;

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SearchAdapter adapter;

    MaterialSearchBar materialSearchBar;
    List<Donation> suggestList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search__name);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        //Get current location and set the instance variable
//        Intent intent = getIntent();
//        userEmail = intent.getStringExtra("email");
//        locationKey = intent.getStringExtra("Location Key");


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
                List<Donation> suggest = new ArrayList<>();
                for(Donation search: suggestList) {
                    if (search.getItem().toLowerCase().contains(materialSearchBar.getText().toLowerCase())) {
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
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onSearchConfirmed(CharSequence text) {
                startSearch(text.toString());
            }

            @Override
            public void onButtonClicked(int buttonCode) {

            }
        });


        //Init Adapter default set all result

        adapter = new SearchAdapter(this, db.getAllDonationItems());
        recyclerView.setAdapter(adapter);
    }


    private void startSearch(String text) {
       adapter = new SearchAdapter(this, db.getPotentialItem(text));
       recyclerView.setAdapter(adapter);
    }

    public void loadSuggestList() {
        suggestList = db.getAllDonationItems();
        materialSearchBar.setLastSuggestions(suggestList);
    }

    public void ClickedBackButton(View view) {
        onBackPressed();
    }
}
