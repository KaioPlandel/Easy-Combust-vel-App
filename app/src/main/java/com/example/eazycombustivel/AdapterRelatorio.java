package com.example.eazycombustivel;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eazycombustivel.model.Ticket;

import java.util.List;

public class AdapterRelatorio extends RecyclerView.Adapter<AdapterRelatorio.MyViewHolder> {

    private List<Ticket> listaTicket;

    public AdapterRelatorio(List<Ticket> lista) {
        this.listaTicket = lista;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.relatorio_adapter,parent,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Ticket ticket1 = listaTicket.get(position);
        holder.valor.setText(ticket1.getValor());
        holder.categoria.setText(ticket1.getCategoria());
        holder.observacao.setText(ticket1.getObservacao());
        holder.data.setText(ticket1.getData());
        holder.tipo.setText(ticket1.getTipo());


    }

    @Override
    public int getItemCount() {
        return listaTicket.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView data;
        TextView valor;
        TextView categoria;
        TextView observacao;
        TextView tipo;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.textData);
            valor =itemView.findViewById(R.id.textPreco);
            categoria =itemView.findViewById(R.id.textCateg);
            observacao =itemView.findViewById(R.id.textObs);
            tipo = itemView.findViewById(R.id.textTipo);

        }
    }
}
