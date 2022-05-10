package com.example.buscapet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.jaredrummler.materialspinner.MaterialSpinner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PetRegistration extends AppCompatActivity {

    private EditText labelCity,labelAddress,labelTags;
    private MaterialSpinner labelAnimal,labelState;
    private Button signInButton;
    private CheckBox checkHome;
    private String IDpet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_registration);

        getSupportActionBar().hide();
        IniciarComponentes();

        // ANIMAL TYPE DROPDOWN
        String[] animals = new String[]{"Tipo de Animal...", "Cachorro", "Gato", "Outro"};
        final MaterialSpinner animalSpinner = (MaterialSpinner) findViewById(R.id.labelAnimal);
        final List<String> animalsList = new ArrayList<>(Arrays.asList(animals));
        final ArrayAdapter<String> animalAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, animalsList);
        animalAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        animalSpinner.setAdapter(animalAdapter);

        // STATE DROPDOWN
        String[] states = new String[]{"Estado","AC","AL","AP","AM","BA","CE","DF","ES","GO","MA","MT","MS","MG","PA","PB","PR","PE","PI","RJ","RN","RS","RO","RR","SC","SP","SE","TO"};
        final MaterialSpinner stateSpinner = (MaterialSpinner) findViewById(R.id.labelState);
        final List<String> statesList = new ArrayList<>(Arrays.asList(states));
        final ArrayAdapter<String> stateAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, statesList);
        stateAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        stateSpinner.setAdapter(stateAdapter);

        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String city = labelCity.getText().toString();
                String address = labelAddress.getText().toString();
                String label = labelAnimal.getText().toString();
                String state = labelState.getText().toString();

                if(city.isEmpty() ){
                    Snackbar snackbar = Snackbar.make(v,"Favor preencher o campo de cidade",Snackbar.LENGTH_SHORT);
                    snackbar.setActionTextColor(Color.WHITE);
                    snackbar.show();
                }
                else{
                    if(address.isEmpty()) {
                        Snackbar snackbar = Snackbar.make(v, "Favor preencher o campo de endereço", Snackbar.LENGTH_SHORT);
                        snackbar.setActionTextColor(Color.WHITE);
                        snackbar.show();
                    }

                    else{
                        if(label=="Tipo de Animal...") {
                            Snackbar snackbar = Snackbar.make(v, "Favor selecionar o tipo de pet", Snackbar.LENGTH_SHORT);
                            snackbar.setActionTextColor(Color.WHITE);
                            snackbar.show();
                        }

                        else{
                            if(state=="Estado") {
                                Snackbar snackbar = Snackbar.make(v, "Favor selecionar o estado", Snackbar.LENGTH_SHORT);
                                snackbar.setActionTextColor(Color.WHITE);
                                snackbar.show();
                            }

                            else{
                                SalvarDadosPet();
                                Snackbar snackbar = Snackbar.make(v, "Pet cadastrado com sucesso!", Snackbar.LENGTH_SHORT);
                                snackbar.setActionTextColor(Color.WHITE);
                                snackbar.show();

                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        TelaPrincipal();
                                    }
                                },3000);
                            }
                        }
                    }
                }
            }
        });
    }

    private void IniciarComponentes(){

        labelAnimal = findViewById(R.id.labelAnimal);
        labelState = findViewById(R.id.labelState);
        labelCity = findViewById(R.id.labelCity);
        labelAddress = findViewById(R.id.labelAddress);
        labelTags = findViewById(R.id.labelTags);
        checkHome = findViewById(R.id.checkHome);
        signInButton = findViewById(R.id.signInButton);
    }


    private void SalvarDadosPet(){
        String label = labelAnimal.getText().toString();
        String state = labelState.getText().toString();
        String city = labelCity.getText().toString();
        String address = labelAddress.getText().toString();
        String tags = labelTags.getText().toString();
        String home;

        //Integer label2 = labelAnimal.getSelectedIndex();

        if(checkHome.isChecked()){
            home="sim";
        } else home="não";

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        Map<String, Object> pets = new HashMap<>();

        IDpet = FirebaseAuth.getInstance().getCurrentUser().getUid();
        pets.put("label", label);
        pets.put("state", state);
        pets.put("city", city);
        pets.put("address", address);
        pets.put("tags", tags);
        pets.put("home", home);


        DocumentReference documentReference = db.collection("Pets").document(IDpet.toString());
        documentReference.set(pets).addOnSuccessListener(new OnSuccessListener<Void>() {
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

    private void TelaPrincipal(){
        Intent intent = new Intent(PetRegistration.this,UserAccount.class);
        startActivity(intent);
        finish();
    }
}