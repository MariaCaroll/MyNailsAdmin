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
import com.example.mynailsadmin.helper.DateUtil;
import com.example.mynailsadmin.model.model.Profissionais;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateProfActivity extends AppCompatActivity {

    private EditText edNome, edSobrenome,edFantasia, edTelefone, edWhats;
    private EditText  edCidade, edLogradouro, edNumero, edBairro, edCep;
    private EditText edMinimo, edMaximo,  edModalidade, edDate;
    private Spinner spTipoProfissional, spEstado, spModoAtender;

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

         edDate = findViewById(R.id.edtData);
        edNome = findViewById(R.id.edtNomeProf);
        edSobrenome = findViewById(R.id.edtSobrenomeProf);
        edFantasia = findViewById(R.id.edtNomeFatasiaProf);
        edTelefone = findViewById(R.id.edtTelefoneProf);
        edWhats = findViewById(R.id.edtWhatsProf);
        spEstado = findViewById(R.id.sprEstado);
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



        //preenche o campo data
        edDate.setText(DateUtil.dataAtual());

    }

    public void cadastrarProf(View view) {
        prof = new Profissionais();
        //pessoal
        prof.setNome(edNome.getText().toString().trim());
        prof.setSobreome(edSobrenome.getText().toString().trim());
        prof.setNome_fantasia(edFantasia.getText().toString().trim());

        //endereco
        prof.setEndereco_uf(spEstado.getSelectedItem().toString().trim());
        prof.setEndereco_cep(edCep.getText().toString().trim());
        prof.setEndereco_cidade(edCidade.getText().toString().trim());
        prof.setEndereco_numero(edNumero.getText().toString().trim());
        prof.setEndereco_bairro(edBairro.getText().toString().trim());
        prof.setEndereco_rua(edLogradouro.getText().toString().trim());

        //contato
        prof.setWhats(edWhats.getText().toString().trim());
        prof.setTelenone(edTelefone.getText().toString().trim());

        //valores
        prof.setValor_maximo(Double.parseDouble(edMaximo.getText().toString().trim()));
        prof.setValor_minimo(Double.parseDouble(edMinimo.getText().toString().trim()));

        //proffisao
        String tipoProfissional = spTipoProfissional.getSelectedItem().toString().trim();
        prof.setTipo_profissional(tipoProfissional);
        prof.setTipo_atendimento(spModoAtender.getSelectedItem().toString().trim());
        prof.setModalidades(edModalidade.getText().toString().trim());


        prof.setData(edDate.getText().toString().trim());

        //salvar a prof
        prof.salvar(tipoProfissional);
        limparCampos();

        Toast.makeText(CreateProfActivity.this, "Sucesso ao cadastrar Profissional",
                Toast.LENGTH_SHORT).show();
    }

    public  void limparCampos(){

        edNome.setText("");
        edSobrenome.setText("");
        edFantasia.setText("");
        edTelefone.setText("");
        edWhats.setText("");
        edCidade.setText("");
        edLogradouro.setText("");
        edNumero.setText("");
        edBairro.setText("");
        edCep.setText("");
        edMinimo.setText("");
        edMaximo.setText("");
        edModalidade.setText("");

    }

}
