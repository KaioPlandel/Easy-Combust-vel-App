package com.plandel.easyCombustivel.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.plandel.easyCombustivel.Helper.DateCustom;
import com.plandel.easyCombustivel.Helper.DespesaDAO;
import com.plandel.easyCombustivel.Helper.ReceitaDAO;

import com.plandel.easyCombustivel.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

import java.text.NumberFormat;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {

    private ConstraintLayout buttonCalculoKmLitro, buttonAlcoolXGasolina, buttonQuantidadeLitro, buttonCustoPercurso;
    private com.github.clans.fab.FloatingActionButton addReceita, addDespesa;
    private LinearLayout menuRelatorio;
    private TextView editTotalGanho,editTotalDespesa,editSaldo;
    private ReceitaDAO receitaDAO;
    private DespesaDAO despesaDAO;
    private MaterialCalendarView calendarView;
    private String dataAtual;
    private ImageView buttonRelatorio;
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
        editTotalDespesa = findViewById(R.id.editDespesa);
        editSaldo = findViewById(R.id.editSaldo);
        calendarView = findViewById(R.id.calendarView);
        buttonRelatorio = findViewById(R.id.buttonRelatorio);
        receitaDAO = new ReceitaDAO(getApplicationContext());
        despesaDAO = new DespesaDAO(getApplicationContext());
        calendarView.setTitleMonths(DateCustom.getNomeMeses());

        calendarView.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

                String mes = String.valueOf(date.getMonth());
                String ano = String.valueOf(date.getYear());
                String mesAno = 0+mes+ano;

                apresentarSaldo(mesAno);
            }
        });

        buttonRelatorio.setOnClickListener(new View.OnClickListener() {
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

        String mes =  String.valueOf(calendarView.getCurrentDate().getMonth());
        String ano = String.valueOf(calendarView.getCurrentDate().getYear());
        dataAtual = 0+mes+ano;
        apresentarSaldo(dataAtual);
    }


    public void apresentarSaldo(String Atual){

        Log.i("TAG", "onMonthChanged: " + Atual);


        try {
            double totalReceita =receitaDAO.somarTotal(Atual);
            Locale locale = new Locale("pt", "BR");
            NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);
            editTotalGanho.setText(String.valueOf("Receita: " + currencyFormatter.format(totalReceita)));

            double totalDespesa = despesaDAO.somarTotal(Atual);
            editTotalDespesa.setText(String.valueOf("Despesa: " + currencyFormatter.format(totalDespesa)));

            double totalSaldo = totalReceita - totalDespesa;
            if(totalSaldo > 0){
                editSaldo.setTextColor(getResources().getColor(R.color.icone_color));
            }else if(totalSaldo == 0){
                editSaldo.setTextColor(getResources().getColor(R.color.background));

            }else {
                editSaldo.setTextColor(getResources().getColor(R.color.red));
            }
            editSaldo.setText(String.valueOf("Saldo: " + currencyFormatter.format(totalSaldo)));
        }catch (Exception e){
            e.printStackTrace();
        }


    }


}



