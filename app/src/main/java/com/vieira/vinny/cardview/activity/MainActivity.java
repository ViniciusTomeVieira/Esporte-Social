package com.vieira.vinny.cardview.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.vieira.vinny.cardview.R;
import com.vieira.vinny.cardview.adapter.PostagemAdapter;
import com.vieira.vinny.cardview.fragment.AgendaFragment;
import com.vieira.vinny.cardview.fragment.ConfigFragment;
import com.vieira.vinny.cardview.fragment.FeedFragment;
import com.vieira.vinny.cardview.fragment.LojaFragment;
import com.vieira.vinny.cardview.fragment.PerfilFragment;
import com.vieira.vinny.cardview.fragment.TorneioFragment;
import com.vieira.vinny.cardview.helper.ConfiguracaoFirebase;
import com.vieira.vinny.cardview.model.Postagem;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerPostagem;
    private List<Postagem> postagens = new ArrayList<>();
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configura Toolbar

        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("EsporteSocial"); // Titulo da toolbar
        setSupportActionBar(toolbar);

        // Configurações de objetos
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        // Configurar bottomNavigation
        configuraBottomNavigationView();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPager, new FeedFragment()).commit();








        recyclerPostagem = findViewById(R.id.recyclerPostagem);

        //Define layout
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerPostagem.setLayoutManager(layoutManager);

        //Define adapter
        this.prepararPostagens();
        PostagemAdapter adapter = new PostagemAdapter(postagens);
        recyclerPostagem.setAdapter(adapter);
    }
    private void configuraBottomNavigationView(){
        BottomNavigationViewEx bottomNavigationViewEx = findViewById(R.id.bottomNavigation);

        // faz configuraçõs inciais do bottomNavigation

        bottomNavigationViewEx.enableAnimation(true);
        bottomNavigationViewEx.enableItemShiftingMode(true);
        bottomNavigationViewEx.enableShiftingMode(true);
        bottomNavigationViewEx.setTextVisibility(true);

        // Habilitar navegação
        habilitarNavegacao(bottomNavigationViewEx);

    }

    // Criar eventos de clique no BottomNavigation

    private void habilitarNavegacao(BottomNavigationViewEx viewEx){
        viewEx.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                FragmentManager fragmentManager = getSupportFragmentManager();
                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                switch (menuItem.getItemId()){

                    case R.id.ic_home: fragmentTransaction.replace(R.id.viewPager, new FeedFragment()).commit(); return true;
                    case R.id.ic_calendario: fragmentTransaction.replace(R.id.viewPager, new AgendaFragment()).commit(); return true;
                    case R.id.ic_torneio: fragmentTransaction.replace(R.id.viewPager, new TorneioFragment()).commit(); return true;
                    case R.id.ic_loja: fragmentTransaction.replace(R.id.viewPager, new LojaFragment()).commit(); return true;
                    case R.id.ic_configuracoes: fragmentTransaction.replace(R.id.viewPager, new PerfilFragment()).commit(); return true;
                }

                return false;
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.menu_sair: deslogarUsuario();

        }

        return super.onOptionsItemSelected(item);
    }

    private void deslogarUsuario(){
        try{
            autenticacao.signOut();
            startActivity(new Intent(getApplicationContext(),LoginActivity.class));
        }catch(Exception e){
            e.printStackTrace();
        }

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

    public void selecionarTela(View view){
        view.setBackgroundColor(2);

    }
}
