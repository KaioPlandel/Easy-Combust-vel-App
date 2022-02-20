package com.plandel.easyCombustivel.view;

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

import com.plandel.easyCombustivel.Helper.AdapterDespesa;
import com.plandel.easyCombustivel.Helper.DateCustom;
import com.plandel.easyCombustivel.Helper.DespesaDAO;
import com.plandel.easyCombustivel.Helper.RecyclerItemClickListener;

import com.plandel.easyCombustivel.R;
import com.plandel.easyCombustivel.model.Despesa;
import com.plandel.easyCombustivel.model.Receita;
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
 * Use the {@link DespesaRelatorioFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DespesaRelatorioFragment extends Fragment {

    private List<Receita> lista = new ArrayList<>();
    private RecyclerView recycleViewRelatorio;
    private DespesaDAO despesaDAO;
    private List<Despesa> listaDespesa = new ArrayList<>();
    private PieChart pieChart;
    private MaterialCalendarView calendar;
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
        pieChart = view.findViewById(R.id.grafico);

        calendar = view.findViewById(R.id.calendar);
        calendar.setTitleMonths(DateCustom.getNomeMeses());

        //Gerando grafico com a data atual
        String mes =  String.valueOf(calendar.getCurrentDate().getMonth());
        String ano = String.valueOf(calendar.getCurrentDate().getYear());
         gerarGrafico(DateCustom.formatarMesAno(mes,ano));

        calendar.setOnMonthChangedListener(new OnMonthChangedListener() {
            @Override
            public void onMonthChanged(MaterialCalendarView widget, CalendarDay date) {

                String mes = String.valueOf(date.getMonth());
                String ano = String.valueOf(date.getYear());

                //apresentar grafico
                gerarGrafico(DateCustom.formatarMesAno(mes,ano));
                pieChart.setVisibility(View.VISIBLE);

            }
        });




        recycleViewRelatorio.addOnItemTouchListener(new RecyclerItemClickListener(getActivity(), recycleViewRelatorio, new RecyclerItemClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                //edição envia a despesa para a activity de edição
                List<Despesa> list = new ArrayList<>();
                list = despesaDAO.listar();
                Despesa despesaSelecionada = list.get(position);

                Intent intent = new Intent(getActivity(),DespesaActivity.class);
                intent.putExtra("pacoteDespesa",despesaSelecionada);
                startActivity(intent);
            }

            @Override
            public void onLongItemClick(View view, int position) {

                //deletar despesa
                listaDespesa = despesaDAO.listar();
                Despesa despesaSelecionada = listaDespesa.get(position);


                AlertDialog.Builder dialog = new AlertDialog.Builder(getActivity())
                        .setTitle("Apagar Gasto")
                        .setMessage("Tem certeza que deseja apagar este lançamento ?")
                        .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                if(despesaDAO.deletar(despesaSelecionada)){
                                    Toast.makeText(getActivity(),"Sucesso ao apagar.",Toast.LENGTH_SHORT).show();
                                    carregarListaDespesa();
                                    gerarGrafico(DateCustom.formatarMesAno(mes,ano));

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

    private void carregarListaDespesa(){
        listaDespesa = despesaDAO.listar();

        //criar adapter
        AdapterDespesa adapterDespesa = new AdapterDespesa(listaDespesa);



        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recycleViewRelatorio.setHasFixedSize(true);
        recycleViewRelatorio.addItemDecoration(new DividerItemDecoration(getActivity(), LinearLayout.VERTICAL));
        recycleViewRelatorio.setLayoutManager(layoutManager);
        recycleViewRelatorio.setAdapter(adapterDespesa);


    }

    public void gerarGrafico(String data){

        //Criando grafico
        ArrayList<PieEntry> despesas = new ArrayList<>();

        //recuperando despesas do banco e apresentando no grafico de acordo com a data
        DespesaDAO despesaDAO = new DespesaDAO(getActivity());

        PieEntry combustivel = new PieEntry((float) despesaDAO.somarTotalCategoria(data,"Combustível"),"Combustível");
        PieEntry manutencao = new PieEntry((float) despesaDAO.somarTotalCategoria(data,"Manutencao"),"Manutenção");
        PieEntry pneus = new PieEntry((float) despesaDAO.somarTotalCategoria(data,"Pneus"),"Pneus");
        PieEntry pintura = new PieEntry((float) despesaDAO.somarTotalCategoria(data,"Pintura"),"Pintura");
        PieEntry ipva = new PieEntry((float) despesaDAO.somarTotalCategoria(data,"IPVA"),"IPVA");
        PieEntry multa = new PieEntry((float) despesaDAO.somarTotalCategoria(data,"Multa"),"Multa");
        PieEntry financiamento = new PieEntry((float) despesaDAO.somarTotalCategoria(data,"Financiamento"),"Financiamento");
        PieEntry lavagem = new PieEntry((float) despesaDAO.somarTotalCategoria(data,"Lavagem"),"Lavagem");
        PieEntry seguro = new PieEntry((float) despesaDAO.somarTotalCategoria(data,"Seguro"),"Seguro");
        PieEntry oleo = new PieEntry((float) despesaDAO.somarTotalCategoria(data,"Óleo"),"Óleo");
        PieEntry outros = new PieEntry((float) despesaDAO.somarTotalCategoria(data,"Outros"),"Outros");

        if(combustivel.getValue() != 0){
            despesas.add(combustivel);
        }
        if(oleo.getValue() != 0){
            despesas.add(oleo);
        }
        if(outros.getValue() != 0){
            despesas.add(outros);
        }
        if(manutencao.getValue() != 0){
            despesas.add(manutencao);
        }

        if(pneus.getValue() != 0){
            despesas.add(pneus);

        } if(pintura.getValue() != 0){
            despesas.add(pintura);

        } if(ipva.getValue() != 0){
            despesas.add(ipva);

        } if(multa.getValue() != 0){
            despesas.add(multa);

        } if(financiamento.getValue() != 0){
            despesas.add(financiamento);

        } if(lavagem.getValue() != 0){
            despesas.add(lavagem);

        } if(seguro.getValue() != 0){
            despesas.add(seguro);
        }


        PieDataSet pieDataSet = new PieDataSet(despesas,"Gastos");
        pieDataSet.setColors(ColorTemplate.COLORFUL_COLORS);
        pieDataSet.setValueTextColor(Color.WHITE);
        pieDataSet.setValueTextSize(20f);

        PieData pieData = new PieData(pieDataSet);

        pieChart.setData(pieData);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(10f);
        pieChart.getDescription().setEnabled(false);
        pieChart.setCenterTextColor(Color.BLACK);
        pieChart.setCenterText("Despesas");
        pieChart.setCenterTextSize(22f);
        pieChart.calculateOffsets();
        pieChart.animate();

    }

    @Override
    public void onStart() {
        super.onStart();
        carregarListaDespesa();
    }
}