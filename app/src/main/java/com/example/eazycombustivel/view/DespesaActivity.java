package com.example.eazycombustivel.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.eazycombustivel.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class DespesaActivity extends AppCompatActivity {

    private TextInputLayout textInputLayout;
    private AutoCompleteTextView autoCompleteTextView;
    private ArrayList<String> listaDespesa;
    private ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);

        textInputLayout = findViewById(R.id.textField1);
        autoCompleteTextView = findViewById(R.id.textField2);

        listaDespesa = new ArrayList<>();
        listaDespesa.add("Entregas Aplicativos");
        listaDespesa.add("Entregas Particular");
        listaDespesa.add("Corrida Aplicativos");
        listaDespesa.add("Corrida Particular");
        listaDespesa.add("Gorjeta");
        listaDespesa.add("Outros");

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),R.layout.lista_opcoes_receita,listaDespesa);
        autoCompleteTextView.setAdapter(arrayAdapter);

        autoCompleteTextView.setThreshold(1);
    }
}