package com.example.eazycombustivel.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.example.eazycombustivel.Helper.DateCustom;
import com.example.eazycombustivel.Helper.TicketDAO;
import com.example.eazycombustivel.R;
import com.example.eazycombustivel.model.Ticket;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

public class ReceitaActivity extends AppCompatActivity {
    private TextInputLayout texto1;
    private AutoCompleteTextView texto2;
    private ArrayList<String> listaReceita;
    private ArrayAdapter<String> arrayAdapter;
    private EditText textValor,editData,observacao;
    private FloatingActionButton floating_action_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);



        texto1 = findViewById(R.id.texto1);
        texto2 = findViewById(R.id.texto2);
        observacao = findViewById(R.id.OBS);

        textValor = findViewById(R.id.textValor);
        editData = findViewById(R.id.editData);
        editData.setText(DateCustom.dataAtual());

        floating_action_button =  findViewById(R.id.buttonSalvarReceita);

        floating_action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(camposAnalizados()){
                    String textoValor = textValor.getText().toString();
                    String textoOpcao = texto2.getText().toString();
                    String textoData = editData.getText().toString();
                    String textoObservacao = observacao.getText().toString();
                    String textoTipo = "Receita";

                    Ticket ticket = new Ticket();
                    ticket.setData(textoData);
                    ticket.setValor(textoValor);
                    ticket.setCategoria(textoOpcao);
                    ticket.setObservacao(textoObservacao);
                    ticket.setTipo(textoTipo);

                    TicketDAO ticketDAO = new TicketDAO(getApplicationContext());
                    ticketDAO.salvar(ticket);




                }else{
                    Toast.makeText(getApplicationContext(),"Erro confira os campos",Toast.LENGTH_SHORT).show();
                }
            }
        });




        //gera a lista de opçoes de receita
        listaReceita = new ArrayList<>();
        listaReceita.add("Entregas Aplicativos");
        listaReceita.add("Entregas Particular");
        listaReceita.add("Corrida Aplicativos");
        listaReceita.add("Corrida Particular");
        listaReceita.add("Gorjeta");
        listaReceita.add("Outros");

        arrayAdapter = new ArrayAdapter<>(getApplicationContext(),R.layout.lista_opcoes_receita,listaReceita);
        texto2.setAdapter(arrayAdapter);

        texto2.setThreshold(1);
    }


    public boolean camposAnalizados(){
        String textoValor = textValor.getText().toString();
        String textoOpcao = texto2.getText().toString();
        String textoData = editData.getText().toString();
        String textoObservacao = observacao.getText().toString();

        if(!textoValor.isEmpty()){
            if(!textoOpcao.isEmpty()){
                if(!textoData.isEmpty()){
                    return true;
                }else{
                    Toast.makeText(getApplicationContext(), "Defina uma data", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                Toast.makeText(getApplicationContext(), "Escolha uma Opção", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(getApplicationContext(), "Digite um Valor", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}