package com.example.eazycombustivel.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.eazycombustivel.model.Receita;

import java.util.ArrayList;
import java.util.List;

public class ReceitaDAO implements IReceita {

    private SQLiteDatabase write;
    private SQLiteDatabase read;

    public ReceitaDAO(Context context) {
        DBHelper db = new DBHelper(context);
        write = db.getWritableDatabase();
        read = db.getReadableDatabase();
    }

    @Override
    public boolean salvar(Receita receita) {

        ContentValues cv = new ContentValues();
        cv.put("valor", receita.getValor());
        cv.put("categoria", receita.getCategoria());
        cv.put("data", receita.getData());
        cv.put("mesAno", receita.getMesAno());
        cv.put("observacao", receita.getObservacao());

        try {
            write.insert(DBHelper.NAME_TABLE, null, cv);
            Log.i("TAG", "salvar: Sucesso");
        } catch (Exception e) {
            Log.i("TAG", "salvar: Erro " + e.getMessage());
            return false;

        }

        return true;
    }


    @Override
    public boolean atualizar(Receita receita) {

        ContentValues cv = new ContentValues();
        cv.put("valor", receita.getValor());
        cv.put("categoria", receita.getCategoria());
        cv.put("data", receita.getData());
        cv.put("observacao", receita.getObservacao());

        try {
            String[] args = {String.valueOf(receita.getId())};
            write.update(DBHelper.NAME_TABLE, cv, "id=?", args);
            Log.i("TAG", "atualizar: Sucesso");
        } catch (Exception e) {
            Log.i("TAG", "atualizar: Erro" + e.getMessage());
            return false;

        }


        return true;
    }

    @Override
    public boolean deletar(Receita receita) {

        try {
            String[] args = {String.valueOf(receita.getId())};
            write.delete(DBHelper.NAME_TABLE, "id=?", args);
        } catch (Exception e) {
            Log.i("TAG", "deletar: Erro" + e.getMessage());
            return false;
        }
        return true;
    }

    @Override
    public List<Receita> listar() {

        List<Receita> listaReceita = new ArrayList<>();

        String sql = "SELECT * FROM " + DBHelper.NAME_TABLE + " ORDER BY id DESC;";
        Cursor cursor = read.rawQuery(sql, null);

        while (cursor.moveToNext()) {

            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            double valor = cursor.getDouble(cursor.getColumnIndex("valor"));
            String categoria = cursor.getString(cursor.getColumnIndex("categoria"));
            String data = cursor.getString(cursor.getColumnIndex("data"));
            String observacao = cursor.getString(cursor.getColumnIndex("observacao"));

            Receita receita = new Receita();
            receita.setId(Integer.parseInt(String.valueOf(id)));
            receita.setValor(valor);
            receita.setCategoria(categoria);
            receita.setData(data);
            receita.setObservacao(observacao);

            listaReceita.add(receita);
        }


        return listaReceita;
    }

    public double somarTotal(String data) {

        double total = 0.0;
        String sql = "SELECT SUM(valor) FROM " + DBHelper.NAME_TABLE + " WHERE mesAno =" + "'" + data + "'" + ";";
        Cursor cursor = read.rawQuery(sql, null);

        if (cursor.moveToFirst()) {
            total = cursor.getDouble(0);
        }

        return total;
    }
}
