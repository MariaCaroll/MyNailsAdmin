package com.example.mynailsadmin.activitys;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.Telephony;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mynailsadmin.R;
import com.example.mynailsadmin.helper.ConfigFirebase;
import com.example.mynailsadmin.helper.DateUtil;
import com.example.mynailsadmin.model.model.Profissionais;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;
import java.util.List;

public class CreateProfActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edNome, edSobrenome,edFantasia, edTelefone, edWhats;
    private EditText  edCidade, edLogradouro, edNumero, edBairro, edCep;
    private EditText edMinimo, edMaximo,  edModalidade, edDate;
    private Spinner spTipoProfissional, spEstado, spModoAtender;
    private ImageView img;
    private Button btCreateProfissional;
    private ProgressBar progressBar;

    private Profissionais prof;

    private DatabaseReference reference;
    private FirebaseDatabase database;
    private StorageReference storage;

    private String[] permissoes = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE};
    private List<String> listaFotosRecuperadas = new ArrayList<>();
    private List<String> listaURLFotos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_prof);
        carregarTelaCreateProf();
        storage = ConfigFirebase.getReferenciaStorage();

    }

    public void salvarImage()
    {
        /**
         * salva a img no storaga
         */
        for (int i = 0; i < listaFotosRecuperadas.size(); i++)
        {
            String urlImagem = listaFotosRecuperadas.get(i);
            int tamanhoLista = listaFotosRecuperadas.size();
            salvarFotoStorage(urlImagem, tamanhoLista, i);
        }
    }

    private  void salvarFotoStorage(String urlString, final int totalFotos, int contador)
    {
        //cria nó no storage
        final StorageReference  imagemProf = storage.child("imagens")
                .child("profissionais")
                .child(prof.getIdProf())
                .child("imagens" + contador);

        //fazer o uplodae da img
        UploadTask uploadTask = imagemProf.putFile(Uri.parse(urlString));
        uploadTask.continueWithTask(new Continuation<UploadTask.TaskSnapshot, Task<Uri>>() {
            @Override
            public Task<Uri> then(@NonNull Task<UploadTask.TaskSnapshot> task) throws Exception {
                if (!task.isSuccessful()) {
                    throw task.getException();
                }

                return imagemProf.getDownloadUrl();
            }
        }).addOnCompleteListener(new OnCompleteListener<Uri>() {
            @Override
            public void onComplete(@NonNull Task<Uri> task) {
                if (task.isSuccessful()) {
                    Uri url = task.getResult();
                    listaURLFotos.add(url.toString());

                    if(totalFotos == listaURLFotos.size()) {
                        prof.setFotos(listaURLFotos);
                        prof.salvar();
                    }

                } else {
                    exibirMensagemErro("Falha ao enviar a foto!");
                }

            }
        });

    }

    public void carregarTelaCreateProf() {

        img = findViewById(R.id.imgProf);
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

        img.setOnClickListener(this);

    }

    public Profissionais configuraProf() {
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

        return prof;

    }

    @Override
    public void onClick(View v) {
        Log.d("onClick", "onClick: " + v.getId());
        switch (v.getId()) {
            case R.id.imgProf:
                Log.d("onClick", "onClick: ");
                escolherImage(1);
                break;
        }
    }

    public void escolherImage(int requestCode)
    {
        Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(i, requestCode);
    }

    public void validarDados(View view)
    {
       prof = configuraProf();
       if(listaFotosRecuperadas.size()!= 0)
       {
           if(!prof.getNome().isEmpty()) {
               if(!prof.getSobreome().isEmpty()){
                   if(!prof.getNome_fantasia().isEmpty()) {
                       if(!prof.getWhats().isEmpty()) {
                           if(!prof.getTelenone().isEmpty()){
                               if(!prof.getEndereco_cidade().isEmpty()){
                                   if(!prof.getTipo_profissional().isEmpty()) {
                                       if(!prof.getTipo_atendimento().isEmpty()) {
                                          salvarImage();
                                       }else{
                                           exibirMensagemErro("Preencha a tipo de atendimento");
                                       }
                                   }else {
                                       exibirMensagemErro("Preencha o tipo de profissional");
                                   }
                               }else {
                                   exibirMensagemErro("Preencha a cidade");
                               }
                           }else {
                               exibirMensagemErro("Preencha o Telefone");
                           }
                       }else {
                           exibirMensagemErro("Preencha o Whats App");
                       }
                   }else {
                       exibirMensagemErro("Preencha nome Fantasia");
                   }
               }else {
                   exibirMensagemErro("Preencha o Sobrenome");
               }
           } else {
               exibirMensagemErro("Preencha o nome");
           }
       }
    }

    private void exibirMensagemErro(String texto)
    {
        Toast.makeText(this, texto, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == Activity.RESULT_OK)
        {
            Uri imagemSelecionada = data.getData();
            String caminhoImagem = imagemSelecionada.toString();

            if(requestCode == 1) {
                img.setImageURI(imagemSelecionada);
            }
            listaFotosRecuperadas.add(caminhoImagem);

        }
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for( int permissaoResultado : grantResults ){
            if( permissaoResultado == PackageManager.PERMISSION_DENIED){
                alertaValidacaoPermissao();
            }
        }

    }

    private void alertaValidacaoPermissao(){

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Permissões Negadas");
        builder.setMessage("Para utilizar o app é necessário aceitar as permissões");
        builder.setCancelable(false);
        builder.setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();

    }


}
