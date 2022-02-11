package com.plandel.easyCombustivel.Helper;

import com.plandel.easyCombustivel.model.Receita;

import java.util.List;

public interface IReceita {

    public boolean salvar(Receita receita);

    public boolean atualizar(Receita receita);

    public boolean deletar(Receita receita);

    public List<Receita> listar();
}
