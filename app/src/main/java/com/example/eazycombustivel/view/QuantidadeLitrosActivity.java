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

public class QuantidadeLitrosActivity extends AppCompatActivity {

    private TextInputEditText editValoPago,precoCombustivelLitro;
    private Button buttonCalcular,buttonLimpar;
    private TextView titulo;
    private LinearLayout buttonVoltar;
    private String textoPrecoCombustivelLitro,textoValorPago;

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



                    titulo.setText("A Quantidade exata de litros é " + valorFormatado);



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
                titulo.setText(tituloReal);
            }
        });


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