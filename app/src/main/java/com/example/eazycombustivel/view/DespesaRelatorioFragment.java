package com.example.eazycombustivel.view;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.eazycombustivel.Helper.AdapterDespesa;
import com.example.eazycombustivel.Helper.AdapterRelatorio;
import com.example.eazycombustivel.Helper.DespesaDAO;
import com.example.eazycombustivel.Helper.ReceitaDAO;
import com.example.eazycombustivel.R;
import com.example.eazycombustivel.model.Despesa;
import com.example.eazycombustivel.model.Receita;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DespesaRelatorioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DespesaRelatorioFragment extends Fragment {

    private List<Receita> lista = new ArrayList<>();
    private RecyclerView recycleViewRelatorio;
    private DespesaDAO despesaDAO;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DespesaRelatorioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DespesaRelatorioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DespesaRelatorioFragment newInstance(String param1, String param2) {
        DespesaRelatorioFragment fragment = new DespesaRelatorioFragment();
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

        despesaDAO = new DespesaDAO(getActivity());

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_despesa_relatorio, container, false);

        recycleViewRelatorio = view.findViewById(R.id.recyclerDespesa);


        //criar adapter
        AdapterDespesa adapterDespesa = new AdapterDespesa(listarDespesa());



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleViewRelatorio.setHasFixedSize(true);
        recycleViewRelatorio.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recycleViewRelatorio.setLayoutManager(layoutManager);
        recycleViewRelatorio.setAdapter(adapterDespesa);

        return view;
    }

    private List<Despesa> listarDespesa(){
        return despesaDAO.listar();
    }

    @Override
    public void onStart() {
        super.onStart();
        listarDespesa();
    }
}