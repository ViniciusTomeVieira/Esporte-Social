package com.vieira.vinny.cardview.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.vieira.vinny.cardview.R;
import com.vieira.vinny.cardview.adapter.PostagemAdapter;
import com.vieira.vinny.cardview.helper.ConfiguracaoFirebase;
import com.vieira.vinny.cardview.model.Postagem;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class FeedFragment extends Fragment {

    private RecyclerView recyclerPostagem;
    private List<Postagem> postagens = new ArrayList<>();
    private FirebaseAuth autenticacao;
    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        recyclerPostagem = container.findViewById(R.id.recyclerPostagem);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerPostagem.setLayoutManager(layoutManager);

        this.prepararPostagens();
        PostagemAdapter adapter = new PostagemAdapter(postagens);
        recyclerPostagem.setAdapter(adapter);



        return inflater.inflate(R.layout.fragment_feed, container, false);

    }

    public void prepararPostagens(){
        Postagem p = new Postagem("Vinicius Vieira","Vão td tnc porra",R.drawable.imagem1);
        this.postagens.add(p);

        p = new Postagem("Eduardo Woloszyn","Pau no cu do Mantau",R.drawable.imagem2);
        this.postagens.add(p);

        p = new Postagem("Kezia Melo","#procurandoumpaiprosmeusfilhos",R.drawable.imagem3);
        this.postagens.add(p);

        p = new Postagem("Kethryn Starke","Eu ainda naum sei screver",R.drawable.imagem4);
        this.postagens.add(p);

        p = new Postagem("Marcio José Mantau","Fazendo um projetinho",R.drawable.imagem2);
        this.postagens.add(p);
    }

}
