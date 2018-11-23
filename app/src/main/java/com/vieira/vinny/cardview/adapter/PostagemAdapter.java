package com.vieira.vinny.cardview.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vieira.vinny.cardview.R;
import com.vieira.vinny.cardview.helper.UsuarioFirebase;
import com.vieira.vinny.cardview.model.Postagem;

import java.util.List;

public class PostagemAdapter extends RecyclerView.Adapter<PostagemAdapter.MyViewHolder>{

    private List<Postagem> postagens;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    public PostagemAdapter(List<Postagem> p ) {
        this.postagens = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.postagem_detalhe,viewGroup,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        testePostagem();
        Postagem postagem = postagens.get(i);
        myViewHolder.textNome.setText(postagem.getNome());
        myViewHolder.textLocal.setText(postagem.getLocal());
        myViewHolder.textDescricao.setText(postagem.getDescricao());
        myViewHolder.textHorario.setText(postagem.getHorario());
        myViewHolder.textPessoas.setText(postagem.getQuantidadeJogadores());
        myViewHolder.imagePostagem.setImageResource(postagem.getImagem());
    }

    @Override
    public int getItemCount() {
        return postagens.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        private TextView textNome;
        private TextView textLocal;
        private ImageView imagePostagem;
        private TextView textDescricao;
        private TextView textHorario;
        private TextView textPessoas;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textNome = itemView.findViewById(R.id.textNome);
            textLocal = itemView.findViewById(R.id.textLocal);
            textDescricao = itemView.findViewById(R.id.textDescricao);
            textHorario = itemView.findViewById(R.id.textHorario);
            textPessoas = itemView.findViewById(R.id.textPessoas);
            imagePostagem = itemView.findViewById(R.id.imagePostagem);
        }
    }

    public void recuperaPostagens(){
        FirebaseUser usuarioPerfil = UsuarioFirebase.getUsuarioAtual();
        DatabaseReference postaagensBanco = referencia.child("partidas").child(usuarioPerfil.getUid());
        postaagensBanco.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Postagem post = dataSnapshot.getValue(Postagem.class);
                postagens.add(post);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

            }

        public void testePostagem(){

            for (int i = 0; i < 6; i++) {
                Postagem p = new Postagem();
                p.setNome("Clovis");
                p.setDescricao("Bora quebrar td nesse caralho");
                p.setHorario("12:00");
                p.setQuantidadeJogadores("55");
                p.setLocal("Na rua mesmo");
                p.setImagem(2);

                postagens.add(i,p);
            }

        }
    }

