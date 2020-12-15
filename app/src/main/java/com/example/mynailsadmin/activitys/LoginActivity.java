package com.example.mynailsadmin.activitys;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mynailsadmin.R;
import com.example.mynailsadmin.helper.ConfigFirebase;
import com.example.mynailsadmin.model.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {


    private EditText edEmailAdmin, edSenhaAdmin;
    private Button btEnviarLogin;
    private TextView criarAdmin;

    private Usuario admin;

    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        carregarComponentes();


    }

    public  void carregarComponentes() {

        criarAdmin = findViewById(R.id.txtCreateNewAdmin);


    }

    public void abrirCadastro(View view){
        Intent i = new Intent(LoginActivity.this, CreateAdminActivity.class);
        startActivity( i );
    }
}