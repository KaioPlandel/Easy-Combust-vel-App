package com.plandel.easyCombustivel.Helper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.plandel.easyCombustivel.R;
import com.plandel.easyCombustivel.model.Receita;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterRelatorio extends RecyclerView.Adapter<AdapterRelatorio.MyViewHolder> {

    List<Receita> listaReceita = new ArrayList<>();

    public AdapterRelatorio(List<Receita> lista) {
        this.listaReceita = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.relatorio_adapter,parent,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Receita receita = listaReceita.get(position);
        Locale locale = new Locale("pt", "BR");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        holder.valor.setText(currencyFormatter.format(receita.getValor()));
        holder.categoria.setText(receita.getCategoria());
        holder.observacao.setText(receita.getObservacao());
        holder.data.setText(receita.getData());
    }

    @Override
    public int getItemCount() {
        return listaReceita.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView data;
        TextView valor;
        TextView categoria;
        TextView observacao;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.textData);
            valor =itemView.findViewById(R.id.textPreco);
            categoria =itemView.findViewById(R.id.textCateg);
            observacao =itemView.findViewById(R.id.textObs);
        }
    }
}
