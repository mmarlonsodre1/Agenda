package com.example.marlon.tpagenda;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class Agenda extends AppCompatActivity {
    public static TextView mresultNome, mresultTelefone, mresultEmail, mresultCidade;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda);
        mresultNome = findViewById(R.id.resultName);
        mresultTelefone = findViewById(R.id.resultTelefone);
        mresultEmail = findViewById(R.id.resultEmail);
        mresultCidade= findViewById(R.id.resultCidade);

        Intent intent = getIntent();
        String mNome = (String) intent.getSerializableExtra("nomes");
        String mTelefone = (String) intent.getSerializableExtra("telefones");
        String mEmail = (String) intent.getSerializableExtra("emails");
        String mCidade = (String) intent.getSerializableExtra("cidades");

        mresultNome.setText(mNome);
        mresultTelefone.setText(mTelefone);
        mresultEmail.setText(mEmail);
        mresultCidade.setText(mCidade);
    }
}
