package com.example.eazycombustivel.datasource;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.telephony.AccessNetworkConstants;
import android.util.Log;

import androidx.annotation.Nullable;

import com.example.eazycombustivel.datamodel.ReceitaDataModel;
import com.example.eazycombustivel.model.Receita;

import java.util.ArrayList;
import java.util.List;

public class AppDataBase extends SQLiteOpenHelper {

    public static final String NOME_BD = "transacoes";
    public static final int VERSION = 1;

    public SQLiteDatabase db;


    public AppDataBase(@Nullable Context context) {
        super(context, NOME_BD, null, VERSION);

        db = getWritableDatabase();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        try {
            db.execSQL(ReceitaDataModel.criarTabela());
            Log.i("TAG", "Tabela Criada com sucesso");
        }catch (Exception e){
            Log.i("TAG", "Erro ao criar tabela "+ e.getMessage());

        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean insert(String tabela, ContentValues cv){

        db = getWritableDatabase();
        boolean retorno = false;

        try {
            retorno = db.insert(tabela,null,cv) > 0;
        }catch (Exception e){
            Log.i("TAG", "Erro insert: " + e.getMessage());
        }
        return  retorno;
    }

    public List<Receita> getReceitas(String tabela){
        db = getWritableDatabase();

        List<Receita> listaGanhos = new ArrayList<>();
        String sql = "SELECT * FROM " + tabela + " ORDER BY id DESC";

        Cursor cursor;
        cursor = db.rawQuery(sql,null);

        if(cursor.moveToFirst()){

            do{
                Receita ganho = new Receita();
                ganho.setId(cursor.getInt(cursor.getColumnIndex(ReceitaDataModel.ID)));
                ganho.setValor(cursor.getInt(cursor.getColumnIndex(ReceitaDataModel.VALOR)));
                ganho.setData(cursor.getString(cursor.getColumnIndex(ReceitaDataModel.DATA)));
                ganho.setCategoria(cursor.getString(cursor.getColumnIndex(ReceitaDataModel.CATEGORIA)));
                ganho.setObservacao(cursor.getString(cursor.getColumnIndex(ReceitaDataModel.OBSERVACAO)));

                listaGanhos.add(ganho);



            }while (cursor.moveToNext());


        }
        return listaGanhos;
    }



    public int addAllValues(String tabela){
        int total = 0;
        Cursor c = db.rawQuery("SELECT SUM(" + ReceitaDataModel.VALOR+ ") FROM " + tabela,null);

        if(c.moveToFirst()){
            total = c.getInt(0);
        }
        return total;
    }


}
