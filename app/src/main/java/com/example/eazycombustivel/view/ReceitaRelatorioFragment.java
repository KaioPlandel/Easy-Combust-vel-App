package com.example.eazycombustivel.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
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
import com.example.eazycombustivel.Helper.DateCustom;
import com.example.eazycombustivel.Helper.DespesaDAO;
import com.example.eazycombustivel.Helper.ReceitaDAO;
import com.example.eazycombustivel.Helper.RecyclerItemClickListener;
import com.example.eazycombustivel.R;
import com.example.eazycombustivel.model.Despesa;
import com.example.eazycombustivel.model.Receita;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnMonthChangedListener;

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
    private PieChart graficoReceita;
    private MaterialCalendarView calendarReceita;
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
        graficoReceita = view.findViewById(R.id.graficoReceita);
        calendarReceita = view.findViewById(R.id.calendarReceita);

       calendarReceita.setTitleMonths(DateCustom.getNomeMeses());

        String mes =  String.valueOf(calendarReceita.getCurrentDate().getMonth());
        String ano = String.valueOf(calendarReceita.getCurrentDate().getYear());
        String dataAtual = 0+mes+ano;

        gerarGraficoReceita(dataAtual);

        calendarReceita.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

                String mes = String.valueOf(date.getMonth());
                String ano = String.valueOf(date.getYear());
                String mesAno = 0+mes+ano;
                gerarGraficoReceita(mesAno);

            }
        });



        recycleViewRelatorio.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recycleViewRelatorio, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                //ATUALIZAR PASSANDO DADOS PARA A ACTIVITY DE ATUALIZAÇÃO
                Receita receitaSelecionada = listaReceita.get(position);

                Intent intent = new Intent(getActivity(),ReceitaActivity.class);
                intent.putExtra("pacoteReceita",receitaSelecionada);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

                //APAGAR RECEITA
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

    public void gerarGraficoReceita(String data){

        //Criando grafico
        ArrayList<PieEntry> receitas = new ArrayList<>();

        //recuperando despesas do banco e apresentando no grafico de acordo com a data
        ReceitaDAO receitaDAO = new ReceitaDAO(getActivity());

        PieEntry eApp = new PieEntry((float) receitaDAO.somarTotalCategoria(data,"Entrega Aplicativo"),"Entrega App");
        PieEntry ePaticular = new PieEntry((float) receitaDAO.somarTotalCategoria(data,"Entrega Particular"),"Entrega Particular");
        PieEntry corridaApp = new PieEntry((float) receitaDAO.somarTotalCategoria(data,"Corrida Aplicatico"),"Corrida App");
        PieEntry corridaParticular = new PieEntry((float) receitaDAO.somarTotalCategoria(data,"Corrida Particular"),"Corrida Particular");
        PieEntry gorjeta = new PieEntry((float) receitaDAO.somarTotalCategoria(data,"Gorjeta"),"Gorjeta");
        PieEntry outros = new PieEntry((float) receitaDAO.somarTotalCategoria(data,"outros"),"outros");


        if(eApp.getValue() != 0){
            receitas.add(eApp);
        }
        if(ePaticular.getValue() != 0){
            receitas.add(ePaticular);
        }

        if(corridaApp.getValue() != 0){
            receitas.add(corridaApp);

        } if(corridaParticular.getValue() != 0){
            receitas.add(corridaParticular);

        } if(gorjeta.getValue() != 0){
            receitas.add(gorjeta);

        } if(outros.getValue() != 0){
            receitas.add(outros);



            PieDataSet pieDataSet = new PieDataSet(receitas,"Receitas");
            pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
            pieDataSet.setValueTextColor(Color.WHITE);
            pieDataSet.setValueTextSize(20f);

            PieData pieData = new PieData(pieDataSet);

            graficoReceita.setData(pieData);
            graficoReceita.setEntryLabelColor(Color.BLACK);
            graficoReceita.setEntryLabelTextSize(10f);
            graficoReceita.getDescription().setEnabled(false);
            graficoReceita.setCenterTextColor(Color.BLACK);
            graficoReceita.setCenterText("Despesas");
            graficoReceita.setCenterTextSize(22f);
            graficoReceita.animate();

    }

    }


    @Override
    public void onStart() {
        super.onStart();
        listar();
    }
}