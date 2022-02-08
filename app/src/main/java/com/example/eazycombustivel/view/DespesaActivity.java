package com.example.eazycombustivel.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.blackcat.currencyedittext.CurrencyEditText;
import com.example.eazycombustivel.Helper.DateCustom;
import com.example.eazycombustivel.Helper.DespesaDAO;
import com.example.eazycombustivel.R;
import com.example.eazycombustivel.model.Despesa;
import com.example.eazycombustivel.model.Receita;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;
import java.util.Locale;

public class DespesaActivity extends AppCompatActivity {

    private TextInputLayout textInputLayout;
    private AutoCompleteTextView textListCategory;
    private ArrayList<String> listaDespesa;
    private ArrayAdapter<String> arrayAdapter;
    private CurrencyEditText textValorDespesa;
    private EditText editDataDespesa, editObsDespesa;
    private FloatingActionButton buttonEnviarDespesa;
    private DespesaDAO despesaDAO;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_despesa);

        textInputLayout = findViewById(R.id.textField1);
        textListCategory = findViewById(R.id.textField2);
        editDataDespesa = findViewById(R.id.editDataDespesa);
        textValorDespesa = findViewById(R.id.textValorDespesa);
        editObsDespesa = findViewById(R.id.editObsDespesa);
        buttonEnviarDespesa = findViewById(R.id.buttonEnviarDespesa);
        toolbar = findViewById(R.id.toolbar);

        toolbar.setTitle("Adicionar Despesa");
        toolbar.setBackgroundColor(getResources().getColor(R.color.background));
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        //Instanciar despesaDao
        despesaDAO = new DespesaDAO(getApplicationContext());

        //Configurar localidade para pt. mascara moeda
        Locale locale = new Locale("pt","BR");
        textValorDespesa.setLocale(locale);
        textValorDespesa.requestFocus();

        //adicionar a data atual
        editDataDespesa.setText(" " + DateCustom.dataAtual());

        //criar lista de despesas
        listaDespesa = new ArrayList<>();
        listaDespesa.add("Combustível");
        listaDespesa.add("Manutenção");
        listaDespesa.add("Pneus");
        listaDespesa.add("Pintura");
        listaDespesa.add("IPVA");
        listaDespesa.add("Multa");
        listaDespesa.add("Financiamento");
        listaDespesa.add("Lavagem");
        listaDespesa.add("Seguro");

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),R.layout.lista_opcoes_receita,listaDespesa);
        textListCategory.setAdapter(arrayAdapter);

        textListCategory.setThreshold(1);


        //recuperar despesa caso edição
       Despesa despesaSelecionada = (Despesa) getIntent().getSerializableExtra("pacoteDespesa");
        if(despesaSelecionada != null){

            textValorDespesa.setText(String.valueOf(despesaSelecionada.getValor() *10));
            editDataDespesa.setText(despesaSelecionada.getData());
            editObsDespesa.setText(despesaSelecionada.getObservacao());

        }


        //salvando os dados onClick
        buttonEnviarDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(camposAnalizados()){

                    if(despesaSelecionada != null){//atualizar


                        long campoValorLong = textValorDespesa.getRawValue();
                        double valorDouble = Double.parseDouble(String.valueOf(campoValorLong));

                        double campoValor = valorDouble / 100;
                        Log.i("TAG", "onClick: " + campoValor);



                        String textoOpcao = textListCategory.getText().toString();
                        String textoData = editDataDespesa.getText().toString();
                        String[] split = textoData.split("/");
                        String mes = split[1];
                        String ano = split[2];
                        String mesAno = mes + ano;

                        Log.i("TAG", "Mes e ano: " + mes + ano);
                        String textoObservacao = editObsDespesa.getText().toString();


                        Despesa despesa = new Despesa();
                        despesa.setValor(campoValor);
                        despesa.setCategoria(textoOpcao);
                        despesa.setData(textoData);
                        despesa.setMesAno(mesAno);
                        despesa.setObservacao(textoObservacao);


                        Despesa despesaAtual = new Despesa();
                        despesaAtual.setId(despesaSelecionada.getId());
                        despesaAtual.setValor(campoValor);
                        despesaAtual.setCategoria(textoOpcao);
                        despesaAtual.setData(textoData);
                        despesaAtual.setMesAno(mesAno);
                        despesaAtual.setObservacao(textoObservacao);

                        if(despesaDAO.atualizar(despesaAtual)){
                            Toast.makeText(getApplicationContext(), "Despesa atualizada com sucesso!", Toast.LENGTH_SHORT).show();
                            finish();
                        }


                    }else {//salvar

                        long campoValorLong = textValorDespesa.getRawValue();
                        double valorDouble = Double.parseDouble(String.valueOf(campoValorLong));

                        double campoValor = valorDouble / 100;
                        Log.i("TAG", "onClick: " + campoValor);



                        String textoOpcao = textListCategory.getText().toString();
                        String textoData = editDataDespesa.getText().toString();
                        String[] split = textoData.split("/");
                        String mes = split[1];
                        String ano = split[2];
                        String mesAno = mes + ano;

                        Log.i("TAG", "Mes e ano: " + mes + ano);
                        String textoObservacao = editObsDespesa.getText().toString();


                        Despesa despesa = new Despesa();
                        despesa.setValor(campoValor);
                        despesa.setCategoria(textoOpcao);
                        despesa.setData(textoData);
                        despesa.setMesAno(mesAno);
                        despesa.setObservacao(textoObservacao);

                        if(despesaDAO.salvar(despesa)){
                            limparCampos();
                            Toast.makeText(getApplicationContext(), "Despesa salva com sucesso!", Toast.LENGTH_SHORT).show();
                        }




                    }


                }


            }

        });
    }

    public boolean camposAnalizados(){
        String textoValor = textValorDespesa.getText().toString();
        String textoOpcao = textListCategory.getText().toString();
        String textoData = editDataDespesa.getText().toString();
        String textoObservacao = editObsDespesa.getText().toString();

        if(!textoValor.isEmpty()){
            if(!textoOpcao.isEmpty()){
                if(!textoData.isEmpty()){
                    if(!textListCategory.getText().toString().equals("Categoria")){
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
        editDataDespesa.setText(DateCustom.dataAtual());
        textValorDespesa.setText("");
        editObsDespesa.setText("");
    }
}