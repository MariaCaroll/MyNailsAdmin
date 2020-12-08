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

        //CADASTRAR USUARIO
        progressBar.setVisibility(View.GONE);
        btCreateAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoNome = edUsuarioAdmin.getText().toString();
                String textoEmail = edEmailAdminCreate.getText().toString();
                String textoSenha = edSenhaAdminCreate.getText().toString();

                if (!textoNome.isEmpty()){
                    if (!textoEmail.isEmpty()){
                        if (!textoSenha.isEmpty()){

                            //cadastra o usuário mesmo

                            admin = new Usuario();
                            admin.setNome(textoNome);
                            admin.setEmail(textoEmail);
                            admin.setSenha(textoSenha);
                            cadastrarAdmin( admin);

                        }else {
                            Toast.makeText(CreateAdminActivity.this,
                                    "Preencha a Senha",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }else {
                        Toast.makeText(CreateAdminActivity.this,
                                "Preencha o E-mail",
                                Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(CreateAdminActivity.this,
                            "Preencha o Usuário",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void cadastrarAdmin(Usuario admin){
        progressBar.setVisibility(View.VISIBLE);
        autenticacao = ConfigFirebase.getReferenciaAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                admin.getEmail(),
                admin.getSenha()
        ).addOnCompleteListener(
                this,
                new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if( task.isSuccessful()) {

                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(CreateAdminActivity.this,
                                    "Cadastrado com sucesso!!",
                                    Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            finish();
                        }else {
                            progressBar.setVisibility(View.GONE);

                            String erroExcecao = "";
                            try{
                                throw task.getException();
                            }catch (FirebaseAuthWeakPasswordException e) {
                                erroExcecao = " Digite uma senha mais forte!";
                            }catch  (FirebaseAuthInvalidCredentialsException e) {
                                erroExcecao = " Por favor, digite um e-mail válido";
                            }catch (FirebaseAuthUserCollisionException e ) {
                                erroExcecao = "Esta conta já foi cadastrada";
                            }catch (Exception e) {
                                erroExcecao = "ao cadastrar usuário: " + e.getMessage();
                                e.printStackTrace();
                            }

                            Toast.makeText( CreateAdminActivity.this,
                                    "Erro:" + erroExcecao,
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );


    }

    public void carregarTelaCreteAdmin(){
        edUsuarioAdmin = (EditText) findViewById(R.id.edtUsuarioAdmin);
        edEmailAdminCreate = (EditText) findViewById(R.id.edtCreatLoginAdmin);
        edSenhaAdminCreate = (EditText) findViewById(R.id.editTextPasswordCreateAdmin);
        btCreateAdmin = (Button) findViewById(R.id.btnCreateAdmin);
        progressBar = (ProgressBar) findViewById(R.id.progressCreateAdmin);

    }
}