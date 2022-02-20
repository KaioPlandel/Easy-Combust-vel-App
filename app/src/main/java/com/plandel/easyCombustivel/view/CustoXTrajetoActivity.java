package com.plandel.easyCombustivel.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.android.material.textfield.TextInputEditText;
import com.plandel.easyCombustivel.R;

import java.text.DecimalFormat;

public class CustoXTrajetoActivity extends AppCompatActivity {

    private TextInputEditText editKmPLitro,editKmPercurso,editValorLitro;
    private Button calcular,limpar;
    private TextView textTitulo;
    private String textoKmPercurso,textoValorLitro,textoKmPLitro;
    private LinearLayout buttonVoltar;
    private ImageView ajudaCustoTrajeto;
    private ProgressBar progressBarCustoTrajeto;
    private ConstraintLayout constraintLayout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custo_xtrajeto);
        editKmPercurso = findViewById(R.id.editKmPercurso1);
        editValorLitro = findViewById(R.id.editvalorLitro1);
        editKmPLitro = findViewById(R.id.editKmPLitro1);
        textTitulo = findViewById(R.id.textTitulo);
        String titulo = textTitulo.getText().toString();
        progressBarCustoTrajeto = findViewById(R.id.progressBarCustoTrajeto);

        calcular = findViewById(R.id.buttonCalcular5);
        limpar = findViewById(R.id.buttonLimpar5);
        buttonVoltar = findViewById(R.id.buttonVoltar5);
        ajudaCustoTrajeto = findViewById(R.id.ajudaCustoTrajeto);
        constraintLayout3 = findViewById(R.id.constraintLayout3);

        ajudaCustoTrajeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDialog();
            }
        });

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
                constraintLayout3.setBackgroundColor(getResources().getColor(R.color.white));
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

                    textTitulo.setText("");

                    progressBarCustoTrajeto.setVisibility(View.VISIBLE);

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBarCustoTrajeto.setVisibility(View.GONE);
                            textTitulo.setText("Valor Total a Gastar é R$ " + valorFormatado + ". Será necessário "+ quantidadeLitrosFormat  + " Litros. ");
                            textTitulo.setTextColor(getResources().getColor(R.color.black));
                            constraintLayout3.setBackgroundColor(getResources().getColor(R.color.background_result));

                        }
                    },1000);
                }

            }
        });


    }

    public boolean camposAnalizados(){

        if(!editKmPLitro.getText().toString().isEmpty()){
            if(!editKmPercurso.getText().toString().isEmpty()){
                if(!editValorLitro.getText().toString().isEmpty()){
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

    public void abrirDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Descrição")
                .setMessage("   1- Média Consumo: Adicione quanto o seu veículo faz por litro. \n \n"  +
                        "    2- Percurso Total: Adicione em Km a distância que irá percorrer. \n \n" +
                        "    3- Valor Litro: Adicione o valor do litro do combustível. \n \n" +
                        " O resultado será o valor total que irá gastar para fazer o trajeto. \n \n")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.create();
        builder.show();
    }




}