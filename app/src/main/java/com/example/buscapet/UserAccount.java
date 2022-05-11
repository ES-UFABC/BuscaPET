package com.example.buscapet;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class UserAccount extends AppCompatActivity {

    private TextView feedButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);

        getSupportActionBar().hide();
        Initiate();

        feedButton.setOnClickListener(v -> {
            Intent intent = new Intent (UserAccount.this, TelaAnimais.class);
            startActivity(intent);
        });
    }

    private void Initiate(){
        feedButton = findViewById(R.id.feedButton);
    }
}