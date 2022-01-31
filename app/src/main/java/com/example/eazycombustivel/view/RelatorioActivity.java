package com.example.eazycombustivel.view;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.widget.LinearLayout;


import com.example.eazycombustivel.Helper.AdapterRelatorio;
import com.example.eazycombustivel.R;
import com.example.eazycombustivel.controller.ReceitaController;
import com.example.eazycombustivel.model.Receita;

import java.util.ArrayList;
import java.util.List;

public class RelatorioActivity extends AppCompatActivity {

    private List<Receita> lista = new ArrayList<>();
    private RecyclerView recycleViewRelatorio;
    private AdapterRelatorio adapterRelatorio;
    private ReceitaController receitaController;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        receitaController = new ReceitaController(getApplicationContext());
        recycleViewRelatorio = findViewById(R.id.recycleViewRelatorio);


        //criar adapter
       adapterRelatorio = new AdapterRelatorio(receitaController.listar());


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycleViewRelatorio.setHasFixedSize(true);
        recycleViewRelatorio.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recycleViewRelatorio.setLayoutManager(layoutManager);
        recycleViewRelatorio.setAdapter(adapterRelatorio);



    }
}