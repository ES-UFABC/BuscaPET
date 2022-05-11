package com.example.buscapet;

import android.content.Intent;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.auth.User;

public class SignIn extends AppCompatActivity {

    private TextView signUpLink;
    private EditText labelEmail,label_password;
    private Button signInButton;
    private ProgressBar progressBar;

    String[] mensagens = {"Preencha todos os campos", "Login realizado com sucesso!"};


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
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = labelEmail.getText().toString();
                String senha = label_password.getText().toString();

                if(email.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v,mensagens[0],Snackbar.LENGTH_SHORT);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                } else{ //se preencheu tudo certo fazer a autenticação do usuário
                    Autenticarusuario(v);

                }
            }
        });


    }

    private void Autenticarusuario(View v){
        String email = labelEmail.getText().toString();
        String senha = label_password.getText().toString();

        //verificar o login do usuario
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressBar.setVisibility(View.VISIBLE);
                    Snackbar snackbar = Snackbar.make(v,mensagens[1],Snackbar.LENGTH_SHORT);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            TelaPrincipal();
                        }
                    },3000);
                }else{
                    String erro;
                    try {
                        throw task.getException();

                    }catch (FirebaseAuthInvalidCredentialsException e) {
                        erro = "Usuário inválido";

                    }catch (Exception e){
                        erro= "Erro ao logar";
                    }
                    Snackbar snackbar = Snackbar.make(v,erro,Snackbar.LENGTH_SHORT);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                }

            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser usuarioAtual = FirebaseAuth.getInstance().getCurrentUser();

        if(usuarioAtual!= null){
            //UserAccount();  N FUNCIONOU
        }
    }

    private void TelaPrincipal(){
        //Intent intent = new Intent(SignIn.this,UserAccount.class);
        Intent intent = new Intent(SignIn.this,TelaAnimais.class);
        startActivity(intent);
        finish();
    }

    private void initiateComponents() {
        signUpLink = findViewById(R.id.signUpLink);
        labelEmail = findViewById(R.id.labelEmail);
        label_password = findViewById(R.id.labelPassword);
        signInButton = findViewById(R.id.signInButton);
        progressBar = findViewById(R.id.progressBar);

    }
}