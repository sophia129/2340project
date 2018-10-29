package com.example.breadscrumbs.donation_tracker.SearchStuff;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.breadscrumbs.donation_tracker.R;

public class Search_Options extends AppCompatActivity {

    String userEmail;
    String key;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_options);

        Intent intent = getIntent();
        userEmail = intent.getStringExtra("email");
        System.out.println("email in location: " + userEmail);

    }

    public void ClickedCategoryButton(View view) {
        Intent newIntent = new Intent(this, Search_Category.class);
        newIntent.putExtra("email", userEmail);
        startActivity(newIntent);
    }


    public void ClickedNameButton(View view) {
        Intent newIntent = new Intent(this, Search_Names.class);
        newIntent.putExtra("email", userEmail);
        newIntent.putExtra("Location Key", key);
        startActivity(newIntent);
    }

    public void ClickedBackButton(View view) {
        onBackPressed();
    }
}
