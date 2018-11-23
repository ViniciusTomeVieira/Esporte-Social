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
    public FeedFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_feed, container, false);

        recyclerPostagem = view.findViewById(R.id.recyclerPostagem);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerPostagem.setLayoutManager(layoutManager);
        PostagemAdapter adapter = new PostagemAdapter(postagens);
        recyclerPostagem.setAdapter(adapter);


        return view;

    }



}
