package com.example.eazycombustivel.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static String NOME_DB = "relatorio";
    public static int VERSION = 1;
    public static String NAME_TABLE = "receita";
    public static String NAME_TABLE2 = "despesa";

    public DBHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + NAME_TABLE
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " valor REAL NOT NULL, " +
                "categoria TEXT NOT NULL, " +
                "data TEXT NOT NULL, " +
                "observacao TEXT," +
                "mesAno TEXT );";

        String sql2 = "CREATE TABLE IF NOT EXISTS " + NAME_TABLE2
                + "( id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                " valor REAL NOT NULL, " +
                "categoria TEXT NOT NULL, " +
                "data TEXT NOT NULL, " +
                "observacao TEXT, " +
                "mesAno TEXT );";

        try {
            db.execSQL(sql);
            Log.i("TAG", "onCreateTABLE: SUCESSO ");

            db.execSQL(sql2);
            Log.i("TAG", "onCreateTABLE: SUCESSO ");

        }catch (Exception e){
            Log.i("TAG", "onCreateTABLE: ERRO ");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
