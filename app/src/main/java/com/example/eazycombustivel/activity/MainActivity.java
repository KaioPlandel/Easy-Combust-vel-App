package com.example.eazycombustivel.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.eazycombustivel.R;
import com.example.eazycombustivel.fragments.HomeFragment;

public class MainActivity extends AppCompatActivity {

   private HomeFragment homeFragment;
   private ImageView buttonAddCombustivel;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        buttonAddCombustivel = findViewById(R.id.addCombustivel);

        //QUANDO CLICAR NO BOTAO VAI PRA TELA DE ADD DESPESA

        buttonAddCombustivel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToAddDespesa();
            }
        });


    }




    @Override
    protected void onStart() {
        super.onStart();
        goToHome();
    };

    public void goToHome(){
        homeFragment = new HomeFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragmento,homeFragment);
        transaction.commit();
    };

    public void goToAddDespesa(){
        startActivity(new Intent(getApplicationContext(), ReceitaEDespesaActivity.class));
    }
}



