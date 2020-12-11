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
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

public class LoginActivity extends AppCompatActivity {


    private EditText edEmailAdmin, edSenhaAdmin;
    private Button btEnviarLogin;
    private ProgressBar progressBar;

    private Usuario admin;

    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        veriicarUsuarioLogado();
         carregarComponentesLogin();
//
//        //Login usuário
       progressBar.setVisibility(View.GONE);
       btEnviarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
           public void onClick(View v) {

               String txtEmail = edEmailAdmin.getText().toString();
               String txtSenha = edSenhaAdmin.getText().toString();

                if (!txtEmail.isEmpty()){
                    if (!txtSenha.isEmpty()){

//                        //login mesmo
                        admin = new Usuario();
                        admin.setEmail(txtEmail);
                        admin.setSenha(txtSenha);
                        validarLogin();

                    }else {
                        Toast.makeText(LoginActivity.this,
                                "Preencha a Senha",
                                Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(LoginActivity.this,
                            "Preencha a E-mail",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
   }

    private void carregarComponentesLogin() {

       edEmailAdmin = (EditText) findViewById(R.id.edtLoginAdmin);
       edSenhaAdmin = (EditText) findViewById(R.id.editTextPasswordAdmin);
       btEnviarLogin = (Button) findViewById(R.id.btnAutenticarDadosAdmin);
        progressBar = (ProgressBar) findViewById(R.id.progressCreateAdmin);

   }
   public void veriicarUsuarioLogado() {
        autenticacao = ConfigFirebase.getReferenciaAutenticacao();
        if (autenticacao.getCurrentUser() != null) {
           startActivity(new Intent(getApplicationContext(), MainActivity.class));
           finish();

       }
   }

    public void validarLogin() {
        progressBar.setVisibility(View.VISIBLE);
        autenticacao = ConfigFirebase.getReferenciaAutenticacao();

        autenticacao.signInWithEmailAndPassword(
                admin.getEmail(),
                admin.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                progressBar.setVisibility(View.GONE);
                String excecao = "";
                try {
                    throw task.getException();
                }catch ( FirebaseAuthInvalidUserException e ) {
                    excecao = "Usuário não está cadastrado.";
                }catch ( FirebaseAuthInvalidCredentialsException e ){
                    excecao = "E-mail e senha não correspondem a um usuário cadastrado";
                }catch (Exception e){
                    excecao = "Erro ao cadastrar usuário: "  + e.getMessage();
                    e.printStackTrace();
                }

                Toast.makeText(LoginActivity.this,
                        excecao,
                        Toast.LENGTH_SHORT).show();
            }

        });

    }

    public void abrirTelaPrincipal() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
   }
}