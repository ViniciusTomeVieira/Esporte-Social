package com.vieira.vinny.cardview.helper;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;
import com.vieira.vinny.cardview.model.Usuario;

public class UsuarioFirebase {

    public static FirebaseUser getUsuarioAtual(){
        FirebaseAuth usuario = ConfiguracaoFirebase.getFirebaseAutenticacao();
        return usuario.getCurrentUser();
    }

    public static void atualizarNomeUsuario(String nome){
        try{
            //Usuario logado no app
            FirebaseUser usuarioLogado = getUsuarioAtual();

            //Configurar objeto para alteração do perfil
            UserProfileChangeRequest profile = new UserProfileChangeRequest.Builder().setDisplayName(nome).build();

            usuarioLogado.updateProfile(profile).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(!task.isSuccessful()){
                        Log.d("Perfil","Erro ao atualizar nome de perfil");
                    }
                }
            });
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public static Usuario getDadosUsuarioLogado(){

        FirebaseUser firebaseUser = getUsuarioAtual();

        Usuario usuario = new Usuario();
        usuario.setEmail(firebaseUser.getEmail());
        usuario.setNome(firebaseUser.getDisplayName());
        usuario.setId(firebaseUser.getUid());

        if(firebaseUser.getPhotoUrl() == null){
            usuario.setCaminhoFoto("");
        }else{
            usuario.setCaminhoFoto(firebaseUser.getPhotoUrl().toString());
        }
        return  usuario;
    }

    public static String getIdentificadorUsuario(){
        return getUsuarioAtual().getUid();
    }
}
