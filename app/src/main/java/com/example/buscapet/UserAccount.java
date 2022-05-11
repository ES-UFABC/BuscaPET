package com.example.buscapet;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class UserAccount extends AppCompatActivity {
    private TextView nomeUsuario,emailUsuario;
    private Button bt_deslogar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        IniciarComponentes();
        getSupportActionBar().hide();

        bt_deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent( UserAccount.this,SignIn.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void IniciarComponentes(){
        nomeUsuario = findViewById(R.id.userName);
        emailUsuario = findViewById(R.id.userEmail);
        bt_deslogar = findViewById(R.id.bt_deslogar);
    }
}