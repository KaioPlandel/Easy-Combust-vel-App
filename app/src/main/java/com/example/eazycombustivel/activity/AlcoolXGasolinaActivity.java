package com.example.eazycombustivel.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.eazycombustivel.R;
import com.google.android.material.textfield.TextInputEditText;

public class AlcoolXGasolinaActivity extends AppCompatActivity {

  private   TextInputEditText editvalorGasolina,editValorAlcool;
   private Button calcular,apagar;
   private LinearLayout buttonVoltar;
   private TextView textTitulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcool_xgasolina);

        editValorAlcool = findViewById(R.id.editValorAlcool1);
        editvalorGasolina = findViewById(R.id.editvalorGasolina1);
        textTitulo = findViewById(R.id.textTitulo1);
        String titulo =  textTitulo.getText().toString();

        calcular = findViewById(R.id.buttonCalcular2);
        apagar = findViewById(R.id.buttonLimpar2);
        buttonVoltar = findViewById(R.id.buttonVoltar3);



        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularComparacao();
            }
        });

        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textTitulo.setText(titulo);
                editValorAlcool.setText("");
                editvalorGasolina.setText("");
                textTitulo.setText("");
            }
        });

        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    public void calcularComparacao(){
        if(this.camposAnalizados()){

            String textoGasolina = editvalorGasolina.getText().toString();
            String textoAlcool = editValorAlcool.getText().toString();

            if(textoAlcool.contains(",")){
                textoAlcool = textoAlcool.replace(",",".");
            }
            if(textoGasolina.contains(",")){
                textoGasolina = textoGasolina.replace(",",".");
            }


            double valorGasolina = Double.parseDouble(textoGasolina);
            double valorAlcool = Double.parseDouble(textoAlcool);

            double valorComparacao = valorAlcool / valorGasolina;

            if (valorComparacao > 0.7){
                textTitulo.setText("O melhor combustível para Você é a GASOLINA");
            }else {
                textTitulo.setText("O melhor combustível para Você é o ÀLCOOL");
            }

        }

    }

    public boolean camposAnalizados(){

        if(!editvalorGasolina.getText().toString().isEmpty()){
            if(!editValorAlcool.getText().toString().isEmpty()){
                return true;
            }else{
                Toast.makeText(getApplicationContext(), "Digite o valor do Álcool", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(getApplicationContext(), "Digite o valor da Gasolina", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}