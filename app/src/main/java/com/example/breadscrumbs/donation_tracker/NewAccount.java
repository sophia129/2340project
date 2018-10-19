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

public class NewAccount extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText name;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private Spinner userTypeSpinner;
    private Button createAccountButton;

    SQLiteDatabaseHandler db = MainActivity.getDb();

    /**
     *
     * @param savedInstanceState
     *
     * Creates New Account Screen and allows for interaction
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        setContentView(R.layout.activity_new_account);

        name = findViewById(R.id.userName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        userTypeSpinner = findViewById(R.id.userSpinner);
        createAccountButton = findViewById(R.id.createAccount);

        ArrayList<String> typeList = new ArrayList<>();
        for (User.UserType u : User.UserType.values()) {
            typeList.add(u.getUserTypeString());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, typeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                ClickCreateAccount(v);
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
    public void ClickCreateAccount(View view) {
        User.UserType userType = User.UserType.valueOf(userTypeSpinner.getSelectedItem().toString().toUpperCase());
        User userToAdd = new User(name.getText().toString(),
                email.getText().toString(),
                password.getText().toString(), userType);

        boolean nameEmpty = name.getText().toString().equals("");
        boolean emailEmpty = email.getText().toString().equals("");
        boolean passwordEmpty = password.getText().toString().equals("");
        boolean confirmPasswordEmpty = confirmPassword.getText().toString().equals("");

        boolean passwordsMatch = NewAccountModel.checkPasswordMatch(password.getText().toString(),
                confirmPassword.getText().toString());

        boolean passwordLengthOkay = password.getText().toString().length() >= 8;

//        // PERSISTANCE!!
//
        if (nameEmpty || emailEmpty || passwordEmpty || confirmPasswordEmpty) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Missing Entry!");
            dialog.setMessage("Please ensure all fields are filled");
            dialog.setPositiveButton("OK", null);
            dialog.show();
        } else if (!passwordsMatch) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Matching Error!");
            dialog.setMessage("Passwords don't match. Please check again.");
            dialog.setPositiveButton("OK", null);
            dialog.show();
        } else if (!passwordLengthOkay) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Password Error!");
            dialog.setMessage("Password must be at least 8 characters.");
            dialog.setPositiveButton("OK", null);
            dialog.show();
        } else if (db.allUsers().contains(userToAdd)) {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Email Error!");
            dialog.setMessage("That email already exists in our system.");
            dialog.setPositiveButton("OK", null);
            dialog.show();
        } else {
            db.addUser(userToAdd);
            Intent newIntent = new Intent(this, MainActivity.class);
            startActivity(newIntent);
        }

    }

    /**
     * Puts things back to the default state (meant for when the user returns from MainMenu).
     */
    public void resetPage() {
        name = findViewById(R.id.userName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);

        name.setText("");
        email.setText("");
        password.setText("");
        confirmPassword.setText("");
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}

