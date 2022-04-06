package com.example.buscapet;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class SignIn extends AppCompatActivity {

    private TextView signUpLink;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        
        getSupportActionBar().hide();
        initiateComponents();

        signUpLink.setOnClickListener(new View.OnClickListener () {
           @Override
           public void onClick(View v) {
               
               Intent intent = new Intent(SignIn.this, SignUp.class);
               startActivity(intent);
           }
        });
    }

    private void initiateComponents() {
        signUpLink = findViewById(R.id.signUpLink);
    }
}