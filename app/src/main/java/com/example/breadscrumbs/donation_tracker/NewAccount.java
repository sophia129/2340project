package com.example.breadscrumbs.donation_tracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;


import Model.SQLiteDatabaseHandler;
import Model.User;

/**
 * NewAccount class that handles the creation of a new account
 */
public class NewAccount extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText name;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private Spinner userTypeSpinner;

    private final SQLiteDatabaseHandler db = MainActivity.getDb();

    /**
     *
     * @param savedInstanceState
     *
     * Creates New Account Screen and allows for interaction
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_new_account);

        name = findViewById(R.id.userName);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        confirmPassword = findViewById(R.id.confirmPassword);
        userTypeSpinner = findViewById(R.id.userSpinner);
        Button createAccountButton = findViewById(R.id.createAccount);

        ArrayList<String> typeList = new ArrayList<>();
        for (User.UserType u : User.UserType.values()) {
            typeList.add(u.getUserTypeString());
        }

        ArrayAdapter<String> adapter = (ArrayAdapter<String>) new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, typeList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userTypeSpinner.setAdapter(adapter);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
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
     * Handles back press click; takes user back to previous activity
     *
     * @param view Automatic parameter for user interaction
     */
    public void ClickedBackButton(View view) {
        onBackPressed();
    }

    /**
     * Handles log in click; checks validity of credentials before starting MainMenu.
     * If the user is not valid, then the user will not be taken to MainMenu.
     *
     * @param view Automatic parameter for user interaction
     */
    public void ClickCreateAccount(View view) {
        final Object selectedUserType = userTypeSpinner.getSelectedItem();
        String selectedUserTypeString = selectedUserType.toString();
        selectedUserTypeString = selectedUserTypeString.toUpperCase();
        String noSpaceString = Model.User.removeSpacesFromUserTypeString(selectedUserTypeString);
        User.UserType userType = User.UserType.valueOf(noSpaceString);
        final Editable nameText = name.getText();
        final String nameString = nameText.toString();
        final Editable emailText = email.getText();
        final String emailString = emailText.toString();
        final Editable passwordText = password.getText();
        final String passwordString = passwordText.toString();
        final Editable confirmPasswordText = confirmPassword.getText();
        final String confirmPasswordString = confirmPasswordText.toString();

        User userToAdd = new User(nameString,
                emailString,
                passwordString, userType);

        final int passwordLength = passwordString.length();
        boolean passwordLengthOkay = passwordLength >= 8;
        final List<User> allUsers = db.allUsers();
        final boolean containsUser = allUsers.contains(userToAdd);

        if ("".equals(nameString) || "".equals(emailString)
                || "".equals(passwordString) || "".equals(confirmPasswordString)) {

            /*AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Missing Entry!");
            dialog.setMessage("Please ensure all fields are filled");
            dialog.setPositiveButton("OK", null);
            dialog.show();*/

            displayMissingEntry();

        } else if (!checkPasswordMatch(passwordString,
                confirmPasswordString)) {

            /*AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Matching Error!");
            dialog.setMessage("Passwords don't match. Please check again.");
            dialog.setPositiveButton("OK", null);
            dialog.show();*/

            displayMatchingError();

        } else if (!passwordLengthOkay) {

            /*AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Password Error!");
            dialog.setMessage("Password must be at least 8 characters.");
            dialog.setPositiveButton("OK", null);
            dialog.show();*/

            displayPasswordLengthBad();

        } else if (containsUser) {

            /*AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Email Error!");
            dialog.setMessage("That email already exists in our system.");
            dialog.setPositiveButton("OK", null);
            dialog.show();*/

            displayUserExists();
        } else {
            db.addUser(userToAdd);
            Intent newIntent = new Intent(this, MainActivity.class);
            startActivity(newIntent);
        }
    }

    /**
     * Puts things back to the default state (meant for when the user returns from MainMenu).
     */
    private void resetPage() {
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

    /**
     * Display that an entry is missing
     */
    private void displayMissingEntry() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Missing Entry!");
        dialog.setMessage("Please ensure all fields are filled");
        dialog.setPositiveButton("OK", null);
        dialog.show();
    }

    /**
     * Display that the passwords do not match
     */
    private void displayMatchingError() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Matching Error!");
        dialog.setMessage("Passwords don't match. Please check again.");
        dialog.setPositiveButton("OK", null);
        dialog.show();
    }

    /**
     * Display that the Password length is lest than 8
     */
    private void displayPasswordLengthBad() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Password Error!");
        dialog.setMessage("Password must be at least 8 characters.");
        dialog.setPositiveButton("OK", null);
        dialog.show();
    }

    /**
     *  Display that a User already exists in the system
     */
    private void displayUserExists() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Email Error!");
        dialog.setMessage("That email already exists in our system.");
        dialog.setPositiveButton("OK", null);
        dialog.show();
    }


    /**
     * Confirms whether the password is the same as the confirmed password
     *
     * @param password The original password
     * @param confirmPassword The password that should match the original password
     * @return true is the two parameters match, else false
     */
    private static boolean checkPasswordMatch(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

}

