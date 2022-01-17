package com.example.eazycombustivel.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


import com.example.eazycombustivel.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton buttonAddCombustivel;
    ConstraintLayout buttonCalculoKmLitro, buttonAlcoolXGasolina, buttonQuantidadeLitro, buttonCustoPercurso;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCalculoKmLitro = findViewById(R.id.buttonCalculoKmLitro);
        buttonAlcoolXGasolina = findViewById(R.id.buttonalcoolXGasolina);
        buttonQuantidadeLitro = findViewById(R.id.litrosGasto);
        buttonCustoPercurso = findViewById(R.id.buttonCustoPercurso);

        buttonAddCombustivel = findViewById(R.id.buttonaddCombustivel);
        // buttonSave = findViewById(R.id.buttonSave);

        // QUANDO CLICAR NO BOTAO VAI PRA TELA DE ADD DESPESA

        buttonAddCombustivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddDespesa();
            }
        });
        buttonQuantidadeLitro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuantidadeLitrosActivity.class);
                startActivity(intent);
            }
        });

        buttonAlcoolXGasolina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AlcoolXGasolinaActivity.class);
                startActivity(intent);
            }
        });


        buttonCalculoKmLitro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MediaKmLitroActivity.class);
                startActivity(intent);
            }
        });

        buttonCustoPercurso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CustoXTrajetoActivity.class);
                startActivity(intent);
            }
        });


    }


    public void goToAddDespesa() {
        startActivity(new Intent(getApplicationContext(), ReceitaEDespesaActivity.class));
    }

}



