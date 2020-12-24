package com.example.mynailsadmin.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.mynailsadmin.R;
import com.example.mynailsadmin.helper.ConfigFirebase;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity  {


    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      setContentView(R.layout.activity_main);


    }



    public void abrirTelaLogin(View view){

        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity( i );

    }

    public void abrirTelaAbout(View view){

        Intent i = new Intent(MainActivity.this, AboutUsActivity.class);
        startActivity( i );

    }

}