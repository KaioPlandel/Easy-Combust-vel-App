package com.example.eazycombustivel.datamodel;

public class ReceitaDataModel {

    //1 Atributo nome da tabela
    public static String NOME_TABLE = "receita";

    //2 Atributo nome dos campos
    public static final String ID = "id";
    public static final String VALOR = "valor";
    public static final String CATEGORIA = "categoria";
    public static final String DATA = "data";
    public static final String OBSERVACAO = "observacao";

    //query para criar o banco de dados
    public static String query = "";

    //metodo para criar o script
    public static  String criarTabela(){
        query += "CREATE TABLE IF NOT EXISTS "+NOME_TABLE+" (";
        query += ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, ";
        query += VALOR+ " INTEGER, ";
        query += CATEGORIA+ " TEXT, ";
        query += DATA+ " TEXT, ";
        query += OBSERVACAO+ " TEXT )";

        return query;
    }
}
