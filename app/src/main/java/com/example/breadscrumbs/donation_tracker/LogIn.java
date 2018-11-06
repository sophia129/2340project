package com.example.breadscrumbs.donation_tracker;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.EditText;
import Model.LogInModel;

/**
 * LogIn class that handles input of username and password and checks the validity
 */
public class LogIn extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_log_in);
    }

    @Override
    protected void onResume()
    {
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
    public void ClickLogIn(View view) {
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.Password);

        final Editable emailText = email.getText();
        final Editable passwordText = password.getText();
        final String emailString = emailText.toString();
        final String passwordString = passwordText.toString();

        if (LogInModel.validSignIn(emailString, passwordString)) {
            Intent newIntent = new Intent(this, MainMenu.class);
            newIntent.putExtra("email", emailString);
            startActivity(newIntent);
        } else {
            AlertDialog.Builder dialog = new AlertDialog.Builder(this);
            dialog.setTitle("Invalid Credentials");
            dialog.setMessage("Please make sure you enter valid credentials.");
            dialog.setPositiveButton("OK", null);
            dialog.show();
        }
    }

    /**
     * Puts things back to the default state (meant for when the user returns from MainMenu).
     */
    private void resetPage()
    {
        EditText userName = findViewById(R.id.email);
        EditText password = findViewById(R.id.Password);

        userName.setText("");
        password.setText("");
    }

}
