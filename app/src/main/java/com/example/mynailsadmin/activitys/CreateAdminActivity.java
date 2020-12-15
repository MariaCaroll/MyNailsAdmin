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

    private EditText campoNome, campoEmail, campoSenha;
    private Button btCreateAdmin;


    private Usuario usuario;

    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_admin);

        carregarTelaCreteAdmin();


    }

    public void carregarTelaCreteAdmin() {
        campoNome = (EditText) findViewById(R.id.edtUsuarioAdmin);
        campoEmail = (EditText) findViewById(R.id.edtCreatLoginAdmin);
        campoSenha = (EditText) findViewById(R.id.editTextPasswordCreateAdmin);
        btCreateAdmin = (Button) findViewById(R.id.btnCreateAdmin);

        btCreateAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                autenticarDados();

            }
        });


    }

    public void autenticarDados() {
        String textoNome = campoNome.getText().toString();
        String textoEmail = campoEmail.getText().toString();
        String textoSenha = campoSenha.getText().toString();

        //validar campos vazios
        if (!textoNome.isEmpty()) {
            if (!textoEmail.isEmpty()) {
                if (!textoSenha.isEmpty()) {

                    usuario = new Usuario();
                    usuario.setNome(textoNome);
                    usuario.setEmail(textoEmail);
                    usuario.setSenha(textoSenha);
                    cadastrarUsuario();

                } else {
                    Toast.makeText(CreateAdminActivity.this, "Preencha a senha!", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(CreateAdminActivity.this, "Preencha o email!", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(CreateAdminActivity.this, "Preencha o nome!", Toast.LENGTH_SHORT).show();
        }
    }


    public void cadastrarUsuario() {
        autenticacao = ConfigFirebase.getReferenciaAutenticacao();
        autenticacao.createUserWithEmailAndPassword(
                usuario.getEmail(), usuario.getSenha()
        ).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CreateAdminActivity.this,
                            "Sucesso ao cadastrar usuário!",
                            Toast.LENGTH_SHORT).show();
                    limparCampos();
                } else {

                    String excecao = "";
                    try {
                        throw task.getException();
                    } catch (FirebaseAuthWeakPasswordException e) {
                        excecao = "Digite uma senha mais forte!";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        excecao = "Por favor, digite um e-mail válido";
                    } catch (FirebaseAuthUserCollisionException e) {
                        excecao = "Este conta já foi cadastrada";
                    } catch (Exception e) {
                        excecao = "Erro ao cadastrar usuário: " + e.getMessage();
                        e.printStackTrace();
                    }

                    Toast.makeText(CreateAdminActivity.this,
                            excecao,
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void limparCampos() {
        campoEmail.setHint("E-mail");
        campoNome.setHint("Usuário");
        campoSenha.setHint("Senha");
    }
}