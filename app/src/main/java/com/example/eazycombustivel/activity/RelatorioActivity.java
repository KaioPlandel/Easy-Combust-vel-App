package com.example.eazycombustivel.activity;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.eazycombustivel.AdapterRelatorio;
import com.example.eazycombustivel.Helper.DbHelper;
import com.example.eazycombustivel.Helper.RecyclerItemClickListener;
import com.example.eazycombustivel.Helper.TicketDAO;
import com.example.eazycombustivel.R;
import com.example.eazycombustivel.model.Ticket;

import java.util.ArrayList;
import java.util.List;

public class RelatorioActivity extends AppCompatActivity {

    private RecyclerView recycleViewRelatorio;
    private AdapterRelatorio adapterRelatorio;
    private List<Ticket> listaTicket = new ArrayList<>();
    private Ticket ticketSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        recycleViewRelatorio = findViewById(R.id.recycleViewRelatorio);


        //Adicionar evento de clique
        recycleViewRelatorio.addOnItemTouchListener(
                new RecyclerItemClickListener(
                        getApplicationContext(),
                        recycleViewRelatorio,
                        new RecyclerItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {


                                //Recuperar valor
                                Ticket ticketSelecionado = listaTicket.get(position);
                                String tipo = ticketSelecionado.getTipo();

                                if (tipo.equals("Receita")) {
                                    //Envia para a tela de origem
                                    Intent intent = new Intent(RelatorioActivity.this, ReceitaActivity.class);
                                    intent.putExtra("ticketSelecionado", ticketSelecionado);
                                    startActivity(intent);

                                }


                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                                //Recuperar Ticket
                                ticketSelecionado = listaTicket.get(position);


                                //Configurar Dialog
                                AlertDialog.Builder dialog = new AlertDialog.Builder(RelatorioActivity.this);
                                dialog.setTitle("Apagar lançamento");
                                dialog.setMessage("Tem certeza que deseja apagar este lançamento?");
                                dialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        TicketDAO ticketDAO = new TicketDAO(getApplicationContext());
                                        if (ticketDAO.deletar(ticketSelecionado)) {
                                            carregarListaReceita();
                                            Toast.makeText(getApplicationContext(), "Lançamento apagado", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Erro ao apagar lançamento", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                });
                                dialog.setNegativeButton("Não", null);

                                dialog.create();
                                dialog.show();
                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );

    }

    @Override
    protected void onStart() {
        super.onStart();
        carregarListaReceita();
    }

    public void carregarListaReceita() {

        //listar Receitas
        TicketDAO ticketDAO = new TicketDAO(getApplicationContext());
        listaTicket = ticketDAO.listar();


        //Configurar um adapter
        adapterRelatorio = new AdapterRelatorio(listaTicket);


        //Configurar RecyclerView
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recycleViewRelatorio.setHasFixedSize(true);
        recycleViewRelatorio.addItemDecoration(new DividerItemDecoration(getApplicationContext(), LinearLayout.VERTICAL));
        recycleViewRelatorio.setLayoutManager(layoutManager);
        recycleViewRelatorio.setAdapter(adapterRelatorio);
    }
}