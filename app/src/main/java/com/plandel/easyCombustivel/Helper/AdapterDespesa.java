package com.plandel.easyCombustivel.Helper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.plandel.easyCombustivel.R;
import com.plandel.easyCombustivel.model.Despesa;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class AdapterDespesa extends RecyclerView.Adapter<AdapterDespesa.MyViewHolder> {

    List<Despesa> listaDespesa = new ArrayList<>();
    public AdapterDespesa(List<Despesa> lista) {
        this.listaDespesa = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

      View itemLista =  LayoutInflater.from(parent.getContext()).inflate(R.layout.despesa_adapter,parent,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Despesa despesa = listaDespesa.get(position);
        Locale locale = new Locale("pt", "BR");
        NumberFormat currencyFormatter = NumberFormat.getCurrencyInstance(locale);

        holder.textPreco.setText(currencyFormatter.format(despesa.getValor()));
        holder.textData.setText(despesa.getData());
        holder.textCateg.setText(despesa.getCategoria());
        holder.textObs.setText(despesa.getObservacao());

    }

    @Override
    public int getItemCount() {
        return listaDespesa.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView textCateg,textData,textPreco,textObs;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textCateg = itemView.findViewById(R.id.textCategDespesa);
            textData = itemView.findViewById(R.id.textDataDespesa);
            textPreco = itemView.findViewById(R.id.textPrecoDespesa);
            textObs = itemView.findViewById(R.id.textObsDespesa);
        }
    }
}
