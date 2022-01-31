package com.example.eazycombustivel.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.blackcat.currencyedittext.CurrencyEditText;
import com.example.eazycombustivel.R;
import com.example.eazycombustivel.controller.ReceitaController;
import com.example.eazycombustivel.model.Receita;
import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class MainActivity extends AppCompatActivity {

    private FloatingActionButton buttonAddCombustivel;
    private ConstraintLayout buttonCalculoKmLitro, buttonAlcoolXGasolina, buttonQuantidadeLitro, buttonCustoPercurso;
    private com.github.clans.fab.FloatingActionButton addReceita, addDespesa;
    private LinearLayout menuRelatorio;
    private ReceitaController receitaController;
    private TextView editTotalGanho;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonCalculoKmLitro = findViewById(R.id.buttonCalculoKmLitro);
        buttonAlcoolXGasolina = findViewById(R.id.buttonalcoolXGasolina);
        buttonQuantidadeLitro = findViewById(R.id.litrosGasto);
        buttonCustoPercurso = findViewById(R.id.buttonCustoPercurso);
        addReceita = findViewById(R.id.addReceita);
        addDespesa = findViewById(R.id.addDespesa);
        menuRelatorio = findViewById(R.id.menuRelatorio);
        editTotalGanho = findViewById(R.id.textoTotalGanho);

        receitaController = new ReceitaController(getApplicationContext());




        menuRelatorio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RelatorioActivity.class);
                startActivity(intent);
            }
        });

        addDespesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, DespesaActivity.class);
                startActivity(intent);
            }
        });

        addReceita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ReceitaActivity.class);
                startActivity(intent);
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

    @Override
    protected void onStart() {
        super.onStart();

        int totalReceita = receitaController.somar();

        editTotalGanho.setText(String.valueOf("Receita: R$ " + totalReceita));
    }
}



