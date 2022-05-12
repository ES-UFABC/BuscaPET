package com.example.buscapet;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PetRegistration extends AppCompatActivity {
    private Button signInButton;
    private CheckBox checkHome;
    private EditText labelCity,labelAddress,labelTags;
    private FirebaseStorage storage;
    private ImageView profilePic;
    private MaterialSpinner labelAnimal,labelState;
    private StorageReference storageReference;
    private String IDpet;
    public Uri imageURI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_registration);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        profilePic = findViewById(R.id.labelPicture);
        profilePic.setOnClickListener(view -> choosePicture());

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
        Intent intent = new Intent(PetRegistration.this, TelaAnimais.class);
        startActivity(intent);
        finish();
    }

    private void choosePicture() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK && data!=null && data.getData()!=null){
            imageURI = data.getData();
            profilePic.setImageURI(imageURI);
            uploadPicture();
        }
    }

    private void uploadPicture() {

        final ProgressDialog prss = new ProgressDialog(this);
        prss.setTitle("Enviando...");
        prss.show();

        final String key = FirebaseAuth.getInstance().getCurrentUser().getUid();
        //final String key = UUID.randomUUID().toString();
        StorageReference imageRef = storageReference.child("images/" + key);
        imageRef.putFile(imageURI)
                .addOnSuccessListener(snapshot -> {
                    prss.dismiss();
                    Snackbar.make(findViewById(android.R.id.content), "Imagem enviada!", Snackbar.LENGTH_LONG).show();
                })
                .addOnFailureListener(e -> {
                    prss.dismiss();
                    Toast.makeText(getApplicationContext(), "Falha ao enviar.", Toast.LENGTH_LONG).show();
                })
                .addOnProgressListener(snapshot -> {
                    double progressPercent = (100.00 * snapshot.getBytesTransferred() / snapshot.getTotalByteCount());
                    prss.setMessage("Carregando: " + (int) progressPercent + "%");
                });
    }
}