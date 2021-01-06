package com.example.mynailsadmin.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.mynailsadmin.R;
import com.example.mynailsadmin.helper.ConfigFirebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity  {


    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);

      //verificarUsuarioLogado();
        //logado();

    }
  /*  @Override
    protected void onStart() {
        super.onStart();
        verificarUsuarioLogado();
        //logado();*/


/*    }

    public void logado() {
        FirebaseUser currentUser = autenticacao.getCurrentUser();
        if (currentUser != null ){
            abrirTelaPrincipal();
        } else {
            abriLogin() ;
        }


    }*/
 /*   public void verificarUsuarioLogado(){
        autenticacao = ConfigFirebase.getReferenciaAutenticacao();

        if( autenticacao.getCurrentUser() != null ){
            abrirTelaPrincipal();
        }
    }*/


    public void abrirTelaLogin(View view){

        startActivity(new Intent(this, LoginActivity.class));
        /*Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity( i );*/

    }

    public void abriLogin() {
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity( i );
    }

    public void abrirTelaAbout(View view){

        Intent i = new Intent(MainActivity.this, AboutUsActivity.class);
        startActivity( i );

    }

  /*  public void abrirTelaPrincipal(){

        startActivity(new Intent(this, PrincipalActivity.class));
    }*/



}