package com.vieira.vinny.cardview.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.vieira.vinny.cardview.R;
import com.vieira.vinny.cardview.activity.CriarTorneio;
import com.vieira.vinny.cardview.activity.MainActivity;
import com.vieira.vinny.cardview.adapter.TorneioAdapter;
import com.vieira.vinny.cardview.helps.Helper;
import com.vieira.vinny.cardview.model.Postagem;
import com.vieira.vinny.cardview.model.Torneio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class TorneioFragment extends Fragment {
    private RecyclerView recyclerView;
    private List<Torneio> listTorneio ;
    private Torneio torneios;
    private EditText etBusca;
    private FloatingActionButton btnCriaPartida;
    private DatabaseReference reference ;
    private TorneioAdapter adapter;

    private ValueEventListener valueEventListenerTorneio;

    public TorneioFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        reference = FirebaseDatabase.getInstance().getReference();
        View view = inflater.inflate(R.layout.fragment_torneio, container, false);
        listTorneio = new ArrayList<Torneio>();
        initComponents(view);
        populaLista(view);
        loadManager(view);
        loadAdapter();
        // Inflate the layout for this fragment
        return view;
    }

    private void loadAdapter() {
        adapter = new TorneioAdapter(listTorneio, getActivity());
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onStart() {
        super.onStart();
        Helper.listar(valueEventListenerTorneio, reference, listTorneio, new Torneio(), adapter);
    }

    private void loadManager(View view) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
    }

    private void initComponents(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        btnCriaPartida = view.findViewById(R.id.cria_torneio);

        btnCriaPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent criarTorneio = new Intent(getContext(),CriarTorneio.class);
                startActivity(criarTorneio);
            }
        });
    }
    private void populaLista(View view){
        DatabaseReference torneio = reference.child("torneio");
        Query torneioPesquisa = torneio.orderByValue();
        torneioPesquisa.keepSynced(true);
        torneioPesquisa.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    Torneio t = new Torneio();
                    for ( DataSnapshot postSnapshot : dataSnapshot.getChildren()){
                        t = postSnapshot.getValue(Torneio.class);
                        listTorneio.add(t);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
