package com.example.buscapet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class TelaAnimais extends Activity {

    private ImageView userButton;
    private ImageView petButton;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private TextView animal_nome11, animal_estado11, animal_cidade11, animal_endereco11, animal_descricao11, animal_acolhido11;
    private TextView animal_nome22, animal_estado22, animal_cidade22, animal_endereco22, animal_descricao22, animal_acolhido22;
    private TextView animal_nome33, animal_estado33, animal_cidade33, animal_endereco33, animal_descricao33, animal_acolhido33;
    private TextView animal_nome44, animal_estado44, animal_cidade44, animal_endereco44, animal_descricao44, animal_acolhido44;
    private ImageView animal_image11,animal_image22,animal_image33,animal_image44;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tela_animais);
        Initiate();

        userButton.setOnClickListener(v -> {
            Intent intent = new Intent(TelaAnimais.this, UserAccount.class);
            startActivity(intent);
        });

        petButton.setOnClickListener(v -> {
            Intent intent = new Intent(TelaAnimais.this, PetRegistration.class);
            startActivity(intent);
        });

        //Iniciar Componentes
        animal_nome11= (TextView)findViewById(R.id.Animal_nome1);
        animal_estado11 = (TextView)findViewById(R.id.Animal_estado1);
        animal_cidade11 = (TextView)findViewById(R.id.Animal_cidade1);
        animal_endereco11 = (TextView)findViewById(R.id.Animal_endereco1);
        animal_descricao11 = (TextView)findViewById(R.id.Animal_descricao1);
        animal_acolhido11 = (TextView)findViewById(R.id.Animal_acolhido1);

        animal_nome22 = findViewById(R.id.Animal_nome2);
        animal_estado22 = findViewById(R.id.Animal_estado2);
        animal_cidade22 = findViewById(R.id.Animal_cidade2);
        animal_endereco22 = findViewById(R.id.Animal_endereco2);
        animal_descricao22 = findViewById(R.id.Animal_descricao2);
        animal_acolhido22 = findViewById(R.id.Animal_acolhido2);

        animal_nome33 = findViewById(R.id.Animal_nome3);
        animal_estado33 = findViewById(R.id.Animal_estado3);
        animal_cidade33 = findViewById(R.id.Animal_cidade3);
        animal_endereco33 = findViewById(R.id.Animal_endereco3);
        animal_descricao33 = findViewById(R.id.Animal_descricao3);
        animal_acolhido33 = findViewById(R.id.Animal_acolhido3);

        animal_nome44 = findViewById(R.id.Animal_nome4);
        animal_estado44 = findViewById(R.id.Animal_estado4);
        animal_cidade44 = findViewById(R.id.Animal_cidade4);
        animal_endereco44 = findViewById(R.id.Animal_endereco4);
        animal_descricao44= findViewById(R.id.Animal_descricao4);
        animal_acolhido44 = findViewById(R.id.Animal_acolhido4);

        animal_image11 = findViewById(R.id.animal_image1);
        animal_image22 = findViewById(R.id.animal_image2);
        animal_image33 = findViewById(R.id.animal_image3);
        animal_image44 = findViewById(R.id.animal_image4);

        //pegar base de dados

        DocumentReference documentReference = db.collection("Pets").document("5mBkSoSC9LPAVNDFQ8rXjt09tSz2");
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot != null){
                    animal_nome11.setText(documentSnapshot.getString("label"));
                    animal_estado11.setText(documentSnapshot.getString("state"));
                    animal_cidade11.setText(documentSnapshot.getString("city"));
                     animal_endereco11.setText(documentSnapshot.getString("address"));
                     animal_descricao11.setText(documentSnapshot.getString("tags"));
                     animal_acolhido11.setText(documentSnapshot.getString("home"));
                }
            }
        });

        DocumentReference documentReference2 = db.collection("Pets").document("AV0T6zc6GgeCjPotghxIiU0OW792");
        documentReference2.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot != null){
                    animal_nome22.setText(documentSnapshot.getString("label"));
                    animal_estado22.setText(documentSnapshot.getString("state"));
                    animal_cidade22.setText(documentSnapshot.getString("city"));
                    animal_endereco22.setText(documentSnapshot.getString("address"));
                    animal_descricao22.setText(documentSnapshot.getString("tags"));
                    animal_acolhido22.setText(documentSnapshot.getString("home"));
                }
            }
        });

        DocumentReference documentReference3 = db.collection("Pets").document("O8XL9Um4Z7TeZCrPvv7svRfmk1F3");
        documentReference3.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot != null){
                    animal_nome33.setText(documentSnapshot.getString("label"));
                    animal_estado33.setText(documentSnapshot.getString("state"));
                    animal_cidade33.setText(documentSnapshot.getString("city"));
                    animal_endereco33.setText(documentSnapshot.getString("address"));
                    animal_descricao33.setText(documentSnapshot.getString("tags"));
                    animal_acolhido33.setText(documentSnapshot.getString("home"));
                }
            }
        });

        DocumentReference documentReference4 = db.collection("Pets").document("kiMSgoYxAVZ6dhTDt5zeV3Lyg552");
        documentReference4.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot != null){
                    animal_nome44.setText(documentSnapshot.getString("label"));
                    animal_estado44.setText(documentSnapshot.getString("state"));
                    animal_cidade44.setText(documentSnapshot.getString("city"));
                    animal_endereco44.setText(documentSnapshot.getString("address"));
                    animal_descricao44.setText(documentSnapshot.getString("tags"));
                    animal_acolhido44.setText(documentSnapshot.getString("home"));
                }
            }
        });




    }

    protected void OnStart() {
        super.onStart();

        //TextView animal= (TextView)findViewById(R.id.Animal_nome1);
        //animal_estado11 = findViewById(R.id.Animal_estado1);
        //animal_cidade11 = findViewById(R.id.Animal_cidade1);
        //animal_endereco11 = findViewById(R.id.Animal_endereco1);
        //animal_descricao11 = findViewById(R.id.Animal_descricao1);
        //animal_acolhido11 = findViewById(R.id.Animal_acolhido1);



        /*usuarioID= FirebaseAuth.getInstance().getCurrentUser().getUid();
        //DocumentReference documentReference = db.collection("Pets").document("5mBkSoSC9LPAVNDFQ8rXjt09tSz2");
        DocumentReference documentReference = db.collection("Pets").document("1");
        documentReference.addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot documentSnapshot, @Nullable FirebaseFirestoreException error) {
                if(documentSnapshot != null){
                    animal_nome11.setText(documentSnapshot.getString("label"));
                }

               // animal_estado11.setText(documentSnapshot.getString("state"));
                //animal_cidade11.setText(documentSnapshot.getString("city"));
               // animal_endereco11.setText(documentSnapshot.getString("address"));
               // animal_descricao11.setText(documentSnapshot.getString("home"));
               // animal_acolhido11.setText(documentSnapshot.getString("tags"));
            }
        });*/
    }

    private void Initiate() {
        userButton = findViewById(R.id.userButton);
        petButton = findViewById(R.id.petButton);
    }


    private void IniciarComponentes() {
        //animal_nome11 = findViewById(R.id.Animal_nome1);
        //animal_estado11 = findViewById(R.id.Animal_estado1);
        //animal_cidade11 = findViewById(R.id.Animal_cidade1);
        //animal_endereco11 = findViewById(R.id.Animal_endereco1);
        //animal_descricao11 = findViewById(R.id.Animal_descricao1);
        //animal_acolhido11 = findViewById(R.id.Animal_acolhido1);
        /*Animal_nome2 = findViewById(R.id.Animal_nome2);
        Animal_estado2 = findViewById(R.id.Animal_estado2);
        Animal_cidade2 = findViewById(R.id.Animal_cidade2);
        Animal_endereco2 = findViewById(R.id.Animal_endereco2);
        Animal_descricao2 = findViewById(R.id.Animal_descricao2);
        Animal_acolhido2 = findViewById(R.id.Animal_acolhido2);
        Animal_nome3 = findViewById(R.id.Animal_nome3);
        Animal_estado3 = findViewById(R.id.Animal_estado3);
        Animal_cidade3 = findViewById(R.id.Animal_cidade3);
        Animal_endereco3 = findViewById(R.id.Animal_endereco3);
        Animal_descricao3 = findViewById(R.id.Animal_descricao3);
        Animal_acolhido3 = findViewById(R.id.Animal_acolhido3);
        Animal_nome4 = findViewById(R.id.Animal_nome4);
        Animal_estado4 = findViewById(R.id.Animal_estado4);
        Animal_cidade4 = findViewById(R.id.Animal_cidade4);
        Animal_endereco4 = findViewById(R.id.Animal_endereco4);
        Animal_descricao4 = findViewById(R.id.Animal_descricao4);
        Animal_acolhido4 = findViewById(R.id.Animal_acolhido4);*/
    }
};
