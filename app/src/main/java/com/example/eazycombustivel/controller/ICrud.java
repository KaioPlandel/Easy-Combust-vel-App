package com.example.eazycombustivel.controller;

import java.util.List;

public interface ICrud<T> {

    public boolean incluir(T obj);

    public boolean deletar(T obj);

    public boolean alterar(int id);

    public List<T> listar();
}
