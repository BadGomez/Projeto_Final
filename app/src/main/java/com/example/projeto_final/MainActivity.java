package com.example.projeto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void CriarDoente(View view){
        Intent intentcriar = new Intent(this, DisplayCreate.class);
        startActivity(intentcriar);
    }

    public void VerDados(View view){
        Intent intentver = new Intent(this, DisplayVer.class);
        startActivity(intentver);
    }
}
