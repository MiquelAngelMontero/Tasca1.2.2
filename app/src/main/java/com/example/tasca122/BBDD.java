package com.example.tasca122;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class BBDD extends SQLiteOpenHelper {

    private Context context;
    private static final String NOM_BBDD = "Incidencies.db";//Nom de la bbdd
    private static final int VERSIO_BBDD = 1;


    private static final String NOM_TAULA = "incidencies";//Nom de la taula
    //Camps de la taula
    private static final String ID_COLUMNA = "_id";
    private static final String NOM_COLUMNA = "nom";
    private static final String ELEMENT_COLUMNA = "element";
    private static final String TIPUS_COLUMNA = "tipus";
    private static final String UBICACIO_COLUMNA = "ubicacio";
    private static final String DESCRIPCIO_COLUMNA = "descripcio";
    private static final String DATA_COLUMNA = "data";


    //Constructor
    public BBDD(@Nullable Context context) {
        super(context, NOM_BBDD, null, VERSIO_BBDD);
        this.context = context;
    }

    //Metodes requerits per SQLiteOpenHelper
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Ordre SQL per a crear la taula
        String query = "CREATE TABLE " + NOM_TAULA +
                        " (" + ID_COLUMNA + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        NOM_COLUMNA + " TEXT, " +
                        ELEMENT_COLUMNA + " TEXT, " +
                        TIPUS_COLUMNA + " TEXT, " +
                        UBICACIO_COLUMNA + " TEXT, " +
                        DESCRIPCIO_COLUMNA + " TEXT, " +
                        DATA_COLUMNA + " TEXT);";
        db.execSQL(query);//Execucio de la ordre
    }

    //Metode que s'acciona al fer una actualització a la bbdd
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + NOM_TAULA);//Primer elimina la taula
        onCreate(db);//I despres es torna a crar
    }

    void afegirIncidencia(String nom, String element, String tipus, String ubicacio, String descripcio, String data) {
        SQLiteDatabase db = this.getWritableDatabase();//obtenim la nosta BBDD i la afegim a la variable db
        ContentValues cv = new ContentValues();//valors de la app

        //Afegim al cv cada element que li hem passat per paràmetre
        cv.put(NOM_COLUMNA, nom);
        cv.put(ELEMENT_COLUMNA, element);
        cv.put(TIPUS_COLUMNA, tipus);
        cv.put(UBICACIO_COLUMNA, ubicacio);
        cv.put(DESCRIPCIO_COLUMNA, descripcio);
        cv.put(DATA_COLUMNA, data);

        //Afegim el cv a la bbdd
        long result = db.insert(NOM_TAULA, null, cv);

        //Enviem un missatge depenent de si les dades s'han introduiït correctament
        if(result == -1) {
            Toast.makeText(context, "La introducció de dades ha fallat", Toast.LENGTH_SHORT);
        } else {
            Toast.makeText(context, "Les dades s'han introduït correctament", Toast.LENGTH_SHORT);
        }
    }

    Cursor llegirDb() {
        String query = "SELECT * FROM " + NOM_TAULA; //Fem la consulta
        SQLiteDatabase db = this.getReadableDatabase(); //Obtenim la nosta base de dades en una variable

        Cursor cursor = null; //Creem el cursor que guardarà els resultats de la nostra consulta
        if(db != null) {
            cursor  = db.rawQuery(query, null); //Si la nostra bbdd no està buida omplim el cursor amb els resultats
        }
        return cursor; //Retornem els resultats
    }
}
