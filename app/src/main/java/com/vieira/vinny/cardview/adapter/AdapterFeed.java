package com.vieira.vinny.cardview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vieira.vinny.cardview.R;
import com.vieira.vinny.cardview.helper.UsuarioFirebase;
import com.vieira.vinny.cardview.model.Postagem;

import java.util.List;

public class AdapterFeed extends RecyclerView.Adapter<AdapterFeed.MyViewHolder>{

    private List<Postagem> postagens;
    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();
    private Context context;

    public AdapterFeed(List<Postagem> p, Context context ) {
        this.postagens = p;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.postagem_detalhe,viewGroup,false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
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

         TextView textNome;
         TextView textLocal;
         ImageView imagePostagem;
         TextView textDescricao;
         TextView textHorario;
         TextView textPessoas;

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




    }

