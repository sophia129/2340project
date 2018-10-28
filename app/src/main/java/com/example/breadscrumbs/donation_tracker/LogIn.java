package com.example.breadscrumbs.donation_tracker;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import Model.LogInModel;
import Model.SQLiteDatabaseHandler;

public class LogIn extends AppCompatActivity {

    SQLiteDatabaseHandler db = MainActivity.getDb();

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
     * Handles back press click; takes user back to MainActivity
     */
    public void ClickedBackButton(View view) {
        onBackPressed();
    }

    /**
     * Handles log in click; checks validity of credentials before starting MainMenu.
     * If the user is not valid, then the user will not be taken to MainMenu.
     */
    public void ClickLogIn(View view) {
        EditText email = findViewById(R.id.email);
        EditText password = findViewById(R.id.Password);

        if (LogInModel.validSignIn(email.getText().toString(), password.getText().toString())) {
            Intent newIntent = new Intent(this, MainMenu.class);
            newIntent.putExtra("email", email.getText().toString());
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
    public void resetPage()
    {
        EditText userName = findViewById(R.id.email);
        EditText password = findViewById(R.id.Password);

        userName.setText("");
        password.setText("");
    }

}
