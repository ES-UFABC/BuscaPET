package com.example.buscapet;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.jaredrummler.materialspinner.MaterialSpinner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PetRegistration extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pet_registration);

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
}