package com.vieira.vinny.cardview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.vieira.vinny.cardview.R;
import com.vieira.vinny.cardview.model.Torneio;

import java.util.List;

public class TorneioAdapter extends RecyclerView.Adapter<TorneioAdapter.MyViewAdapter> {
    private List<Torneio> listaTorneio;
    private Context context;
    private DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Torneio");

    public TorneioAdapter(List<Torneio> l, Context context) {
        listaTorneio = l;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View itemLista = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_torneio,viewGroup,false);
        Button btn_inscrever = itemLista.findViewById(R.id.btn_inscrever);
        btn_inscrever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Você está inscrito",Toast.LENGTH_SHORT).show();
            }
        });
        return new TorneioAdapter.MyViewAdapter(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewAdapter myViewAdapter, int i) {
        Torneio torneio = listaTorneio.get(i);
        myViewAdapter.titulo.setText(torneio.getTitulo());
        myViewAdapter.inscritos.setText(torneio.getInscritos());
        myViewAdapter.cidade.setText(torneio.getCidade());
        myViewAdapter.local.setText(torneio.getLocal());
        myViewAdapter.taxa.setText(torneio.getTaxa());
    }


    @Override
    public int getItemCount() {
        return listaTorneio.size();
    }

    public class MyViewAdapter extends RecyclerView.ViewHolder {
        private TextView titulo;
        private TextView cidade;
        private TextView taxa;
        private TextView local;
        private TextView inscritos;
        public MyViewAdapter(@NonNull View itemView) {
            super(itemView);
            titulo        = itemView.findViewById(R.id.tv_titulo);
            cidade        = itemView.findViewById(R.id.tv_local);
            taxa          = itemView.findViewById(R.id.tv_taxa);
            local         = itemView.findViewById(R.id.tv_localizacao);
            inscritos     = itemView.findViewById(R.id.tv_participantes);
        }
    }
}