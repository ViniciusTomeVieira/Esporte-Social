package com.vieira.vinny.cardview.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.vieira.vinny.cardview.R;
import com.vieira.vinny.cardview.adapter.TorneioAdapter;
import com.vieira.vinny.cardview.model.Torneio;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TorneioFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Torneio> listTorneio = new ArrayList<Torneio>();
    private Torneio torneio;

    public TorneioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_torneio, container, false);
        initComponents(view);
        populaLista();
        loadManager(view);
        loadAdapter();
        // Inflate the layout for this fragment
        return view;
    }

    private void loadAdapter() {
        TorneioAdapter adapter = new TorneioAdapter(listTorneio, getActivity());
        recyclerView.setAdapter(adapter);
    }

    private void loadManager(View view) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
    }

    private void initComponents(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
    }

    private void populaLista(){
        torneio = new Torneio("Valendo uma caixa de Schin","São Valentim - RS",
                "R$1.500,00","Bairro Terra a Vista", "10/16" , R.drawable.common_google_signin_btn_icon_dark);
        listTorneio.add(torneio);
        torneio = new Torneio("Valendo uma caixa de Brahma","Gaurama - RS",
                "R$1.500,00","Bairro Tijolo a Vista", "10/16" , R.drawable.common_google_signin_btn_icon_dark);
        listTorneio.add(torneio);
        torneio = new Torneio("Valendo um Leitão","Ibirama - SC",
                "R$1.500,00","Bairro Bila a Vista", "10/16" , R.drawable.common_google_signin_btn_icon_dark);
        listTorneio.add(torneio);
        torneio = new Torneio("Valendo uma Vaca","Campos Novos - SC",
                "R$1.500,00","Bairro Borra a Vista", "10/16" , R.drawable.common_google_signin_btn_icon_dark);
        listTorneio.add(torneio);
        torneio = new Torneio("Valendo um cachorro","Imbituba - SC",
                "R$1.500,00","Bairro Ban a Vista", "10/16" , R.drawable.common_google_signin_btn_icon_dark);
        listTorneio.add(torneio);
    }
}
