package com.example.tasca122;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //Declarem els botons i la llista de la vista
    RecyclerView llista;
    FloatingActionButton afegir;

    BBDD bd; //Inicialitzem la classe BBDD
    ArrayList<String> id_incidencia, nom_incidencia, element_incidencia, tipus_incidencia, //Declarem les arrays que contindran els continguts de la nostra taula
    ubicacio_incidencia, descripcio_incidencia, data_incidencia;
    Llista llistat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Asignem la vista a la variable
        llista = findViewById(R.id.llista);
        afegir = findViewById(R.id.afegir);
        afegir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Afegir.class);
                startActivity(intent);
            }
        });

        //Inicialitzem tant la BBDD com les diferents arrays
        bd = new BBDD(MainActivity.this);
        id_incidencia = new ArrayList<>();
        nom_incidencia = new ArrayList<>();
        element_incidencia = new ArrayList<>();
        tipus_incidencia = new ArrayList<>();
        ubicacio_incidencia = new ArrayList<>();
        descripcio_incidencia = new ArrayList<>();
        data_incidencia = new ArrayList<>();

        guardarEnArray();

        llistat = new Llista(MainActivity.this, id_incidencia, nom_incidencia, element_incidencia, tipus_incidencia, ubicacio_incidencia, descripcio_incidencia, data_incidencia);
        llista.setAdapter(llistat);
        llista.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void guardarEnArray() {
        Cursor cursor = bd.llegirDb(); //Omplim el nostre cursor amb les dades de la nostra base de dades
        if(cursor.getCount() == 0) { //Fem que imrimeixi un missatge si la bbdd no te valors
            Toast.makeText(this, "No hi han dades", Toast.LENGTH_SHORT);
        } else {
            while(cursor.moveToNext()) { //Fem un while que continui mentres segueixi tenin valors
                //Afegim els valors a les nostres arrays
                id_incidencia.add(cursor.getString(0));
                nom_incidencia.add(cursor.getString(1));
                element_incidencia.add(cursor.getString(2));
                tipus_incidencia.add(cursor.getString(3));
                ubicacio_incidencia.add(cursor.getString(4));
                descripcio_incidencia.add(cursor.getString(5));
                data_incidencia.add(cursor.getString(6));
            }
        }
    }
}