package com.example.eazycombustivel.view;

import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.eazycombustivel.Helper.AdapterRelatorio;
import com.example.eazycombustivel.Helper.DespesaDAO;
import com.example.eazycombustivel.Helper.ReceitaDAO;
import com.example.eazycombustivel.Helper.RecyclerItemClickListener;
import com.example.eazycombustivel.R;
import com.example.eazycombustivel.model.Despesa;
import com.example.eazycombustivel.model.Receita;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReceitaRelatorioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReceitaRelatorioFragment extends Fragment {


    private List<Receita> lista = new ArrayList<>();
    private RecyclerView recycleViewRelatorio;
    private AdapterRelatorio adapterRelatorio;
    private ReceitaDAO receitaDAO;
    private DespesaDAO despesaDAO;
    private List<Receita> listaReceita = new ArrayList<>();

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReceitaRelatorioFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReceitaRelatorioFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReceitaRelatorioFragment newInstance(String param1, String param2) {
        ReceitaRelatorioFragment fragment = new ReceitaRelatorioFragment();
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

        receitaDAO = new ReceitaDAO(getActivity());
        despesaDAO = new DespesaDAO(getActivity());

        // Inflate the layout for this fragment
        View view  = inflater.inflate(R.layout.fragment_receita_relatorio, container, false);

        recycleViewRelatorio = view.findViewById(R.id.recycleViewRelatorio);



        recycleViewRelatorio.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recycleViewRelatorio, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(getActivity(), "Um clique", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongItemClick(View view, int position) {

                List<Receita> listaReceita = receitaDAO.listar();
                Receita receitaSelecionada = listaReceita.get(position);

                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("Deletar Receita")
                        .setMessage("Tem certeza que deseja apagar este lançamento ?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(receitaDAO.deletar(receitaSelecionada)){
                                    Toast.makeText(getActivity(),"Sucesso ao deletar",Toast.LENGTH_SHORT).show();
                                   listar();
                                }
                            }
                        }).setNegativeButton("Não",null);

                dialog.create();
                dialog.show();
            }

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        }));

        return view;

    }

    private void listar(){

        receitaDAO.listar();
        listaReceita = receitaDAO.listar();

        //criar adapter
        adapterRelatorio = new AdapterRelatorio(listaReceita);


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleViewRelatorio.setHasFixedSize(true);
        recycleViewRelatorio.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recycleViewRelatorio.setLayoutManager(layoutManager);
        recycleViewRelatorio.setAdapter(adapterRelatorio);

        

    }


    @Override
    public void onStart() {
        super.onStart();
        listar();
    }
}