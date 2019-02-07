package com.vieira.vinny.cardview.activity;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    DatabaseReference reference = FirebaseDatabase.getInstance().getReference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criar_torneio);
    }

    public void criarTorneio(View view) {
        etNome = findViewById(R.id.nome_torneio);
        etCidade = findViewById(R.id.cidade);
        etTaxa = findViewById(R.id.taxa_inscricao);
        etLocal = findViewById(R.id.local);
        etTimesInscritos = findViewById(R.id.participantes);
        etModalidade = findViewById(R.id.modalidade);
        btnCriar = findViewById(R.id.btn_criar);
        int imagemTorneio = R.id.ic_torneio;

        torneio = new Torneio(etNome.getText().toString(),etCidade.getText().toString(),etTaxa.getText().toString(),etLocal.getText().toString()
        ,etTimesInscritos.getText().toString(),imagemTorneio,etModalidade.getText().toString());
        reference.child("torneio").push().setValue(torneio);

        btnCriar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference torneios = reference.child("torneio");
                torneios.push().setValue("Vo ve e te aviso");
            }
        });
    }

}
