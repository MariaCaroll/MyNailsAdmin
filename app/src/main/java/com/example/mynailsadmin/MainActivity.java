package com.example.mynailsadmin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

   //private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();

    private EditText edLogin, edSenha;
    private EditText edNomePro, edSobrenomeProf, edFantasia, edEndereco, edNumero, edBairro, edUf;
    private EditText edCidade, edCep, edProfissao, edModalidades, edAtendimento, edMinimo, edMaximo;
    private Button btEnviarLogin, bnProximoExpecificacoes,btSalvarProf ;
    private Button btCreateProf, btReadProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carregarComponentes();

        /* DatabaseReference usuarios = referencia.child("usuarios");
        Usuarios usuario = new Usuarios();
       usuario.setNome("Maria");
        usuario.setSobrenome("Lima");
        usuario.setLogin("maria");
        usuario.setSenha("lima");*/

        /*usuario.setNome("Maycon");
        usuario.setSobrenome("Macedo");
        usuario.setLogin("maycon");
        usuario.setSenha("macedo");

        usuarios.child("02").setValue(usuario); */
    }


    private void carregarComponentes() {
        btEnviarLogin = (Button) findViewById(R.id.btnAutenticarDadosAdmin);
        edLogin = (EditText) findViewById(R.id.edtLoginAdmin);
        edSenha = (EditText) findViewById(R.id.editTextPasswordAdmin);

        btEnviarLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_inicial_admin);
                carregaComponenstesOpcoesAdmin();
            }
        });
    }
    private void carregaComponenstesOpcoesAdmin() {
        btCreateProf = (Button) findViewById(R.id.btnTelaCadastroProfissional);
        btReadProf = (Button) findViewById(R.id.btnTelaPesquisarProfissional);


        btCreateProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_cadastro_inicial_prof);
                carregaComponenstesProfi();
            }
        });

        btReadProf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    private void carregaComponenstesProfi() {
        bnProximoExpecificacoes = (Button) findViewById(R.id.btnProximo);
        edNomePro = (EditText) findViewById(R.id.edtNomeProf);
        edSobrenomeProf = (EditText) findViewById(R.id.edtSobrenomeProf);
        edFantasia = (EditText) findViewById(R.id.edtNomeFantasia);
        edEndereco = (EditText) findViewById(R.id.edtEnderecoProf);
        edNumero = (EditText) findViewById(R.id.edtNomeProf);
        edBairro = (EditText) findViewById(R.id.edtBairroProf);
        edCidade= (EditText) findViewById(R.id.edtCidadeProf);
        edUf = (EditText) findViewById(R.id.edtUFProf);

        bnProximoExpecificacoes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.activity_cadastro_expecificacao_prof);
                carregaComponenstesCadastroExpecifProfi();
            }
        });



    }
    private void carregaComponenstesCadastroExpecifProfi() {
        btSalvarProf = (Button) findViewById(R.id.btnCadastrar);
        edModalidades = (EditText) findViewById(R.id.edtModalidades);
        edAtendimento = (EditText) findViewById(R.id.edtModoAtender);
        edMinimo = (EditText) findViewById(R.id.edtMinimo);
        edMaximo = (EditText) findViewById(R.id.edtMaximo);
    }

}