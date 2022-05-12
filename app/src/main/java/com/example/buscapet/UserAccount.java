package com.example.buscapet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class UserAccount extends AppCompatActivity {

    private TextView feedButton, nomeUsuario, emailUsuario;
    private Button bt_deslogar;

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

        nomeUsuario= findViewById(R.id.userName);
        emailUsuario = findViewById(R.id.userEmail);
        bt_deslogar = findViewById(R.id.logoutButton);



        FirebaseFirestore db = FirebaseFirestore.getInstance();
        String usuarioId;
        String email = FirebaseAuth.getInstance().getCurrentUser().getEmail();

        usuarioId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DocumentReference documentReference = db.collection("Usuarios").document(usuarioId);
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot != null){
                    nomeUsuario.setText(documentSnapshot.getString("nome"));
                    emailUsuario.setText(email);
                }
            }
        });

        bt_deslogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(UserAccount.this,SignIn.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private void Initiate(){
        feedButton = findViewById(R.id.feedButton);
    }
}