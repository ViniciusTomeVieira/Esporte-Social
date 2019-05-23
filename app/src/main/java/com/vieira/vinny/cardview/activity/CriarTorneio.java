package com.vieira.vinny.cardview.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.vieira.vinny.cardview.R;
import com.vieira.vinny.cardview.model.Torneio;

public class CriarTorneio extends AppCompatActivity {

    private EditText etNome;
    private EditText etCidade;
    private EditText etTaxa;
    private EditText etLocal;
    private EditText etTimesInscritos;
    private EditText etModalidade;
    private Torneio torneio;
    private Button btnCriar;
    private Intent torneioFragment;
    private int imagemTorneio;
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_torneio);
        initialize();
        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                torneio = new Torneio(etNome.getText().toString(), etCidade.getText().toString(),etTaxa.getText().toString(),etLocal.getText().toString()
                        ,etTimesInscritos.getText().toString(), imagemTorneio, etModalidade.getText().toString());
                DatabaseReference torneios = reference.child("torneio");
                torneios.push().setValue(torneio);
                torneios.push().addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Toast.makeText(CriarTorneio.this, "Torneio Criado com sucesso", Toast.LENGTH_SHORT).show();
                        torneioFragment = new Intent(view.getContext(), MainActivity.class);
                        startActivity(torneioFragment);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Toast.makeText(CriarTorneio.this, "Houve um erro ao criar um novo Torneio", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    public void initialize(){
        etNome = findViewById(R.id.nome_torneio);
        etCidade = findViewById(R.id.cidade);
        etTaxa = findViewById(R.id.taxa_inscricao);
        etLocal = findViewById(R.id.local);
        etTimesInscritos = findViewById(R.id.participantes);
        etModalidade = findViewById(R.id.modalidade);
        btnCriar = findViewById(R.id.btn_criar);
        imagemTorneio = R.id.ic_torneio;
    }

    public void criarTorneio(View view) {
        initialize();
    }

}