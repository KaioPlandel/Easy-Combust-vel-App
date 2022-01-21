package com.example.eazycombustivel.Helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.eazycombustivel.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class TicketDAO implements ITicket{

    private SQLiteDatabase escreve;
    private SQLiteDatabase le;

    public TicketDAO(Context context) {

    DbHelper db = new DbHelper(context);
    escreve = db.getWritableDatabase();
    le = db.getReadableDatabase();

    }

    @Override
    public boolean salvar(Ticket ticket) {

        try{
            ContentValues cv = new ContentValues();
            cv.put("valor",ticket.getValor());
            cv.put("data",ticket.getData());
            cv.put("categoria",ticket.getCategoria());
            cv.put("observacao",ticket.getObservacao());
            cv.put("tipo",ticket.getTipo());
            Log.i("TAG", "Salvo com sucesso");
            escreve.insert(DbHelper.NOME_TABLE,null,cv);
        }catch (Exception e){
            Log.i("TAG", "Erro ao salvar " + e.getMessage());
            return false;
        }


        return true;
    }

    @Override
    public boolean atualizar(Ticket ticket) {
        return false;
    }

    @Override
    public boolean deletar(Ticket ticket) {
        return false;
    }

    @Override
    public List<Ticket> listar() {

        List<Ticket> tickets = new ArrayList<>();

        String sql = "SELECT * FROM " + DbHelper.NOME_TABLE + " ;";
        Cursor cursor = le.rawQuery(sql,null);

        while (cursor.moveToNext()){

            Long id = cursor.getLong(cursor.getColumnIndex("id"));
            String valor = cursor.getString(cursor.getColumnIndex("valor"));
            String data = cursor.getString(cursor.getColumnIndex("data"));
            String categoria = cursor.getString(cursor.getColumnIndex("categoria"));
            String observacao = cursor.getString(cursor.getColumnIndex("observacao"));
            String tipo = cursor.getString(cursor.getColumnIndex("tipo"));


            Ticket ticket = new Ticket();
            ticket.setId(id);
            ticket.setValor(valor);
            ticket.setData(data);
            ticket.setCategoria(categoria);
            ticket.setObservacao(observacao);
            ticket.setTipo(tipo);




            tickets.add(ticket);

        }

        return tickets;
    }
}
