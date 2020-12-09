package com.example.mynailsadmin.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mynailsadmin.ProfissionaisActivity;
import com.example.mynailsadmin.R;
import com.example.mynailsadmin.helper.ConfigFirebase;
import com.example.mynailsadmin.model.model.Profissionais;
import com.example.mynailsadmin.model.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.database.DatabaseReference;

public class CreateProfActivity extends AppCompatActivity {

    private EditText edNome, edSobrenome,edFantasia, edTelefone, edWhats;
    private EditText edEstado, edCidade, edLogradouro, edNumero, edBairro, edCep;
    private EditText edMinimo, edMaximo,  edModalidade;
    private Spinner spTipoProfissional, spModoAtender;
    private Button btCreateProfissional;
    private ProgressBar progressBar;

    private Profissionais prof;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_prof);
        carregarTelaCreateProf();

        //CADASTRAR PROFISSIONAL
        progressBar.setVisibility(View.GONE);
        btCreateProfissional.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String textoNome = edNome.getText().toString();
                String textoSobrenome = edSobrenome.getText().toString();
                String textoFantasia = edFantasia.getText().toString();
                String textotelefone = edTelefone.getText().toString();
                String textoWhats = edWhats.getText().toString();
                double textoMinimo = Double.parseDouble(String.valueOf(edMinimo));
                double textoMaximo = Double.parseDouble(String.valueOf(edMaximo));
                String textoEstado = edEstado.getText().toString();
                String textoCidade = edCidade.getText().toString();
                String textoLogradouro = edLogradouro.getText().toString();
                String textoNumero = edNumero.getText().toString();
                String textoBairro = edBairro.getText().toString();
                String textoCep = edCep.getText().toString();
                String textoModalidade = edModalidade.getText().toString();
                String textoTipoProfi = spTipoProfissional.getSelectedItem().toString();
                String textoModoAtender = spModoAtender.getSelectedItem().toString();


                if (!(textoNome.isEmpty() || textoSobrenome.isEmpty() || textoFantasia.isEmpty() ||
                        textoWhats.isEmpty() || textotelefone.isEmpty() || textoEstado.isEmpty() ||
                        textoCep.isEmpty() || textoCidade.isEmpty() || textoLogradouro.isEmpty() ||
                        textoNumero.isEmpty() || textoBairro.isEmpty() || textoNumero.isEmpty() ||
                        textoBairro.isEmpty() || textoTipoProfi.isEmpty() || textoModoAtender.isEmpty() ||
                        textoModalidade.isEmpty()))
                {

                    prof = new Profissionais();
                    prof.setNome(textoNome);
                    prof.setSobreome(textoSobrenome);
                    prof.setNome_fantasia(textoFantasia);
                    prof.setTelenone(textotelefone);
                    prof.setWhats(textoWhats);
                    prof.setValor_maximo(textoMaximo);
                    prof.setValor_minimo(textoMinimo);
                    prof.setEndereco_uf(textoEstado);
                    prof.setEndereco_rua(textoLogradouro);
                    prof.setEndereco_numero(textoNumero);
                    prof.setEndereco_bairro(textoBairro);
                    prof.setEndereco_cep(textoCep);
                    prof.setModalidades(textoModalidade);
                    prof.setTipo_profissional(textoTipoProfi);
                    prof.setTipo_atendimento(textoModoAtender);


                }else {
                    Toast.makeText(CreateProfActivity.this,
                            "Preencha os Campos Corretamente! ",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    public void cadastrarProf(Profissionais prof){
        progressBar.setVisibility(View.VISIBLE);
        reference = ConfigFirebase.getFirebase();
//        reference.
//                (prof.getNome(), prof.getSobreome(), prof.getNome_fantasia(), prof.getTelenone(), prof.getWhats(), prof.getEndereco_cidade(), prof.getEndereco_bairro(), prof.getEndereco_numero(), prof.getEndereco_cep(), prof.getEndereco_rua(), prof.getModalidades(), prof.getTipo_atendimento(), prof.getTipo_profissional(), prof.getValor_maximo(), prof.getValor_minimo()).addContentView(
//                this,
//                new OnCompleteListener<AuthResult>() {
//                    @Override
//                    public void onComplete(@NonNull Task<AuthResult> task) {
//                        if( task.isSuccessful()) {
//
//                            progressBar.setVisibility(View.GONE);
//                            Toast.makeText(CreateProfActivity.this,
//                                    "Cadastrado com sucesso!!",
//                                    Toast.LENGTH_SHORT).show();
//                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
//                            finish();
//                        }else {
//                            progressBar.setVisibility(View.GONE);
//
//                            String erroExcecao = "";
//                            try{
//                                throw task.getException();
//                            }catch (FirebaseAuthWeakPasswordException e) {
//                                erroExcecao = " Digite uma senha mais forte!";
//                            }catch  (FirebaseAuthInvalidCredentialsException e) {
//                                erroExcecao = " Por favor, digite um e-mail válido";
//                            }catch (FirebaseAuthUserCollisionException e ) {
//                                erroExcecao = "Esta conta já foi cadastrada";
//                            }catch (Exception e) {
//                                erroExcecao = "ao cadastrar usuário: " + e.getMessage();
//                                e.printStackTrace();
//                            }
//
//                            Toast.makeText( CreateAdminActivity.this,
//                                    "Erro:" + erroExcecao,
//                                    Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//        );


    }

    public void carregarTelaCreateProf() {

        edNome = findViewById(R.id.edtNomeProf);
        edSobrenome = findViewById(R.id.edtSobrenomeProf);
        edFantasia = findViewById(R.id.edtNomeFatasiaProf);
        edTelefone = findViewById(R.id.edtTelefoneProf);
        edWhats = findViewById(R.id.edtWhatsProf);
        edEstado = findViewById(R.id.edtEstadoProf);
        edCidade = findViewById(R.id.edtCidadeProf);
        edLogradouro = findViewById(R.id.edtLogradouroProf);
        edNumero = findViewById(R.id.edtNumeroProf);
        edBairro = findViewById(R.id.edtBairroProf);
        edCep = findViewById(R.id.edtCepProf);
        edMinimo = findViewById(R.id.edtValorMinimoProf);
        edMaximo = findViewById(R.id.edtValorMaximoProf);
        spTipoProfissional = findViewById(R.id.sprProfissao);
        spModoAtender = findViewById(R.id.sprModoAtender);
        edModalidade = findViewById(R.id.edtModalidadeProf);
        btCreateProfissional = findViewById(R.id.btnCadastrarProf);
        progressBar = findViewById(R.id.proBar);

    }
}
