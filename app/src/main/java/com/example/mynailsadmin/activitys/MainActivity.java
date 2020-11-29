package com.example.mynailsadmin.activitys;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.mynailsadmin.Mask;
import com.example.mynailsadmin.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

   private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();


    private EditText edLogin, edSenha, edEmailAdmin, edSenhaAdmin;
    private EditText edNomePro, edSobrenomeProf, edFantasia, edEndereco, edNumero, edBairro, edUf, edPhone, edWhats;
    private TextView txtCreateAdmin;
    private EditText edCidade, edCep, edModalidades, edMinimo, edMaximo;
    private Spinner spAtendimento, spProfissao;
    private Button btEnviarLogin, bnCadastraProfi,btSalvarProf ;
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
    private void carregaComponenstesNewAdmin() {
        //edEmailAdmin = (EditText) findViewById(R.id.edtCreateAdmin);
       // edSenhaAdmin = (EditText) findViewById(R.id.editTextPasswordCreateAdmin);
        txtCreateAdmin = (TextView)  findViewById(R.id.txtCreateNewAdmin);

    }

    private void carregaComponenstesProfi() {
        bnCadastraProfi = (Button) findViewById(R.id.btnCadastrar);
        edNomePro = (EditText) findViewById(R.id.edtNomeProf);
        edSobrenomeProf = (EditText) findViewById(R.id.edtSobrenomeProf);
        edFantasia = (EditText) findViewById(R.id.edtNomeFantasia);
        edEndereco = (EditText) findViewById(R.id.edtEnderecoProf);
        edNumero = (EditText) findViewById(R.id.edtNumeroProf);
        edBairro = (EditText) findViewById(R.id.edtBairroProf);
        edCidade= (EditText) findViewById(R.id.edtCidadeProf);
        edUf = (EditText) findViewById(R.id.edtUFProf);
        edModalidades = (EditText) findViewById(R.id.edtModalidades);
        spAtendimento = (Spinner) findViewById(R.id.sprModoAtender);
        spProfissao = (Spinner) findViewById(R.id.sprProfissao);
        edMinimo = (EditText) findViewById(R.id.edtMinimo);
        edMaximo = (EditText) findViewById(R.id.edtMaximo);
        edPhone = (EditText) findViewById(R.id.edtPhoneProf);
        edWhats = (EditText) findViewById(R.id.edtWhatsProf);


        //Macaras
        edPhone.addTextChangedListener(Mask.insert("(##)####-####", edPhone));
        edWhats.addTextChangedListener(Mask.insert("(##) #####-####", edWhats));

        //Spinner
        Spinner spinProf = findViewById(R.id.sprProfissao);
        ArrayAdapter<CharSequence> adapterProf = ArrayAdapter.createFromResource(this, R.array.profissao, android.R.layout.simple_spinner_item);
        adapterProf.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinProf.setAdapter(adapterProf);
        spinProf.setOnItemSelectedListener(this);
        TextView textoSpProf = (TextView) spProfissao.getChildAt(0);
        textoSpProf.setTextColor(getResources().getColor(R.color.holo_orange_clarinha));

        Spinner spinPAten = findViewById(R.id.sprModoAtender);
        ArrayAdapter<CharSequence> adapterAten = ArrayAdapter.createFromResource(this, R.array.modoAtender, android.R.layout.simple_spinner_item);
        adapterAten.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinProf.setAdapter(adapterAten);
        spinProf.setOnItemSelectedListener(this);
        TextView textoSpAten = (TextView) spProfissao.getChildAt(0);
        textoSpAten.setTextColor(getResources().getColor(R.color.holo_orange_clarinha));


        bnCadastraProfi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}