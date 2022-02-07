package com.example.eazycombustivel.Helper;

import com.example.eazycombustivel.model.Despesa;
import com.example.eazycombustivel.model.Receita;

import java.util.List;

public interface IDespesa {

    public boolean salvar(Despesa despesa);

    public boolean atualizar(Despesa despesa);

    public boolean deletar(Despesa despesa);

    public List<Despesa> listar();

}
