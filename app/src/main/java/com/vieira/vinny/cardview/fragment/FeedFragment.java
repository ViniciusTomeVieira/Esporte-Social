package com.vieira.vinny.cardview.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.vieira.vinny.cardview.R;
import com.vieira.vinny.cardview.adapter.AdapterFeed;
import com.vieira.vinny.cardview.helper.ConfiguracaoFirebase;
import com.vieira.vinny.cardview.helper.UsuarioFirebase;
import com.vieira.vinny.cardview.model.Postagem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {
    private RecyclerView recyclerFeed;
    private AdapterFeed adapterFeed;
    private List<Postagem> listaFeed = new ArrayList<>();
    private DatabaseReference feedRef = ConfiguracaoFirebase.getFireBase();
    private String idUsuarioLogado;
    protected DatabaseReference partidasRef = ConfiguracaoFirebase.getFireBase();
    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        //Configurações iniciais


        //Inicializar componentes
        recyclerFeed = view.findViewById(R.id.recyclerPostagem);

        //Configura recyclerview
        recyclerFeed.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        partidasRef = feedRef.child("partidas").child("partida");
        listaFeed = recuperaPostagens();
        adapterFeed = new AdapterFeed(listaFeed, getActivity() );
        recyclerFeed.setAdapter( adapterFeed );


        return view;

    }

    @Override
    public void onStart() {
        super.onStart();
        recuperaPostagens();
    }

    public List<Postagem> recuperaPostagens() {
        final List<Postagem> listaFeedInterna = new ArrayList<>();
        partidasRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds : dataSnapshot.getChildren()){
                    Postagem p = ds.getValue(Postagem.class);
                    listaFeedInterna.add(p);

                }
                


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        return listaFeedInterna;
    }



        }







