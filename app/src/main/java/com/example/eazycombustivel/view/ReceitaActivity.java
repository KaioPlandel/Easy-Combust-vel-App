package com.example.eazycombustivel.view;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.example.eazycombustivel.Helper.DateCustom;
import com.example.eazycombustivel.R;
import com.example.eazycombustivel.controller.ReceitaController;
import com.example.eazycombustivel.model.Receita;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Locale;

public class ReceitaActivity extends AppCompatActivity {
    private TextInputLayout texto1;
    private AutoCompleteTextView texto2;
    private ArrayList<String> listaReceitaOpcao;
    private ArrayAdapter<String> arrayAdapter;
    private EditText editData,observacao;
    private CurrencyEditText textValor;
    private FloatingActionButton floating_action_button;
    ReceitaController receitaController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);

        receitaController = new ReceitaController(getApplicationContext());

        texto1 = findViewById(R.id.texto1);
        texto2 = findViewById(R.id.texto2);
        observacao = findViewById(R.id.OBS);
        textValor = findViewById(R.id.textValor);
        editData = findViewById(R.id.editData);
        editData.setText("  " + DateCustom.dataAtual());

        //focar no editText
        //Configurar localidade para pt. mascara moeda
        Locale locale = new Locale("pt","BR");
        textValor.setLocale(locale);
        textValor.requestFocus();


        floating_action_button =  findViewById(R.id.buttonSalvarReceita);

        //salvando os dados e enviando pra MainActivity
        floating_action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(camposAnalizados()){
                    Long campoValor = textValor.getRawValue();
                    String textoValorS =  String.valueOf(campoValor);


                    String textoOpcao = texto2.getText().toString();
                    String textoData = editData.getText().toString();
                    String textoObservacao = observacao.getText().toString();


                    Receita receita = new Receita();
                    receita.setValor(Integer.parseInt(textoValorS));
                    receita.setData(textoData);
                    receita.setCategoria(textoOpcao);
                    receita.setObservacao(textoObservacao);

                    if(receitaController.incluir(receita)){
                        Toast.makeText(getApplicationContext(),"Receita salva com sucesso!",Toast.LENGTH_SHORT).show();
                        limparCampos();
                    }else {
                        Toast.makeText(getApplicationContext(),"Erro ao salvar",Toast.LENGTH_SHORT).show();

                    }





                }
            }
        });



        //gera a lista de opçoes de receita
        listaReceitaOpcao = new ArrayList<>();
        listaReceitaOpcao.add("Entregas Aplicativos");
        listaReceitaOpcao.add("Entregas Particular");
        listaReceitaOpcao.add("Corrida Aplicativos");
        listaReceitaOpcao.add("Corrida Particular");
        listaReceitaOpcao.add("Gorjeta");
        listaReceitaOpcao.add("Outros");

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),R.layout.lista_opcoes_receita, listaReceitaOpcao);
        texto2.setAdapter(arrayAdapter);

        texto2.setThreshold(1);
    }







    public boolean camposAnalizados(){
        String textoValor = textValor.getText().toString();
        String textoOpcao = texto2.getText().toString();
        String textoData = editData.getText().toString();
        String textoObservacao = observacao.getText().toString();

        if(!textoValor.isEmpty()){
            if(!textoOpcao.isEmpty()){
                if(!textoData.isEmpty()){
                    if(!texto2.getText().toString().equals("Categoria")){
                        return true;
                    }else {
                        Toast.makeText(getApplicationContext(), "Defina uma Categoria", Toast.LENGTH_SHORT).show();
                            return false;
                    }

                }else{
                    Toast.makeText(getApplicationContext(), "Defina uma data", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                Toast.makeText(getApplicationContext(), "Escolha uma Opção", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(getApplicationContext(), "Digite um Valor", Toast.LENGTH_SHORT).show();
            return false;
        }

    }

    public void limparCampos(){
        editData.setText(DateCustom.dataAtual());
        textValor.setText("");
        observacao.setText("");
    }
}