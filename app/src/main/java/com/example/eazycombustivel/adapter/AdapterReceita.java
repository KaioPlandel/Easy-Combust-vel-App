package com.example.eazycombustivel.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eazycombustivel.Helper.Receita;
import com.example.eazycombustivel.R;

import java.util.ArrayList;
import java.util.List;

public class AdapterReceita extends RecyclerView.Adapter<AdapterReceita.MyViewHolder> {
    private Context context;
    private ArrayList<String> receitaValor,receitaData,receitaCategoria,receitaOBS;

    public AdapterReceita(Context context, ArrayList receitaValor, ArrayList receitaData,ArrayList receitaCategoria,ArrayList receitaOBS) {
        this.context = context;
        this.receitaValor = receitaValor;
        this.receitaData = receitaData;
        this.receitaCategoria = receitaCategoria;
        this.receitaOBS = receitaOBS;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

       LayoutInflater inflanter = LayoutInflater.from(context);
      View view = inflanter.inflate(R.layout.adapter_receita,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.valor.setText(String.valueOf(receitaValor.get(position)));
        holder.data.setText(String.valueOf(receitaData.get(position)));
        holder.tipo.setText(String.valueOf(receitaCategoria.get(position)));
        holder.observacao.setText(String.valueOf(receitaOBS.get(position)));



    }

    @Override
    public int getItemCount() {
        return 5;
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
