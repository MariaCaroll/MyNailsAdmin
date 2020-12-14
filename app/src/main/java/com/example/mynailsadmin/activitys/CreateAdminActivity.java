package com.example.mynailsadmin.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

public class CreateAdminActivity extends AppCompatActivity {

    private EditText edUsuarioAdmin, edEmailAdminCreate, edSenhaAdminCreate;
    private Button btCreateAdmin;
    private ProgressBar progressBar;

    private Usuario admin;

    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_admin);

        carregarTelaCreteAdmin();



    }

    public void carregarTelaCreteAdmin(){
        edUsuarioAdmin = (EditText) findViewById(R.id.edtUsuarioAdmin);
        edEmailAdminCreate = (EditText) findViewById(R.id.edtCreatLoginAdmin);
        edSenhaAdminCreate = (EditText) findViewById(R.id.editTextPasswordCreateAdmin);
        btCreateAdmin = (Button) findViewById(R.id.btnCreateAdmin);
        progressBar = (ProgressBar) findViewById(R.id.progressCreateAdmin);

    }
}