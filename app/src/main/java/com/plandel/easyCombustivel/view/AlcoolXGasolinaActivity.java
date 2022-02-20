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

public class AlcoolXGasolinaActivity extends AppCompatActivity {

  private TextInputEditText editvalorGasolina,editValorAlcool;
   private Button calcular,apagar;
   private LinearLayout buttonVoltar;
   private TextView textTitulo;
   private ImageView ajudaAlXGas;
   private ProgressBar progressBarAXG;
   private ConstraintLayout  constraintLayout3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alcool_xgasolina);

        editValorAlcool = findViewById(R.id.editValorAlcool1);
        editvalorGasolina = findViewById(R.id.editvalorGasolina1);
        textTitulo = findViewById(R.id.textTitulo1);
        String titulo =  textTitulo.getText().toString();
        constraintLayout3 = findViewById(R.id.constraintLayout3);

        calcular = findViewById(R.id.buttonCalcular2);
        apagar = findViewById(R.id.buttonLimpar2);
        buttonVoltar = findViewById(R.id.buttonVoltar3);
        ajudaAlXGas = findViewById(R.id.ajudaAlXGas);
        progressBarAXG = findViewById(R.id.progressBarAXG);


        ajudaAlXGas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                abrirDialog();

            }
        });



        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calcularComparacao();
            }
        });

        apagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editValorAlcool.setText("");
                editvalorGasolina.setText("");
                textTitulo.setText(titulo);
                constraintLayout3.setBackgroundColor(getResources().getColor(R.color.white));
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

            textTitulo.setText("");
            progressBarAXG.setVisibility(View.VISIBLE);

            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    progressBarAXG.setVisibility(View.GONE);

                    textTitulo.setTextColor(getResources().getColor(R.color.black));
                    constraintLayout3.setBackgroundColor(getResources().getColor(R.color.background_result));

                    if (valorComparacao > 0.7){
                        textTitulo.setText("O melhor combustível para você é a GASOLINA");

                    }else {
                        textTitulo.setText("O melhor combustível para você é o ÁLCOOL");
                    }

                }
            },1000);



        }

    }

    public void abrirDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Descrição")
                .setMessage("   1- Valor Litro Gasolina: Adicione o valor do litro da gasolina. \n \n"  +
                        "    2- Valor Litro Álcool: Adicione o valor do litro do álcool. \n \n" +
                        "    3- O resulta será o combustível mais econômico no momento. \n \n")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.create();
        builder.show();
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