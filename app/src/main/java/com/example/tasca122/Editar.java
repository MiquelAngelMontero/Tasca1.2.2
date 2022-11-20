package com.example.tasca122;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class Editar extends AppCompatActivity {

    EditText nom_input, element_input, ubicacio_input, descripcio_input, data_input;
    Button actualitzar_input;

    String id, nom, element, ubicacio, descripcio, data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar);

        nom_input = findViewById(R.id.nom_input2);
        element_input = findViewById(R.id.element_input2);
        ubicacio_input = findViewById(R.id.ubicacio_input2);
        descripcio_input = findViewById(R.id.descripcio_input2);
        data_input = findViewById(R.id.data_input2);

        actualitzar_input = findViewById(R.id.actualitzar_input);
        actualitzar_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        obtindreImprimirValors();
    }

    void obtindreImprimirValors() {
        if(getIntent().hasExtra("id") && getIntent().hasExtra("nom") && getIntent().hasExtra("element") && getIntent().hasExtra("tipus") && getIntent().hasExtra("ubicacio") && getIntent().hasExtra("descripcio") && getIntent().hasExtra("data")) {

            //Obtinde la info que li passem a traves de l'intent
            id = getIntent().getStringExtra("id");
            nom = getIntent().getStringExtra("nom");
            element = getIntent().getStringExtra("element");
            ubicacio = getIntent().getStringExtra("ubicacio");
            descripcio = getIntent().getStringExtra("descripcio");
            data = getIntent().getStringExtra("data");

            //Afegir la info a els edit texts
            nom_input.setText(nom);
            element_input.setText(element);
            ubicacio_input.setText(ubicacio);
            descripcio_input.setText(descripcio);
            data_input.setText(data);

        } else {
            Toast.makeText(this, "No hi ha informació d'aquest element", Toast.LENGTH_SHORT).show();
        }
    }
}