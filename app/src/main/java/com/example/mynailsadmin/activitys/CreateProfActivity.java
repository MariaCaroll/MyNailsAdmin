package com.example.mynailsadmin.activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mynailsadmin.R;
import com.example.mynailsadmin.model.model.Profissionais;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateProfActivity extends AppCompatActivity {

    private EditText edNome, edSobrenome,edFantasia, edTelefone, edWhats;
    private EditText edEstado, edCidade, edLogradouro, edNumero, edBairro, edCep;
    private EditText edMinimo, edMaximo,  edModalidade;
    private Spinner spTipoProfissional, spModoAtender;

    private Button btCreateProfissional;
    private ProgressBar progressBar;


    private Profissionais prof;

    private DatabaseReference reference;
    private FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_prof);
        carregarTelaCreateProf();

        //CADASTRAR PROFISSIONAL
        progressBar.setVisibility(View.GONE);


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
