package com.example.marlon.tpagenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class Nome extends AppCompatActivity {
    public static ListView mLvwLista;
    private List<String> mNomeLista = new ArrayList<>();
    private List<String> mTelefoneLista = new ArrayList<>();
    private List<String> mEmailLista = new ArrayList<>();
    private List<String> mCidadeLista = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nome);

        mLvwLista = findViewById(R.id.lista);
        

        final Intent intent = getIntent();
        mNomeLista = intent.getStringArrayListExtra("nomes");
        mTelefoneLista = intent.getStringArrayListExtra("telefones");
        mEmailLista = intent.getStringArrayListExtra("emails");
        mCidadeLista = intent.getStringArrayListExtra("cidades");

        mLvwLista.setAdapter(new ArrayAdapter<String>(getApplicationContext(), R.layout.list_view, R.id.listNome, mNomeLista));

        mLvwLista.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent informacoesContatoIntent = new Intent(Nome.this, Agenda.class);
                informacoesContatoIntent.putExtra("nomes", mNomeLista.get(position));
                informacoesContatoIntent.putExtra("telefones", mTelefoneLista.get(position));
                informacoesContatoIntent.putExtra("emails", mEmailLista.get(position));
                informacoesContatoIntent.putExtra("cidades", mCidadeLista.get(position));
                startActivity(informacoesContatoIntent);

            }
        });
    }

    public void novo (View view){
        finish();
    }
}
