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
    private Ticket ticketAtual;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receita);



        texto1 = findViewById(R.id.texto1);
        texto2 = findViewById(R.id.texto2);
        observacao = findViewById(R.id.OBS);

        textValor = findViewById(R.id.textValor);
        editData = findViewById(R.id.editData);
        editData.setText("  " + DateCustom.dataAtual());

        floating_action_button =  findViewById(R.id.buttonSalvarReceita);

        //Recuperar Tarefa se for edição
        ticketAtual = (Ticket) getIntent().getSerializableExtra("ticketSelecionado");

        //Configurar ticket na tela
        if(ticketAtual != null){
            editData.setText(ticketAtual.getData());
            textValor.setText(ticketAtual.getValor());
            observacao.setText(ticketAtual.getObservacao());
            texto2.setText(ticketAtual.getCategoria());
        }


        floating_action_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                TicketDAO ticketDAO = new TicketDAO(getApplicationContext());

                if(ticketAtual != null){//edicao
                    if(camposAnalizados()){
                        String textoValor = textValor.getText().toString();
                        String textoOpcao = texto2.getText().toString();
                        String textoData = editData.getText().toString();
                        String textoObservacao = observacao.getText().toString();
                        String textoTipo = "Receita";

                        Ticket ticket = new Ticket();
                        ticket.setId(ticketAtual.getId());
                        ticket.setData(textoData);
                        ticket.setValor(textoValor);
                        ticket.setCategoria(textoOpcao);
                        ticket.setObservacao(textoObservacao);
                        ticket.setTipo(textoTipo);

                        //atualizar banco de dados

                        if (ticketDAO.atualizar(ticket)) {
                            limparCampos();
                            Toast.makeText(getApplicationContext(),"Atualização Salva",Toast.LENGTH_SHORT).show();

                        }else {
                            Toast.makeText(getApplicationContext(),"Erro ao Atualizar",Toast.LENGTH_SHORT).show();

                        }


                    }




                }else {//salvar

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
                        if(ticketDAO.salvar(ticket)){
                            Toast.makeText(getApplicationContext(),"Salvo com Sucesso",Toast.LENGTH_SHORT).show();
                            limparCampos();
                        }else {
                            Toast.makeText(getApplicationContext(),"Erro ao Salvar",Toast.LENGTH_SHORT).show();

                        }

                    }

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

    public void limparCampos(){
        editData.setText(DateCustom.dataAtual());
        textValor.setText("");
        observacao.setText("");
    }
}