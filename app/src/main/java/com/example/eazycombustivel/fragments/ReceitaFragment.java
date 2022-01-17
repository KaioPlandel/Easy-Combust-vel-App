package com.example.eazycombustivel.fragments;


import android.os.Bundle;

import androidx.fragment.app.Fragment;


import android.view.LayoutInflater;
import android.view.View;

import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import android.widget.EditText;

import android.widget.Toast;

import com.example.eazycombustivel.Helper.DateCustom;

import com.example.eazycombustivel.R;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReceitaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReceitaFragment extends Fragment {

   private TextInputLayout texto1;
   private AutoCompleteTextView texto2;
   private ArrayList<String> listaReceita;
   private ArrayAdapter<String> arrayAdapter;
   private EditText textValor,editData,observacao;
   private FloatingActionButton floating_action_button;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReceitaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReceitaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReceitaFragment newInstance(String param1, String param2) {
        ReceitaFragment fragment = new ReceitaFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_receita, container, false);

        texto1 = view.findViewById(R.id.texto1);
        texto2 = view.findViewById(R.id.texto2);
        observacao = view.findViewById(R.id.OBS);

        textValor = view.findViewById(R.id.textValor);
        editData = view.findViewById(R.id.editData);
        editData.setText(DateCustom.dataAtual());

        floating_action_button =  view.findViewById(R.id.floating_action_button);




        //gera a lista de opçoes de receita
        listaReceita = new ArrayList<>();
        listaReceita.add("Entregas Aplicativos");
        listaReceita.add("Entregas Particular");
        listaReceita.add("Corrida Aplicativos");
        listaReceita.add("Corrida Particular");
        listaReceita.add("Gorjeta");
        listaReceita.add("Outros");

        arrayAdapter = new ArrayAdapter<>(getContext(),R.layout.lista_opcoes_receita,listaReceita);
        texto2.setAdapter(arrayAdapter);

        texto2.setThreshold(1);


        return view;
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
                    Toast.makeText(getContext(), "Defina uma data", Toast.LENGTH_SHORT).show();
                    return false;
                }
            }else{
                Toast.makeText(getContext(), "Escolha uma Opção", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            Toast.makeText(getContext(), "Digite um Valor", Toast.LENGTH_SHORT).show();
            return false;
        }

    }


}