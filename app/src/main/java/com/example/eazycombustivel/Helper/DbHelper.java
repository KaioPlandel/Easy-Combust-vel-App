package com.example.eazycombustivel.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    public static final String NOME_DB = "DataBaseControle";
    public static final String NOME_TABLE = "listaDeControle";


    public DbHelper(@Nullable Context context) {
        super(context, NOME_DB, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE IF NOT EXISTS " + NOME_TABLE +
                " (id INTEGER PRIMARY KEY AUTOINCREMENT," +
                " data TEXT NOT NULL," +
                "valor TEXT NOT NULL," +
                "categoria TEXT NOT NULL," +
                "observacao TEXT," +
                "tipo TEXT NOT NULL,); ";

        try {
            Log.i("TAG", "Sucesso ao criar a Tabela");
            db.execSQL(sql);

        }catch (Exception e){
            Log.i("TAG", "Erro ao criar a Tabela" + e.getMessage());
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "DROP TABLE IF EXISTS "+ NOME_TABLE + " ;";
        try {
            Log.i("TAG", "Sucesso ao Atualizar App");
            db.execSQL(sql);
            onCreate(db);

        }catch (Exception e){
            Log.i("TAG", "Erro ao Atualizar App" + e.getMessage());
        }

    }
}
