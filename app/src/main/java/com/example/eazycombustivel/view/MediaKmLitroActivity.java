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

public class MediaKmLitroActivity extends AppCompatActivity {

   private LinearLayout buttonVoltar;
    private TextInputEditText editKmInicial,editKmFinal,editLitroAbastecido;
  private  Button buttonCalcular,buttonLimpar;
  private String textoKmInicial,textoKmFinal,textoKmLitros;
  private TextView titulo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_km_litro);

        buttonVoltar = findViewById(R.id.buttonVoltar2);
        buttonCalcular = findViewById(R.id.buttonCalcular);
        buttonLimpar = findViewById(R.id.buttonLimpar);
        titulo = findViewById(R.id.textViewTitulo);
        String tituloReal = titulo.getText().toString();

        editKmFinal = findViewById(R.id.editKmFinal1);
        editKmInicial = findViewById(R.id.editKmInicial1);
        editLitroAbastecido = findViewById(R.id.editLitroAbastecido1);




        buttonCalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fazerCalculoMedia();
            }
        });


        buttonLimpar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editKmFinal.setText("");
                editKmInicial.setText("");
                editLitroAbastecido.setText("");
                titulo.setText(tituloReal);
            }
        });


        buttonVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public void fazerCalculoMedia(){
         if(camposAnalizados()){
             //pegando os valores e passando para textos
             textoKmInicial = editKmInicial.getText().toString();
             textoKmFinal = editKmFinal.getText().toString();
             textoKmLitros = editLitroAbastecido.getText().toString();

             if(textoKmInicial.contains(",")){
                 textoKmInicial = textoKmInicial.replace(",",".");
             }
             if(textoKmFinal.contains(",")){
                 textoKmFinal = textoKmFinal.replace(",",".");
             }
             if(textoKmLitros.contains(",")){
                 textoKmLitros = textoKmLitros.replace(",",".");
             }

             //Convertendo os textos para Double
             double valorInicial = Double.parseDouble(textoKmInicial);
             double valorFinal = Double.parseDouble(textoKmFinal);
             double valorLitros = Double.parseDouble(textoKmLitros);

             //km percorridos ÷ litros para completar = quilometragem por litro

             double mediaKm = (valorFinal - valorInicial) / valorLitros;

             String valorFormatado = new DecimalFormat("#.0").format(mediaKm);

             titulo.setText("Media: " + valorFormatado + " KM L");

         }
    }

    public boolean camposAnalizados(){

        if(!editKmInicial.getText().toString().isEmpty()){
            if(!editKmFinal.getText().toString().isEmpty()){
                if(!editLitroAbastecido.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(), "sucesso", Toast.LENGTH_SHORT).show();
                    return true;
                }else{
                    Toast.makeText(getApplicationContext(), "Digite a quantidade de litros Abastecidos", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                Toast.makeText(getApplicationContext(), "Digite o Km final", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(getApplicationContext(), "Digite o Km Inicial", Toast.LENGTH_SHORT).show();
            return false;
        }

}




}


