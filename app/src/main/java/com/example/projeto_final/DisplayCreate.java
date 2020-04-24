package com.example.projeto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class DisplayCreate extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_create);

        Intent intentcriar = getIntent();

     // ---------- Spinner de seleção do Género -----------   https://www.youtube.com/watch?v=4xKsWNmULr0
        Spinner dropdowngenero;
        dropdowngenero = (Spinner) findViewById(R.id.spinnerGenero);

        final List<String> genero = new ArrayList<>();
        genero.add(getString(R.string.GeneroF));
        genero.add(getString(R.string.GeneroM));

        ArrayAdapter <String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genero);

        dropdowngenero.setAdapter(adapter);

        dropdowngenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strGeneroInserido = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
     //------------------------------------------------------------

     // ---------- Spinner de seleção de Doença Crónica -----------
        Spinner dropdownDoencaCronica;
        dropdownDoencaCronica = (Spinner) findViewById(R.id.spinnerDoencaCronica);

        final List<String> DoencaCronica = new ArrayList<>();
        DoencaCronica.add(getString(R.string.DoencaCronicaSim));
        DoencaCronica.add(getString(R.string.DoencaCronicaNao));

        ArrayAdapter <String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, DoencaCronica);

        dropdownDoencaCronica.setAdapter(adapter2);

        dropdownDoencaCronica.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strTemDoencaCronica = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
     //------------------------------------------------------------
     // ---------- Spinner de seleção do Estado Atual -------------

        Spinner dropdownEstadoAtual;
        dropdownEstadoAtual = (Spinner) findViewById(R.id.spinnerEstadoAtual);

        final List<String> EstadoAtual = new ArrayList<>();
        EstadoAtual.add(getString(R.string.EstadoInfetado));
        EstadoAtual.add(getString(R.string.EstadoRecuperado));
        EstadoAtual.add(getString(R.string.EstadoCritico));
        EstadoAtual.add(getString(R.string.EstadoObito));

        ArrayAdapter <String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, EstadoAtual);

        dropdownEstadoAtual.setAdapter(adapter3);

        dropdownEstadoAtual.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strEstadoAtual = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
     //------------------------------------------------------------
    }
}
