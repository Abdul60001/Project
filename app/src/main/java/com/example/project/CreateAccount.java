package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {

    EditText usernameField, passwordField, confirmPasswordField, userRoleField;
    Button back, next;
    ArrayList<User> userList;
    DBHandler dbHandler;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        builder = new AlertDialog.Builder(this);

        usernameField = findViewById(R.id.editTextTextEmailAddress2);
        passwordField = findViewById(R.id.editTextTextPassword2);
        confirmPasswordField = findViewById(R.id.editTextTextPassword3);
        userRoleField = findViewById(R.id.editTextTextRole);
        back = findViewById(R.id.button24);
        next = findViewById(R.id.button25);

        dbHandler = new DBHandler(this);
        userList = new ArrayList<>();
        syncUserList();

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMainActivity();
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameInput = usernameField.getText().toString().toLowerCase();
                String passwordInput = passwordField.getText().toString();
                String confirmPasswordInput = confirmPasswordField.getText().toString();
                String userRoleInput = userRoleField.getText().toString().toLowerCase();

                if(!isValidEmailAddress(usernameInput)) {
                    displayDialogWithMessage("Error: Invalid email address.");
                }
                else if(usernameAlreadyExists(usernameInput)) {
                    displayDialogWithMessage("Error: Username/email already used.");
                }
                else if(!passwordInput.equals(confirmPasswordInput)) {
                    displayDialogWithMessage("Error: Passwords don't match.");
                }
                else if(passwordInput.length() < 8) {
                    displayDialogWithMessage("Error: Password must be at least 8 characters.");
                }
                else if(!isValidUserRoleInput(userRoleInput)) {
                    displayDialogWithMessage("Error: Invalid role type.");
                }
                else {
                    User currentUser = new User(usernameInput, passwordInput, userRoleInput);
                    dbHandler.addUser(currentUser);
                    goToWelcomePage(currentUser);
                }
            }
        });
    }

    private void displayDialogWithMessage(String message) {
        builder.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {}
                });
        AlertDialog alert = builder.create();
        alert.show();
    }

    private boolean isValidEmailAddress(String email) {
        String emailPattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        return Pattern.compile(emailPattern).matcher(email).matches();
    }

    private boolean isValidUserRoleInput(String userRoleInput) {
        return userRoleInput.equals("student") || userRoleInput.equals("instructor") || userRoleInput.equals("admin");
    }

    private boolean usernameAlreadyExists(String username) {
        for(User u : userList) {
            if(u.getUsername().equals(username.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private void goToMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void goToWelcomePage(User currentUser) {
        Intent intent = new Intent(this, WelcomePage.class);
        intent.putExtra("current_user", currentUser);
        startActivity(intent);
    }

    private void syncUserList() {
        userList.clear();
        Cursor cursor = dbHandler.getUsers();
        while (cursor.moveToNext()) {
            userList.add(new User(Integer.valueOf(cursor.getString(0)), cursor.getString(1), cursor.getString(2), cursor.getString(3)));
        }
        cursor.close();
    }




}