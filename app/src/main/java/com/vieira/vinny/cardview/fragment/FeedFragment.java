package com.vieira.vinny.cardview.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.google.firebase.auth.FirebaseUser;
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
    private ValueEventListener valueEventListenerFeed;
    private DatabaseReference feedRef;
    private String idUsuarioLogado;
    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        //Configurações iniciais
        idUsuarioLogado = UsuarioFirebase.getIdentificadorUsuario();
        feedRef = ConfiguracaoFirebase.getFireBase()
                .child("partidas")
                .child( idUsuarioLogado );

        //Inicializar componentes
        recyclerFeed = view.findViewById(R.id.recyclerPostagem);

        //Configura recyclerview
        adapterFeed = new AdapterFeed(listaFeed, getActivity() );
        recyclerFeed.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        recyclerFeed.setAdapter( adapterFeed );

        return view;

    }






        public void recuperaPostagens(){
            FirebaseUser usuarioPerfil = UsuarioFirebase.getUsuarioAtual();
            DatabaseReference postaagensBanco = feedRef;
            postaagensBanco.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for( DataSnapshot ds : dataSnapshot.getChildren() ){
                        Postagem p = ds.getValue(Postagem.class);
                        listaFeed.add(p);
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
                }


        }


    




