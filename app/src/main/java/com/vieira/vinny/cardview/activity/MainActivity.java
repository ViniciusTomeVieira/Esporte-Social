package com.vieira.vinny.cardview.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;
import com.ittianyu.bottomnavigationviewex.BottomNavigationViewEx;
import com.vieira.vinny.cardview.R;
import com.vieira.vinny.cardview.fragment.CriarPartidaFragment;
import com.vieira.vinny.cardview.fragment.FeedFragment;
import com.vieira.vinny.cardview.fragment.LojaFragment;
import com.vieira.vinny.cardview.fragment.PerfilFragment;
import com.vieira.vinny.cardview.fragment.TorneioFragment;
import com.vieira.vinny.cardview.helper.ConfiguracaoFirebase;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerPostagem;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configura Toolbar

        Toolbar toolbar = findViewById(R.id.toolbarPrincipal);
        toolbar.setTitle("Esporte Social"); // Titulo da toolbar
        toolbar.setLogo(R.drawable.ic_add_24dp);
        setSupportActionBar(toolbar);

        // Configurações de objetos
        autenticacao = ConfiguracaoFirebase.getFirebaseAutenticacao();

        // Configurar bottomNavigation
        configuraBottomNavigationView();
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.viewPager, new FeedFragment()).commit();


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
                    case R.id.ic_calendario: fragmentTransaction.replace(R.id.viewPager, new CriarPartidaFragment()).commit(); return true;
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


    public void selecionarTela(View view){
        view.setBackgroundColor(2);

    }
}
