package com.example.eazycombustivel.view;

import androidx.appcompat.app.AppCompatActivity;

import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;
import android.widget.LinearLayout;


import com.example.eazycombustivel.Helper.AdapterRelatorio;
import com.example.eazycombustivel.Helper.DespesaDAO;
import com.example.eazycombustivel.Helper.ReceitaDAO;
import com.example.eazycombustivel.R;
import com.example.eazycombustivel.model.Despesa;
import com.example.eazycombustivel.model.Receita;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;

import java.util.ArrayList;
import java.util.List;

public class RelatorioActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);



        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Receita", ReceitaRelatorioFragment.class)
                .add("Despesa", DespesaRelatorioFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);










    }

}