package com.example.breadscrumbs.donation_tracker.SearchStuff;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.breadscrumbs.donation_tracker.R;

/**
 * Controller for selecting which type of search to perform
 */
public class Search_Options extends AppCompatActivity {

    private String userEmail = "";
    private String key;

    /**
     * Gets the extras from the MainMenu/LocationDetail activity
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_options);

        Intent intent = getIntent();
        userEmail = intent.getStringExtra("email");
        key = intent.getStringExtra("Location Key");
    }

    /**
     * Responsible for starting the Search_Category activity; loads up intents
     *
     * @param view Automatic parameter for user interaction
     */
    public void ClickedCategoryButton(View view) {
        Intent newIntent = new Intent(this, Search_Category.class);
        newIntent.putExtra("Email", userEmail);
        newIntent.putExtra("Location Key", key);
        startActivity(newIntent);
    }

    /**
     * Responsible for starting the Search_Names activity; loads up intents
     *
     * @param view Automatic parameter for user interaction
     */
    public void ClickedNameButton(View view) {
        Intent newIntent = new Intent(this, Search_Names.class);
        newIntent.putExtra("Email", userEmail);
        newIntent.putExtra("Location Key", key);
        startActivity(newIntent);
    }

    /**
     * Handles back press click; takes user back to previous activity
     *
     * @param view Automatic parameter for user interaction
     */
    public void ClickedBackButton(View view) {
        onBackPressed();
    }
}
