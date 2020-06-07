package com.example.projeto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class DisplayNoticias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_noticias);

        Intent intentNoticias = getIntent();

    }

    public void CriarNoticia(View view){
        Intent intentcriarNoticia = new Intent(this, DisplayCreateNoticia.class);
        startActivity(intentcriarNoticia);
    }
}
