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

public class MainActivity extends AppCompatActivity {

    EditText usernameInput, passwordInput;
    Button login, createAccount;
    ArrayList<User> userList;
    DBHandler dbHandler;

    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        builder = new AlertDialog.Builder(this);

        usernameInput = findViewById(R.id.editTextTextEmailAddress);
        passwordInput = findViewById(R.id.editTextTextPassword);
        login = findViewById(R.id.button2);
        createAccount = findViewById(R.id.button);

        dbHandler = new DBHandler(this);
        userList = new ArrayList<>();
        syncUserList();

        createDefaultAdminUser();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = usernameInput.getText().toString().toLowerCase();
                String password = passwordInput.getText().toString();

                User userInput = authenticateAndReturnUser(username, password);

                if(userInput!=null) {
                    goToWelcomePage(userInput);
                }
                else {
                    displayDialogWithMessage("Error: Invalid password or account does not exist.");
                }
            }
        });

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCreateAccount();
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

    private void createDefaultAdminUser() {
        if(!usernameAlreadyExists("admin")) {
            dbHandler.addUser(new User(1, "admin", "admin123", "admin"));
        }
        syncUserList();
    }

    private boolean usernameAlreadyExists(String username) {
        for(User u : userList) {
            if(u.getUsername().equals(username.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    private User authenticateAndReturnUser(String username, String password) {
        for(User u : userList) {
            if (u.getUsername().equals(username) && u.getPassword().equals(password)) {
                return u;
            }
        }
        return null;
    }

    private void goToCreateAccount() {
        Intent intent = new Intent(this, CreateAccount.class);
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