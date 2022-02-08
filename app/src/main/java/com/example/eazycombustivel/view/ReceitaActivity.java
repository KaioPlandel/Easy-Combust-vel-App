package com.example.eazycombustivel.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.example.eazycombustivel.Helper.DateCustom;
import com.example.eazycombustivel.Helper.ReceitaDAO;
import com.example.eazycombustivel.R;
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
    private ReceitaDAO receitaDAO;
    private Toolbar toolbarReceita;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);



        texto1 = findViewById(R.id.texto1);
        texto2 = findViewById(R.id.texto2);
        observacao = findViewById(R.id.OBS);
        textValor = findViewById(R.id.textValor);
        editData = findViewById(R.id.editData);
        editData.setText(DateCustom.dataAtual());
        toolbarReceita = findViewById(R.id.toolbarReceita);

        //configurar toolbar
        toolbarReceita.setTitle("Adicionar Receita");
        toolbarReceita.setBackgroundColor(getResources().getColor(R.color.background));
        toolbarReceita.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbarReceita);

        //focar no editText
        //Configurar localidade para pt. mascara moeda
        Locale locale = new Locale("pt","BR");
        textValor.setLocale(locale);
        textValor.requestFocus();

        receitaDAO = new ReceitaDAO(getApplicationContext());


        floating_action_button =  findViewById(R.id.buttonSalvarReceita);


        Receita receitaSelecionada = (Receita) getIntent().getSerializableExtra("pacoteReceita");
        if(receitaSelecionada != null){
            textValor.setText(String.valueOf(receitaSelecionada.getValor() *10));
            editData.setText(receitaSelecionada.getData());
            observacao.setText(receitaSelecionada.getObservacao());
        }



        //salvando os dados
        floating_action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(camposAnalizados()){

                    if(receitaSelecionada != null){//edição

                        long campoValorLong = textValor.getRawValue();
                        double valorDouble = Double.parseDouble(String.valueOf(campoValorLong));

                        double campoValor = valorDouble / 100;
                        Log.i("TAG", "onClick: " + campoValor);

                        String textoOpcao = texto2.getText().toString();
                        String textoData = editData.getText().toString();
                        String textoObservacao = observacao.getText().toString();

                        String[] split = textoData.split("/");
                        String mes = split[1];
                        String ano = split[2];
                        String mesAno = mes + ano;
                        Log.i("TAG", "Mes e ano: " + mes + ano);


                        Receita receitaAtual = new Receita();
                        receitaAtual.setId(receitaSelecionada.getId());
                        receitaAtual.setValor(campoValor);
                        receitaAtual.setData(textoData);
                        receitaAtual.setMesAno(mesAno);
                        receitaAtual.setCategoria(textoOpcao);
                        receitaAtual.setObservacao(textoObservacao);

                        if (receitaDAO.atualizar(receitaAtual)) {
                            Toast.makeText(getApplicationContext(),"Receita atualizada com sucesso!",Toast.LENGTH_SHORT).show();
                            finish();
                        }

                    }else {//Salvar

                        long campoValorLong = textValor.getRawValue();
                        double valorDouble = Double.parseDouble(String.valueOf(campoValorLong));

                        double campoValor = valorDouble / 100;
                        Log.i("TAG", "onClick: " + campoValor);



                        String textoOpcao = texto2.getText().toString();
                        String textoData = editData.getText().toString();
                        String textoObservacao = observacao.getText().toString();

                        String[] split = textoData.split("/");
                        String mes = split[1];
                        String ano = split[2];
                        String mesAno = mes + ano;
                        Log.i("TAG", "Mes e ano: " + mes + ano);


                        Receita receita = new Receita();
                        receita.setValor(campoValor);
                        receita.setData(textoData);
                        receita.setMesAno(mesAno);
                        receita.setCategoria(textoOpcao);
                        receita.setObservacao(textoObservacao);

                        if(receitaDAO.salvar(receita)){
                            limparCampos();
                            Toast.makeText(getApplicationContext(), "Receita salva com sucesso!", Toast.LENGTH_SHORT).show();
                        }



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