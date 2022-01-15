package com.example.eazycombustivel.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.example.eazycombustivel.R;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DespesaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DespesaFragment extends Fragment {

   private TextInputLayout textInputLayout;
    private AutoCompleteTextView autoCompleteTextView;
   private ArrayList<String> listaDespesa;
   private ArrayAdapter<String> arrayAdapter;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DespesaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DespesaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DespesaFragment newInstance(String param1, String param2) {
        DespesaFragment fragment = new DespesaFragment();
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
        View view = inflater.inflate(R.layout.fragment_despesa, container, false);

        textInputLayout = view.findViewById(R.id.textField1);
        autoCompleteTextView = view.findViewById(R.id.textField2);

        listaDespesa = new ArrayList<>();
        listaDespesa.add("Entregas Aplicativos");
        listaDespesa.add("Entregas Particular");
        listaDespesa.add("Corrida Aplicativos");
        listaDespesa.add("Corrida Particular");
        listaDespesa.add("Gorjeta");
        listaDespesa.add("Outros");

        arrayAdapter = new ArrayAdapter<>(getContext(),R.layout.lista_opcoes_receita,listaDespesa);
        autoCompleteTextView.setAdapter(arrayAdapter);

        autoCompleteTextView.setThreshold(1);


        return view;
    }
}