package com.example.buscapet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

public class TelaAnimais extends Activity {

    private ImageView userButton;
    private ImageView petButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_animais);
        Initiate();

        userButton.setOnClickListener(v -> {
            Intent intent = new Intent (TelaAnimais.this, UserAccount.class);
            startActivity(intent);
        });

        petButton.setOnClickListener(v -> {
            Intent intent = new Intent (TelaAnimais.this, PetRegistration.class);
            startActivity(intent);
        });
    }

    private void Initiate(){
        userButton = findViewById(R.id.userButton);
        petButton = findViewById(R.id.petButton);
    }
}
