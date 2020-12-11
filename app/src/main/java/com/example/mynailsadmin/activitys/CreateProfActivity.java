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
    public void cadastrarProf(View view){


            progressBar.setVisibility(View.VISIBLE);
            prof = new Profissionais();
            prof.setNome(edNome.getText().toString());
            prof.setSobreome(edSobrenome.getText().toString());
            prof.setNome_fantasia(edFantasia.getText().toString());
            prof.setTelenone(edTelefone.getText().toString());
            prof.setWhats(edWhats.getText().toString());
            prof.setEndereco_cidade(edCidade.getText().toString());
            prof.setEndereco_bairro(edBairro.getText().toString());
            prof.setEndereco_numero(edNumero.getText().toString());
            prof.setEndereco_rua(edLogradouro.getText().toString());
            prof.setEndereco_uf(edEstado.getText().toString());
            prof.setModalidades(edModalidade.getText().toString());
            prof.setValor_minimo(Double.parseDouble(edMinimo.getText().toString()));
            prof.setValor_maximo(Double.parseDouble(edMaximo.getText().toString()));
            prof.setTipo_atendimento(spModoAtender.getSelectedItem().toString());
            prof.setTipo_profissional(spTipoProfissional.getSelectedItem().toString());


            prof.salvar();


    }
//
//    public Boolean validarCampos() {
//
//        String textoNome = edNome.getText().toString();
//        String textoSobrenome = edSobrenome.getText().toString();
//        String textoFantasia = edFantasia.getText().toString();
//        String textotelefone = edTelefone.getText().toString();
//        String textoWhats = edWhats.getText().toString();
//        double textoMinimo = Double.parseDouble(String.valueOf(edMinimo));
//        double textoMaximo = Double.parseDouble(String.valueOf(edMaximo));
//        String textoEstado = edEstado.getText().toString();
//        String textoCidade = edCidade.getText().toString();
//        String textoLogradouro = edLogradouro.getText().toString();
//        String textoNumero = edNumero.getText().toString();
//        String textoBairro = edBairro.getText().toString();
//        String textoCep = edCep.getText().toString();
//        String textoModalidade = edModalidade.getText().toString();
//        String textoTipoProfi = spTipoProfissional.getSelectedItem().toString();
//        String textoModoAtender = spModoAtender.getSelectedItem().toString();
//
//
//        if (!(textoNome.isEmpty() || textoSobrenome.isEmpty() || textoFantasia.isEmpty() ||
//                textoWhats.isEmpty() || textotelefone.isEmpty() || textoEstado.isEmpty() ||
//                textoCep.isEmpty() || textoCidade.isEmpty() || textoLogradouro.isEmpty() ||
//                textoNumero.isEmpty() || textoBairro.isEmpty() || textoNumero.isEmpty() ||
//                textoBairro.isEmpty() || textoTipoProfi.isEmpty() || textoModoAtender.isEmpty() ||
//                textoModalidade.isEmpty()))
//        {
//
//            prof = new Profissionais();
//            prof.setNome(textoNome);
//            prof.setSobreome(textoSobrenome);
//            prof.setNome_fantasia(textoFantasia);
//            prof.setTelenone(textotelefone);
//            prof.setWhats(textoWhats);
//            prof.setValor_maximo(textoMaximo);
//            prof.setValor_minimo(textoMinimo);
//            prof.setEndereco_uf(textoEstado);
//            prof.setEndereco_rua(textoLogradouro);
//            prof.setEndereco_numero(textoNumero);
//            prof.setEndereco_bairro(textoBairro);
//            prof.setEndereco_cep(textoCep);
//            prof.setModalidades(textoModalidade);
//            prof.setTipo_profissional(textoTipoProfi);
//            prof.setTipo_atendimento(textoModoAtender);
//
//
//        }else {
//            Toast.makeText(CreateProfActivity.this,
//                    "Preencha os Campos Corretamente! ",
//                    Toast.LENGTH_SHORT).show();
//        }
//    }

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
