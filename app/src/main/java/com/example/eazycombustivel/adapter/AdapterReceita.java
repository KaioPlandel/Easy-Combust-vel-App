package com.example.eazycombustivel.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eazycombustivel.Helper.Receita;
import com.example.eazycombustivel.R;

import java.util.List;

public class AdapterReceita extends RecyclerView.Adapter<AdapterReceita.MyViewHolder> {

    List<Receita> receitaLista;

    public AdapterReceita(List<Receita> lista) {
        this.receitaLista = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_receita,parent,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Receita receitas = receitaLista.get(position);

        holder.observacao.setText(receitas.getObservacao());
        holder.valor.setText(receitas.getValor());
        holder.data.setText(receitas.getData());
        holder.tipo.setText(receitas.getTipo());

    }

    @Override
    public int getItemCount() {
        return receitaLista.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tipo;
        TextView valor;
        TextView data;
        TextView observacao;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            tipo = itemView.findViewById(R.id.textoTipo);
            valor = itemView.findViewById(R.id.textoValor);
            data = itemView.findViewById(R.id.textoData);
            observacao = itemView.findViewById(R.id.textoComent);

        }
    }
}
