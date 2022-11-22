package com.example.tasca122;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Llista extends RecyclerView.Adapter<Llista.Contenidor> {

    private Context context;
    private ArrayList id_incidencia, nom_incidencia, element_incidencia, tipus_incidencia, //Declarem les arrays que contindran els continguts de la nostra taula
            ubicacio_incidencia, descripcio_incidencia, data_incidencia;

    //Creem un constructor el qual obtingui tots els valors de la bbdd
    Llista(Context context,
           ArrayList id_incidencia,
           ArrayList nom_incidencia,
           ArrayList element_incidencia,
           ArrayList tipus_incidencia,
           ArrayList ubicacio_incidencia,
           ArrayList descripcio_incidencia,
           ArrayList data_incidencia) {
        this.context = context;
        this.id_incidencia = id_incidencia;
        this.nom_incidencia = nom_incidencia;
        this.element_incidencia = element_incidencia;
        this.tipus_incidencia = tipus_incidencia;
        this.ubicacio_incidencia = ubicacio_incidencia;
        this.descripcio_incidencia = descripcio_incidencia;
        this.data_incidencia = data_incidencia;
    }

    @NonNull
    @Override //Creem la llista amb les dades
    public Contenidor onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inlater = LayoutInflater.from(context);
        View view = inlater.inflate(R.layout.fila_llista, parent, false);
        return new Contenidor(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Contenidor holder, final int position) {
        //Obtenim la possició de la array de cada valor i afegim els valors de la bbdd a cada objecte
        holder.texte_id_inciencia.setText(String.valueOf(id_incidencia.get(position)));
        holder.texte_nom_incidencia.setText(String.valueOf(nom_incidencia.get(position)));
        holder.texte_element_incidencia.setText(String.valueOf(element_incidencia.get(position)));
        holder.texte_tipus_incidencia.setText(String.valueOf(tipus_incidencia.get(position)));
        holder.texte_ubicacio_incidencia.setText(String.valueOf(ubicacio_incidencia.get(position)));
        holder.texte_descripcio_incidencia.setText(String.valueOf(descripcio_incidencia.get(position)));
        holder.texte_data_incidencia.setText(String.valueOf(data_incidencia.get(position)));
    }

    @Override
    public int getItemCount() {
        return id_incidencia.size();
    }

    public class Contenidor extends RecyclerView.ViewHolder {
        //Declarem els elements que tenim a la llista
        TextView texte_id_inciencia, texte_nom_incidencia, texte_element_incidencia,
                texte_tipus_incidencia, texte_ubicacio_incidencia, texte_descripcio_incidencia, texte_data_incidencia;

        public Contenidor(@NonNull View itemView) {
            super(itemView);
            texte_id_inciencia = itemView.findViewById(R.id.texte_id_inciencia);
            texte_nom_incidencia = itemView.findViewById(R.id.texte_nom_incidencia);
            texte_element_incidencia = itemView.findViewById(R.id.texte_element_incidencia);
            texte_tipus_incidencia = itemView.findViewById(R.id.texte_tipus_incidencia);
            texte_ubicacio_incidencia = itemView.findViewById(R.id.texte_ubicacio_incidencia);
            texte_descripcio_incidencia = itemView.findViewById(R.id.texte_descripcio_incidencia);
            texte_data_incidencia = itemView.findViewById(R.id.texte_data_incidencia);
        }
    }
}
