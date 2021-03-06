package com.plandel.easyCombustivel.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.plandel.easyCombustivel.model.Despesa;

import java.util.ArrayList;
import java.util.List;

public class DespesaDAO implements IDespesa{

    SQLiteDatabase write;
    SQLiteDatabase read;

    public DespesaDAO(Context context) {
        DBHelper db = new DBHelper(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();

    }

    @Override
    public boolean salvar(Despesa despesa) {

        ContentValues cv = new ContentValues();
        cv.put("valor", despesa.getValor());
        cv.put("categoria", despesa.getCategoria());
        cv.put("data", despesa.getData());
        cv.put("mesAno",despesa.getMesAno());
        cv.put("observacao", despesa.getObservacao());


        try {
            write.insert(DBHelper.NAME_TABLE2,null,cv);
            Log.i("TAG", "salvar: Sucesso");

        }catch (Exception e){
            Log.i("TAG", "salvar: Erro " + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public boolean atualizar(Despesa despesa) {

        ContentValues cv = new ContentValues();
        cv.put("valor",despesa.getValor());
        cv.put("categoria",despesa.getCategoria());
        cv.put("data",despesa.getData());
        cv.put("mesAno",despesa.getMesAno());
        cv.put("observacao",despesa.getObservacao());

        try {
            String[] args = {String.valueOf(despesa.getId())};
            write.update(DBHelper.NAME_TABLE2,cv,"id=?",args);
            Log.i("TAG", "atualizar: Sucesso");
        }catch (Exception e){
            Log.i("TAG", "atualizar: Erro" + e.getMessage());
            return false;
        }



        return true;
    }

    @Override
    public boolean deletar(Despesa despesa) {

        try {
            String[] args = {String.valueOf(despesa.getId())};
            write.delete(DBHelper.NAME_TABLE2,"id=?",args);
        }catch (Exception e){
            Log.i("TAG", "deletar:Erro "+ e.getMessage());
            return false;
        }

        return true;
    }

    @Override
    public List<Despesa> listar() {

        List<Despesa> listaDespesa = new ArrayList<>();
        String sql = "SELECT * FROM " + DBHelper.NAME_TABLE2 + " ORDER BY id DESC;";
        Cursor cursor = read.rawQuery(sql,null);

        while (cursor.moveToNext()){

            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            double valor = cursor.getDouble(cursor.getColumnIndex("valor"));
            String categoria = cursor.getString(cursor.getColumnIndex("categoria"));
            String data = cursor.getString(cursor.getColumnIndex("data"));
            String observacao = cursor.getString(cursor.getColumnIndex("observacao"));

            Despesa despesa = new Despesa();
            despesa.setId(Integer.parseInt(String.valueOf(id)));
            despesa.setValor(valor);
            despesa.setCategoria(categoria);
            despesa.setData(data);
            despesa.setObservacao(observacao);

            listaDespesa.add(despesa);
        }


        return listaDespesa;
    }

    public double somarTotal(String data){

        double total = 0.0;
        String sql = "SELECT SUM(valor) FROM " + DBHelper.NAME_TABLE2 + " WHERE mesAno =" +"'"+ data + "'"+ ";";

        Cursor cursor = read.rawQuery(sql,null);

        try {
            if(cursor.moveToFirst()){
                total = cursor.getDouble(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return total;
    }

    public double somarTotalCategoria(String data,String categoria){

        double total = 0.0;
        String sql = "SELECT SUM(valor) FROM " + DBHelper.NAME_TABLE2 + " WHERE mesAno =" +"'"+ data + "'"+ " AND categoria = "+ "'" + categoria + "'"  + ";";

        Cursor cursor = read.rawQuery(sql,null);

        try {
            if(cursor.moveToFirst()){
                total = cursor.getDouble(0);
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return total;

    }
}
