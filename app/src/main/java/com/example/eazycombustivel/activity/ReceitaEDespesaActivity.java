package com.example.eazycombustivel.activity;

import androidx.appcompat.app.AppCompatActivity;

import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.eazycombustivel.R;

import com.example.eazycombustivel.fragments.DespesaFragment;
import com.example.eazycombustivel.fragments.ReceitaFragment;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v4.FragmentPagerItems;


public class ReceitaEDespesaActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adicionar_despesa);

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(
                getSupportFragmentManager(), FragmentPagerItems.with(this)
                .add("Receita", ReceitaFragment.class)
                .add("Despesa", DespesaFragment.class)
                .create());

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPager.setAdapter(adapter);

        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);
        viewPagerTab.setViewPager(viewPager);


    }

}