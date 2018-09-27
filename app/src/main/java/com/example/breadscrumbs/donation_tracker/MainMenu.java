package com.example.breadscrumbs.donation_tracker;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use these lines to hide the action bar for each page
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_main_menu);
    }

    /**
     * Handles back press click; takes user back to LogIn
     */
    public void ClickedBackButton(View view) {
        onBackPressed();
    }

}
