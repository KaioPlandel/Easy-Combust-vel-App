package com.plandel.easyCombustivel.Helper;

import com.plandel.easyCombustivel.model.Despesa;

import java.util.List;

public interface IDespesa {

    public boolean salvar(Despesa despesa);

    public boolean atualizar(Despesa despesa);

    public boolean deletar(Despesa despesa);

    public List<Despesa> listar();

}
