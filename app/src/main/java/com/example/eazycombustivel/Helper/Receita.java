package com.example.eazycombustivel.Helper;

import java.io.Serializable;

public class Receita implements Serializable {
    private String valor;
    private String data;
    private String tipo;
    private String observacao;

    public Receita() {
    }

    public Receita(String valor, String data, String tipo, String observacao) {
        this.valor = valor;
        this.data = data;
        this.tipo = tipo;
        this.observacao = observacao;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
}
