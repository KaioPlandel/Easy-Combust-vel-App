package com.example.eazycombustivel.view;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eazycombustivel.R;
import com.google.android.material.textfield.TextInputEditText;

import java.text.DecimalFormat;

public class CustoXTrajetoActivity extends AppCompatActivity {

    private TextInputEditText editKmPLitro,editKmPercurso,editValorLitro;
    private Button calcular,limpar;
    private TextView textTitulo;
    private String textoKmPercurso,textoValorLitro,textoKmPLitro;
    private LinearLayout buttonVoltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custo_xtrajeto);
        editKmPercurso = findViewById(R.id.editKmPercurso1);
        editValorLitro = findViewById(R.id.editvalorLitro1);
        editKmPLitro = findViewById(R.id.editKmPLitro1);
        textTitulo = findViewById(R.id.textTitulo);
        String titulo = textTitulo.getText().toString();

        calcular = findViewById(R.id.buttonCalcular5);
        limpar = findViewById(R.id.buttonLimpar5);
        buttonVoltar = findViewById(R.id.buttonVoltar5);

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        limpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textTitulo.setText(titulo);
                editKmPercurso.setText("");
                editValorLitro.setText("");
                editKmPLitro.setText("");
            }
        });

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(camposAnalizados()){
                    //pegando os valores e passando para textos
                    textoKmPercurso = editKmPercurso.getText().toString();
                    textoValorLitro = editValorLitro.getText().toString();
                    textoKmPLitro = editKmPLitro.getText().toString();

                    if(textoKmPercurso.contains(",")){
                        textoKmPercurso = textoKmPercurso.replace(",",".");
                    }
                    if(textoValorLitro.contains(",")){
                        textoValorLitro = textoValorLitro.replace(",",".");
                    }
                    if(textoKmPLitro.contains(",")){
                        textoKmPLitro = textoKmPLitro.replace(",",".");
                    }

                    //Convertendo os textos para Double
                    double valorKmPercurso = Double.parseDouble(textoKmPercurso);
                    double valorLitro = Double.parseDouble(textoValorLitro);
                    double valorKmPorLitro = Double.parseDouble(textoKmPLitro);

                   //calculo aqui
                  double quantidadeLitrosNecessaria =  valorKmPercurso / valorKmPorLitro;
                  double valorAGastar = quantidadeLitrosNecessaria * valorLitro;


                    String quantidadeLitrosFormat = new DecimalFormat("0.0").format(quantidadeLitrosNecessaria);
                    String valorFormatado = new DecimalFormat("0.00").format(valorAGastar);

                    textTitulo.setText("Valor Total a Gastar é R$ " + valorFormatado + " Sera necessário "+ quantidadeLitrosFormat  + " Litros ");
                }

            }
        });


    }

    public boolean camposAnalizados(){

        if(!editKmPLitro.getText().toString().isEmpty()){
            if(!editKmPercurso.getText().toString().isEmpty()){
                if(!editValorLitro.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "sucesso", Toast.LENGTH_SHORT).show();
                    return true;
                }else{
                    Toast.makeText(getApplicationContext(), "Preencha o Campo Valor do Litro", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                Toast.makeText(getApplicationContext(), "Preencha o Campo Km por Percurso", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(getApplicationContext(), "Preencha o Campo Km por Litros", Toast.LENGTH_SHORT).show();
            return false;
        }

    }




}