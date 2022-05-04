package com.example.buscapet;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.jaredrummler.materialspinner.MaterialSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class PetRegistration extends AppCompatActivity {
    private ImageView profilePic;
    public Uri imageURI;
    private FirebaseStorage storage;
    private StorageReference storageReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_registration);

        storage = FirebaseStorage.getInstance();
        storageReference = storage.getReference();
        profilePic = findViewById(R.id.labelPicture);
        profilePic.setOnClickListener(view -> choosePicture());

        getSupportActionBar().hide();

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

        final String key = UUID.randomUUID().toString();
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
                    prss.setMessage("Loading: " + (int) progressPercent + "%");
                });
    }
}