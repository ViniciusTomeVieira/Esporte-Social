package com.vieira.vinny.cardview.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class TorneioAdapter extends RecyclerView.Adapter<TorneioAdapter.MyViewAdapter> {

    public TorneioAdapter() {
    }

    @NonNull
    @Override
    public MyViewAdapter onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewAdapter myViewAdapter, int i) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class MyViewAdapter extends RecyclerView.ViewHolder {

        public MyViewAdapter(@NonNull View itemView) {
            super(itemView);
        }
    }
}
