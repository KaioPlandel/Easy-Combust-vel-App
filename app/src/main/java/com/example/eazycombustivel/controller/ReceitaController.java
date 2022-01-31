package com.example.eazycombustivel.controller;

import android.content.ContentValues;
import android.content.Context;

import androidx.annotation.Nullable;

import com.example.eazycombustivel.datamodel.ReceitaDataModel;
import com.example.eazycombustivel.datasource.AppDataBase;
import com.example.eazycombustivel.model.Receita;

import java.util.List;

public class ReceitaController extends AppDataBase implements ICrud<Receita>{
    public ReceitaController(@Nullable Context context) {
        super(context);
    }

    @Override
    public boolean incluir(Receita obj) {

        ContentValues cv = new ContentValues();
        cv.put(ReceitaDataModel.VALOR,obj.getValor());
        cv.put(ReceitaDataModel.DATA,obj.getData());
        cv.put(ReceitaDataModel.CATEGORIA,obj.getCategoria());
        cv.put(ReceitaDataModel.OBSERVACAO,obj.getObservacao());

        return insert(ReceitaDataModel.NOME_TABLE,cv);
    }

    @Override
    public boolean deletar(Receita obj) {
        return false;
    }

    @Override
    public boolean alterar(int id) {
        return false;
    }

    @Override
    public List<Receita> listar() {
        return getReceitas(ReceitaDataModel.NOME_TABLE);
    }

    public int somar(){
        return addAllValues(ReceitaDataModel.NOME_TABLE);
    }
}
