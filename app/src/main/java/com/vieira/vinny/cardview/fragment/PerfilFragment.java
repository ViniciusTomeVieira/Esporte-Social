package com.vieira.vinny.cardview.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.vieira.vinny.cardview.R;
import com.vieira.vinny.cardview.activity.EditarPerfilActivity;
import com.vieira.vinny.cardview.helper.UsuarioFirebase;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PerfilFragment extends Fragment {
        private ProgressBar progressBar;
        private CircleImageView imagePerfil;
        private TextView textNivel, textAmigos, textPartidas, textNomeUsuario;
        private Button buttonEditarPerfil, buttonAbrirLoja;

    public PerfilFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_perfil, container, false);

        // Configuração dos componentes

        progressBar = view.findViewById(R.id.progressBarPerfil);
        imagePerfil = view.findViewById(R.id.imagePerfil);
        textNivel = view.findViewById(R.id.textNivel);
        textAmigos = view.findViewById(R.id.textAmigos);
        textPartidas = view.findViewById(R.id.textPartidas);
        buttonEditarPerfil = view.findViewById(R.id.buttonEditarPerfil);
        buttonAbrirLoja = view.findViewById(R.id.buttonAbrirLoja);
        textNomeUsuario = view.findViewById(R.id.textNomeUsuario);

        FirebaseUser usuarioPerfil = UsuarioFirebase.getUsuarioAtual();
        textNomeUsuario.setText(usuarioPerfil.getDisplayName());

        // Abre a edição de perfil

        buttonEditarPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), EditarPerfilActivity.class);
                startActivity(i);
            }
        });

        buttonAbrirLoja.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), LojaFragment.class);
                startActivity(i);
            }
        });

        return view;
    }

}
