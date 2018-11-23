package com.vieira.vinny.cardview.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vieira.vinny.cardview.R;
import com.vieira.vinny.cardview.activity.CadastroActivity;
import com.vieira.vinny.cardview.activity.MainActivity;
import com.vieira.vinny.cardview.helper.UsuarioFirebase;
import com.vieira.vinny.cardview.model.Postagem;

/**
 * A simple {@link Fragment} subclass.
 */
public class CriarPartidaFragment extends Fragment {

    private TextInputEditText textLocal, textDescricao, textQuantidadeJog, textHorario;
    private Button botaoCriar;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private String nomeUsuario;
    private String emailUsuario;
    public ProgressBar progressBar;
    public CriarPartidaFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_criar_partida, container, false);

        textLocal = view.findViewById(R.id.textLocal);
        textDescricao = view.findViewById(R.id.textDescricao);
        textQuantidadeJog = view.findViewById(R.id.textQuantidadeJog);
        textHorario = view.findViewById(R.id.textHorario);
        progressBar = view.findViewById(R.id.progressBarPartida);
        progressBar.setVisibility(View.GONE);



        botaoCriar = view.findViewById(R.id.buttonCriar);
        botaoCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                progressBar.setVisibility(View.VISIBLE);
                //Recuperar dados do usuario
                FirebaseUser usuarioPerfil = UsuarioFirebase.getUsuarioAtual();
                nomeUsuario = usuarioPerfil.getDisplayName();
                emailUsuario = usuarioPerfil.getEmail();
                Postagem partida = new Postagem();
                String email = emailUsuario;
                String nome = nomeUsuario;
                String local = textLocal.getText().toString();
                String descricao = textDescricao.getText().toString();
                String quantidade = textQuantidadeJog.getText().toString();
                String horario = textHorario.getText().toString();

                partida.setEmail(email);
                partida.setNome(nome);
                partida.setLocal(local);
                partida.setDescricao(descricao);
                partida.setQuantidadeJogadores(quantidade);
                partida.setHorario(horario);

                try{
                    if(local != null && descricao != null && quantidade != null && horario != null){
                        progressBar.setVisibility(View.GONE);
                        referencia.child("partidas").child(usuarioPerfil.getUid()).setValue(partida);
                        Toast.makeText(getActivity(), "Partida cadastrada!", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getActivity(),MainActivity.class));
                    }else{
                        Toast.makeText(getActivity(), "Algum campo não foi preenchido!", Toast.LENGTH_SHORT).show();
                    }
                }catch(Exception e){
                    startActivity(new Intent(getActivity(),MainActivity.class));

                }
            }
        });






        return view;
    }



}
