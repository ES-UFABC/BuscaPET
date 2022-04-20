package com.example.buscapet;



import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthEmailException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUp extends AppCompatActivity {

    private EditText edit_nome, edit_email, edit_senha;
    private Button bt_cadastrar;

    String[] mensagens = {"Preencha todos os campos", "Cadastro realizado com sucesso!"};
    String usuarioId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_cadastro);

        getSupportActionBar().hide();
        IniciarComponentes();

        bt_cadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nome = edit_nome.getText().toString();
                String email = edit_email.getText().toString();
                String senha = edit_senha.getText().toString();

                if(nome.isEmpty() || email.isEmpty() || senha.isEmpty()){
                    Snackbar snackbar = Snackbar.make(v,mensagens[0],Snackbar.LENGTH_SHORT);
                   // snackbar.setBackgroundTint(Color.WHITE);
                   // snackbar.setTextColor(Color.BLACK);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                }else{
                    CadastrarUsuario(v);

                }
            }
        });
    }

    private void CadastrarUsuario(View v){
        String email = edit_email.getText().toString();
        String senha = edit_senha.getText().toString();

        FirebaseAuth.getInstance().createUserWithEmailAndPassword(email,senha).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){

                   SalvarDadosUsuario();

                   Snackbar snackbar = Snackbar.make(v,mensagens[1],Snackbar.LENGTH_SHORT);
                   snackbar.setActionTextColor(Color.WHITE);
                   snackbar.show();
               }else{
                   String erro;
                   try {
                       throw task.getException();

                   }catch (FirebaseAuthWeakPasswordException e) {
                       erro = "Digite uma senha com no mínimo 6 caracteres";

                   }catch (FirebaseAuthUserCollisionException e) {
                       erro = "Esse e-mail já está cadastrado";

                   }catch (FirebaseAuthInvalidCredentialsException e){
                       erro = "E-mail incorreto";

                   }catch (Exception e){
                       erro = "Erro ao cadastrar usuário";

                   }
                   Snackbar snackbar = Snackbar.make(v,erro,Snackbar.LENGTH_SHORT);
                   snackbar.setActionTextColor(Color.WHITE);
                   snackbar.show();

               }
            }
        });
    }

    private void SalvarDadosUsuario(){      //essa parte pode ser replicada para salvar os dados dos pets!!!
        String nome = edit_nome.getText().toString();

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> usuarios = new HashMap<>();

        usuarios.put("nome", nome);
        usuarioId = FirebaseAuth.getInstance().getCurrentUser().getUid();

        DocumentReference documentReference = db.collection("Usuarios").document(usuarioId);
        documentReference.set(usuarios).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) { //dados do usuario salvos com sucesso
                Log.d("db", "sucesso ao salvar os dados");
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) { //dados do usuario nao salvos
                Log.d("db_error","Erro ao salvar os dados" + e.toString());
            }
        });

    }

    private void IniciarComponentes(){

        edit_nome = findViewById(R.id.edit_nome);
        edit_email = findViewById(R.id.edit_email);
        edit_senha = findViewById(R.id.edit_senha);
        bt_cadastrar = findViewById(R.id.bt_cadastrar);
    }
}