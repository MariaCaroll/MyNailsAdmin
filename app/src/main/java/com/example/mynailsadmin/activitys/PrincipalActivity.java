package com.example.mynailsadmin.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynailsadmin.R;

public class PrincipalActivity extends AppCompatActivity {
    private Button btCreateProf, btEditProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.acticity_principal);
        carregarComponentes();

    }

    public  void carregarComponentes ( ) {

        btCreateProf = (Button) findViewById(R.id.btnCreateProf);

        btEditProf = (Button) findViewById(R.id.btnEdiProf);

        //entrar na tela de criar perfil de profissional

        btCreateProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), CreateProfActivity.class));
                finish();
            }
        });

        //entrar na tela de editar perfil de profissional
        btEditProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                finish();
            }
        });
    }

}
