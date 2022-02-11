package com.plandel.easyCombustivel.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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

public class MediaKmLitroActivity extends AppCompatActivity {

   private LinearLayout buttonVoltar;
    private TextInputEditText editKmInicial,editKmFinal,editLitroAbastecido;
  private  Button buttonCalcular,buttonLimpar;
  private String textoKmInicial,textoKmFinal,textoKmLitros;
  private TextView titulo;
  private ProgressBar progressBarMedia;
  private ImageView ajudaMedia;

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
        progressBarMedia = findViewById(R.id.progressBarMedia);
        ajudaMedia = findViewById(R.id.ajudaMedia);

        ajudaMedia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                abrirDialog();
            }
        });


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

    public void abrirDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Descrição")
                .setMessage("   1- Km inicial: Adicione o km inicial do seu veículo antes de abastecer. \n \n"  +
                        "    2- km final: Quando seu veículo estiver na reserva adicione o km final. \n \n" +
                        "    3- litros Abastecidos: adicione a quantidade de litros que foi abastecido. \n \n" +
                        " O resultado será a média que seu veículo faz por litro de combustível. \n \n")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });

        builder.create();
        builder.show();
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
             if(mediaKm < 0){
                 Toast.makeText(getApplicationContext(),"O resultado não pode ser menor que 0.",Toast.LENGTH_SHORT).show();
                 mediaKm = 0;
             }else {
                 String valorFormatado = new DecimalFormat("0.0").format(mediaKm);

                 titulo.setText("");
                 progressBarMedia.setVisibility(View.VISIBLE);
                 new Handler().postDelayed(new Runnable() {
                     @Override
                     public void run() {
                         progressBarMedia.setVisibility(View.GONE);
                         titulo.setTextColor(getResources().getColor(R.color.background));
                         titulo.setText("Media: " + valorFormatado + " Km/L");
                     }
                 },1000);

             }





         }
    }

    public boolean camposAnalizados(){

        if(!editKmInicial.getText().toString().isEmpty()){
            if(!editKmFinal.getText().toString().isEmpty()){
                if(!editLitroAbastecido.getText().toString().isEmpty()){
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


