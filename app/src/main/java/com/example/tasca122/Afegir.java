package com.example.tasca122;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Afegir extends AppCompatActivity {

    EditText nom_input, element_input, ubicacio_input, descripcio_input, data_input;
    Spinner tipus_input;
    Button afegir_input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_afegir);

        nom_input = findViewById(R.id.nom_input);
        element_input = findViewById(R.id.element_input);
        ubicacio_input = findViewById(R.id.ubicacio_input);
        descripcio_input = findViewById(R.id.descripcio_input);
        data_input = findViewById(R.id.data_input);
        tipus_input = findViewById(R.id.tipus_input);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel("Mi notificacion", "Incidencia", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        afegir_input = findViewById(R.id.afegir_input);
        afegir_input.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BBDD bd = new BBDD(Afegir.this);
                bd.afegirIncidencia(
                        nom_input.getText().toString().trim(),
                        element_input.getText().toString().trim(),
                        tipus_input.getSelectedItem().toString().trim(),
                        ubicacio_input.getText().toString().trim(),
                        descripcio_input.getText().toString().trim(),
                        data_input.getText().toString().trim()
                        );
                NotificationCompat.Builder builder = new NotificationCompat.Builder(Afegir.this, "Mi notificacion");
                builder.setContentTitle("Incidència creada per l'usuari " + nom_input.getText().toString().trim());
                builder.setContentText(descripcio_input.getText().toString().trim());
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(Afegir.this);
                managerCompat.notify(1,builder.build());
            }
        });
    }
}