package com.example.marlon.tpagenda;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private String mNomeSalvo = "NOME";
    private String mTelefoneSalvo = "TELEFONE";
    private String mEmailSalvo = "EMAIL";
    private String mCidadeSalvo = "CIDADE";
    private EditText mNome;
    private EditText mTelefone;
    private EditText mEmail;
    private EditText mCidade;
    private final int WRITE_EXTERNAL_STORAGE_PERMISSION_CODE = 1;
    private final int READ_EXTERNAL_STORAGE_PERMISSION_CODE = 2;
    private List<String> mNomeLista = new ArrayList<>();
    private List<String> mTelefoneLista = new ArrayList<>();
    private List<String> mEmailLista = new ArrayList<>();
    private List<String> mCidadeLista = new ArrayList<>();

    private Button mBtnSalvar;
    private Button mBtnLimpar;
    private Button mBtnVisualizarContatos;

    private static final String TAG = "TP_01";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNome = (EditText) findViewById(R.id.nome);
        mTelefone = (EditText) findViewById(R.id.telefone);
        mEmail = (EditText) findViewById(R.id.email);
        mCidade = (EditText) findViewById(R.id.cidade);

        carregarPermissao();
        carregarContatos();
    }

    public void Salvar (View view){

            Acao contato = new Acao();

            String nome = mNome.getText().toString().trim();
            String telefone = mTelefone.getText().toString().trim();
            String email = mEmail.getText().toString().trim();
            String cidade = mCidade.getText().toString().trim();

            if (nome.isEmpty() || telefone.isEmpty() || email.isEmpty() || cidade.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Preencha Tudo", Toast.LENGTH_SHORT).show();

            } else if (mNomeLista.contains(nome)) {
                Toast.makeText(getApplicationContext(), "Contato já existente", Toast.LENGTH_SHORT).show();

            } else {

                contato.setNome(nome);
                contato.setTelefone(telefone);
                contato.setEmail(email);
                contato.setCidade(cidade);

                mNomeLista.add(contato.getNome());
                StringBuilder nomes = new StringBuilder("");
                for (String nomeContato : mNomeLista)
                    nomes.append(nomeContato).append("-");

                try {
                    FileOutputStream fos = openFileOutput(mNomeSalvo, Context.MODE_PRIVATE);

                    fos.write(nomes.toString().getBytes());
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Deu Ruim", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Deu Ruim", Toast.LENGTH_SHORT).show();
                }

                mTelefoneLista.add(contato.getTelefone());
                StringBuilder telefones = new StringBuilder();
                for (String telefoneContato : mTelefoneLista)
                    telefones.append(telefoneContato).append("-");

                try {
                    FileOutputStream fos = openFileOutput(mTelefoneSalvo, Context.MODE_PRIVATE);

                    fos.write(telefones.toString().getBytes());
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Deu Ruim", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Deu Ruim", Toast.LENGTH_SHORT).show();
                }

                mEmailLista.add(contato.getEmail());
                StringBuilder emails = new StringBuilder("");
                for (String emailContato : mEmailLista)
                    emails.append(emailContato).append("-");

                try {
                    FileOutputStream fos = openFileOutput(mEmailSalvo, Context.MODE_PRIVATE);

                    fos.write(emails.toString().getBytes());
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Deu Ruim", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Deu Ruim", Toast.LENGTH_SHORT).show();
                }

                mCidadeLista.add(contato.getCidade());
                StringBuilder cidades = new StringBuilder("");
                for (String cidadeContato : mCidadeLista)
                    cidades.append(cidadeContato).append("-");

                try {
                    FileOutputStream fos = openFileOutput(mCidadeSalvo, Context.MODE_PRIVATE);

                    fos.write(cidades.toString().getBytes());
                } catch (FileNotFoundException e) {
                    Toast.makeText(getApplicationContext(), "Deu Ruim", Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    Toast.makeText(getApplicationContext(), "Deu Ruim", Toast.LENGTH_SHORT).show();
                }

                mNome.setText("");
                mTelefone.setText("");
                mEmail.setText("");
                mCidade.setText("");
                mNome.requestFocus();

                Toast.makeText(getApplicationContext(), "Salvo", Toast.LENGTH_SHORT).show();

            }
    }

    public void carregarContatos() {
        StringBuilder nomes = new StringBuilder("");
        StringBuilder telefones = new StringBuilder("");
        StringBuilder emails = new StringBuilder("");
        StringBuilder cidades = new StringBuilder("");

        try {
            FileInputStream fis = openFileInput(mNomeSalvo);
            byte[] buffer = new byte[(int) fis.getChannel().size()];
            fis.read(buffer);
            for (byte b : buffer)
                nomes.append((char) b);
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Contato não existe", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Não deu para ler", Toast.LENGTH_SHORT).show();
        }

        try {
            FileInputStream fis = openFileInput(mTelefoneSalvo);
            byte[] buffer = new byte[(int) fis.getChannel().size()];
            fis.read(buffer);
            for (byte b : buffer)
                telefones.append((char) b);
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Contato não existe", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Não deu para ler", Toast.LENGTH_SHORT).show();
        }

        try {
            FileInputStream fis = openFileInput(mEmailSalvo);
            byte[] buffer = new byte[(int) fis.getChannel().size()];
            fis.read(buffer);
            for (byte b : buffer)
                emails.append((char) b);
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Contato não existe!", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Não deu para ler", Toast.LENGTH_SHORT).show();
        }

        try {
            FileInputStream fis = openFileInput(mCidadeSalvo);
            byte[] buffer = new byte[(int) fis.getChannel().size()];
            fis.read(buffer);
            for (byte b : buffer)
                cidades.append((char) b);
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "Contato não existe", Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Não deu para ler", Toast.LENGTH_SHORT).show();
        }

        String[] nomesLista = nomes.toString().split("-");
        String[] telefonesLista = telefones.toString().split("-");
        String[] emailsLista = emails.toString().split("-");
        String[] cidadesLista = cidades.toString().split("-");


        mNomeLista.addAll(Arrays.asList(nomesLista));
        mTelefoneLista.addAll(Arrays.asList(telefonesLista));
        mEmailLista.addAll(Arrays.asList(emailsLista));
        mCidadeLista.addAll(Arrays.asList(cidadesLista));

    }

    public void limparTudo (View view){
            mNome.setText(null);
            mTelefone.setText(null);
            mEmail.setText(null);
            mCidade.setText(null);
            mNome.requestFocus();
            Toast.makeText(getApplicationContext(), "Tudo apagado", Toast.LENGTH_SHORT).show();
        }

        public void irNomes (View view){
            Intent intent = new Intent(this, Nome.class);
            intent.putStringArrayListExtra("nomes", (ArrayList<String>) mNomeLista);
            intent.putStringArrayListExtra("telefones", (ArrayList<String>) mTelefoneLista);
            intent.putStringArrayListExtra("emails", (ArrayList<String>) mEmailLista);
            intent.putStringArrayListExtra("cidades", (ArrayList<String>) mCidadeLista);
            startActivity(intent);
        }



    private void carregarPermissao() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            // ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_PERMISSION_CODE);
        } else {
            String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE};
            ActivityCompat.requestPermissions(this, permissions, READ_EXTERNAL_STORAGE_PERMISSION_CODE);
        }

    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case READ_EXTERNAL_STORAGE_PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    carregarPermissao();
                }
                break;

            default:
                Toast.makeText(this, "Precisa de permissão", Toast.LENGTH_SHORT).show();
        }

    }



}
