package com.example.breadscrumbs.donation_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;

import Model.NewAccountModel;

import Model.SQLiteDatabaseHandler;
import Model.User;


public class NewDonation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText item;
    private EditText description;
    private EditText timestamp;
    private EditText value;
    private EditText location;
    private Spinner categorySpinner;
    private Button addDonationButton;

    SQLiteDatabaseHandler db = MainActivity.getDb();

    /**
     *
     * @param savedInstanceState
     **/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_add_donation);

        item = findViewById(R.id.item);
        description = findViewById(R.id.description);
        timestamp = findViewById(R.id.timestamp);
        value = findViewById(R.id.value);
        location = findViewById(R.id.location);
        categorySpinner = findViewById(R.id.category);
        addDonationButton = findViewById(R.id.addDonation);

        ArrayList<String> typeList = new ArrayList<>();
        typeList.add("Clothing");
        typeList.add("Hat");
        typeList.add("Kitchen");
        typeList.add("Electronics");
        typeList.add("Household");
        typeList.add("Other");

        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, typeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(adapter);

        addDonationButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createDonation(v);
            }
        });


    }
    @Override
    protected void onResume() {
        super.onResume();
        resetPage();
    }

    /**
     * Handles back press click; takes user back to MainActivity
     */
    public void ClickedBackButton(View view) {
        onBackPressed();
    }

    /**
     * Handles log in click; checks validity of credentials before starting MainMenu.
     * If the user is not valid, then the user will not be taken to MainMenu.
     */
    public void createDonation(View view) {
//        User userToAdd = new User(name.getText().toString(),
//                email.getText().toString(),
//                password.getText().toString(), User.UserType.USER); //CHANGE THIS!!!!
//
        boolean itemEmpty = item.getText().toString().equals("");
        boolean descriptionEmpty = description.getText().toString().equals("");
        boolean timestampEmpty = timestamp.getText().toString().equals("");
        boolean valueEmpty = value.getText().toString().equals("");
        boolean locationEmpty = location.getText().toString().equals("");

//         PERSISTANCE!!

//        if (itemEmpty || descriptionEmpty || timestampEmpty || valueEmpty || locationEmpty) {
//            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//            dialog.setTitle("Missing Entry!");
//            dialog.setMessage("Please ensure all fields are filled");
//            dialog.setPositiveButton("OK", null);
//            dialog.show();
//        } else if (db.allUsers().contains(userToAdd)) {
//            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
//            dialog.setTitle("Email Error!");
//            dialog.setMessage("That email already exists in our system.");
//            dialog.setPositiveButton("OK", null);
//            dialog.show();
//        } else {
//            db.addUser(userToAdd);
//            Intent newIntent = new Intent(this, MainActivity.class);
//            startActivity(newIntent);
//        }

    }

    /**
     * Puts things back to the default state (meant for when the user returns from MainMenu).
     */
    public void resetPage() {
        item = findViewById(R.id.item);
        description = findViewById(R.id.description);
        timestamp = findViewById(R.id.timestamp);
        value = findViewById(R.id.value);

        item.setText("");
        description.setText("");
        timestamp.setText("");
        value.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

