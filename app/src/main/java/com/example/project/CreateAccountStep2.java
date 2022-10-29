package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class CreateAccountStep2 extends AppCompatActivity {

    EditText firstName, lastName, role;
    Button back, finish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account_step2);

        firstName = findViewById(R.id.editTextTextPersonName3);
        lastName = findViewById(R.id.editTextTextPersonName4);
        role = findViewById(R.id.editTextTextPersonName5);
        back = findViewById(R.id.button24);
        finish = findViewById(R.id.button25);

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToCreateAccount();
            }
        });

        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWelcomePage();
            }
        });
    }

    private void goToCreateAccount() {
        Intent intent = new Intent(this, CreateAccount.class);
        startActivity(intent);
    }

    private void goToWelcomePage() {
        Intent intent = new Intent(this, WelcomePage.class);
        startActivity(intent);
    }
}