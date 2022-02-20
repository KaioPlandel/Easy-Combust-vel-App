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

public class QuantidadeLitrosActivity extends AppCompatActivity {

    private TextInputEditText editValoPago,precoCombustivelLitro;
    private Button buttonCalcular,buttonLimpar;
    private TextView titulo;
    private LinearLayout buttonVoltar;
    private String textoPrecoCombustivelLitro,textoValorPago;
    private ImageView ajudaCombustivel;
    private ProgressBar progressBarQL;
    private ConstraintLayout constraintLayout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quantidade_litros);

        editValoPago = findViewById(R.id.editValoPago1);
        precoCombustivelLitro = findViewById(R.id.editPrecoCombustivelLitro1);
        titulo = findViewById(R.id.textViewTituto2);
        buttonVoltar = findViewById(R.id.buttonVoltar4);
        String tituloReal = titulo.getText().toString();

        buttonCalcular = findViewById(R.id.buttonCalcular4);
        buttonLimpar = findViewById(R.id.buttonLimpar4);
        ajudaCombustivel = findViewById(R.id.ajudaCombustivel);
        progressBarQL = findViewById(R.id.progressBarQL);
        constraintLayout3 = findViewById(R.id.constraintLayout3);

        ajudaCombustivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirDialog();
            }
        });

        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(camposAnalizados()){
                    textoPrecoCombustivelLitro = precoCombustivelLitro.getText().toString();
                    textoValorPago = editValoPago.getText().toString();


                    if(textoPrecoCombustivelLitro.contains(",")){
                        textoPrecoCombustivelLitro = textoPrecoCombustivelLitro.replace(",",".");
                    }
                    if(textoValorPago.contains(",")){
                        textoValorPago = textoValorPago.replace(",",".");
                    }

                    double valorCombustivelLitro = Double.parseDouble(textoPrecoCombustivelLitro);
                    double valorPago = Double.parseDouble(textoValorPago);

                    Double resultadoCalculo = valorPago / valorCombustivelLitro;
                    String valorFormatado = new DecimalFormat("0.0").format(resultadoCalculo);


                    progressBarQL.setVisibility(View.VISIBLE);
                    titulo.setText("");

                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            progressBarQL.setVisibility(View.GONE);
                            titulo.setText("A Quantidade exata de litros é " + valorFormatado);
                            titulo.setTextColor(getResources().getColor(R.color.black));
                            constraintLayout3.setBackgroundColor(getResources().getColor(R.color.background_result));

                        }
                    },1000);
                }



            }
        });

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        buttonLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editValoPago.setText("");
                precoCombustivelLitro.setText("");
                constraintLayout3.setBackgroundColor(getResources().getColor(R.color.white));
                titulo.setText(tituloReal);
            }
        });


    }

    public void abrirDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Descrição")
                .setMessage("   1- Valor a Pagar: Adicione o valor que você deseja abastecer. \n \n"  +
                        "    2- Valor Litro Combustível: Adicione o valor do litro de combustível. \n \n" +
                        " O resultado será o total de litros à abastecer com o valor. \n \n")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.create();
        builder.show();
    }

    public boolean camposAnalizados(){

        if(!editValoPago.getText().toString().isEmpty()){
            if(!precoCombustivelLitro.getText().toString().isEmpty()){
                return true;
            }else{
                Toast.makeText(getApplicationContext(), "Digite o Valor do litro de Combustível", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(getApplicationContext(), "Digite o Valor Pago", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}