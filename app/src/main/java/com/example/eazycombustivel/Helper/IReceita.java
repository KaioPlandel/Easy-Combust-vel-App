package com.example.eazycombustivel.Helper;

import com.example.eazycombustivel.model.Receita;

import java.util.List;

public interface IReceita {

    public boolean salvar(Receita receita);

    public boolean atualizar(Receita receita);

    public boolean deletar(Receita receita);

    public List<Receita> listar();
}
