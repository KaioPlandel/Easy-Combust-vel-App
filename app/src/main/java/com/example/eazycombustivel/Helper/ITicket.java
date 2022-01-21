package com.example.eazycombustivel.Helper;

import com.example.eazycombustivel.model.Ticket;

import java.util.List;

public interface ITicket {

    public boolean salvar(Ticket ticket);
    public boolean atualizar(Ticket ticket);
    public boolean deletar(Ticket ticket);
    public List<Ticket> listar();
}
