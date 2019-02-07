package com.vieira.vinny.cardview.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vieira.vinny.cardview.R;
import com.vieira.vinny.cardview.activity.CriarTorneio;
import com.vieira.vinny.cardview.activity.MainActivity;
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
    private Torneio torneios;
    private EditText etBusca;
    private Button btnCriaPartida;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    public TorneioFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_torneio, container, false);
        initComponents(view);
//        populaLista();
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
        btnCriaPartida = view.findViewById(R.id.cria_torneio);

        btnCriaPartida.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent criarTorneio = new Intent(getContext(),CriarTorneio.class);
                startActivity(criarTorneio);
            }
        });
    }

    private void populaLista(){
        DatabaseReference torneio = reference.child("torneio");
        torneio.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   torneios = dataSnapshot.getValue(Torneio.class);
                   listTorneio.add(torneios);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
