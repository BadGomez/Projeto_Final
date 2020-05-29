package com.example.projeto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
                String genero = parent.getItemAtPosition(position).toString();
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
                String doente_cronico = parent.getItemAtPosition(position).toString();
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
                String estado_atual = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
     //------------------------------------------------------------

     // ---------- Spinner de seleção do País ---------------------

        Spinner dropdownPaises;
        dropdownPaises = (Spinner) findViewById(R.id.spinnerPaises);

        final List<String> Paises = new ArrayList<>();
        Paises.add(getString(R.string.País_Albânia));
        Paises.add(getString(R.string.País_Alemanha));
        Paises.add(getString(R.string.País_Andorra));
        Paises.add(getString(R.string.País_Armênia));
        Paises.add(getString(R.string.País_Áustria));
        Paises.add(getString(R.string.País_Azerbaijão));
        Paises.add(getString(R.string.País_Bélgica));
        Paises.add(getString(R.string.País_Bielorrússia));
        Paises.add(getString(R.string.País_Bósnia));
        Paises.add(getString(R.string.País_Bulgária));
        Paises.add(getString(R.string.País_Cazaquistão));
        Paises.add(getString(R.string.País_Chipre));
        Paises.add(getString(R.string.País_Croácia));
        Paises.add(getString(R.string.País_Dinamarca));
        Paises.add(getString(R.string.País_Eslováquia));
        Paises.add(getString(R.string.País_Eslovênia));
        Paises.add(getString(R.string.País_Estónia));
        Paises.add(getString(R.string.País_Finlândia));
        Paises.add(getString(R.string.País_Espanha));
        Paises.add(getString(R.string.País_França));
        Paises.add(getString(R.string.País_Geórgia));
        Paises.add(getString(R.string.País_Gibraltar));
        Paises.add(getString(R.string.País_Grécia));
        Paises.add(getString(R.string.País_Hungria));
        Paises.add(getString(R.string.País_Irlanda));
        Paises.add(getString(R.string.País_Islândia));
        Paises.add(getString(R.string.País_Itália));
        Paises.add(getString(R.string.País_Kosovo));
        Paises.add(getString(R.string.País_Letônia));
        Paises.add(getString(R.string.País_Lituânia));
        Paises.add(getString(R.string.País_Luxemburgo));
        Paises.add(getString(R.string.País_MacedôniaDoNorte));
        Paises.add(getString(R.string.País_Malta));
        Paises.add(getString(R.string.País_Moldávia));
        Paises.add(getString(R.string.País_Noruega));
        Paises.add(getString(R.string.País_Holanda));
        Paises.add(getString(R.string.País_Polónia));
        Paises.add(getString(R.string.País_Portugal));
        Paises.add(getString(R.string.País_ReinoUnido));
        Paises.add(getString(R.string.País_Roménia));
        Paises.add(getString(R.string.País_Rússia));
        Paises.add(getString(R.string.País_SanMarino));
        Paises.add(getString(R.string.País_Sérvia));
        Paises.add(getString(R.string.País_Suécia));
        Paises.add(getString(R.string.País_Suiça));
        Paises.add(getString(R.string.País_Turquia));
        Paises.add(getString(R.string.País_Ucrânia));

        ArrayAdapter <String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Paises);

        dropdownPaises.setAdapter(adapter4);

        dropdownPaises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String pais = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
     //------------------------------------------------------------
    }

    public void NovoRegisto(View view){
        TextInputEditText TextInputEditNome = (TextInputEditText) findViewById(R.id.TextInputEditNome);
        String nome = TextInputEditNome.getText().toString();

        if(nome.length() < 1){
            TextInputEditNome.setError(getString(R.string.Campo_Obrigatorio));
            TextInputEditNome.requestFocus();
            return;
        }

        //------------------------------------Data de Aniversário-------------------------------
        TextInputEditText TextInputEditDataNascimento = (TextInputEditText) findViewById(R.id.TextInputEditDataNascimento);

        Date data = null;
        String data_aniversario = new String(TextInputEditDataNascimento.getText().toString());
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        try {
            format.setLenient(false);
            data = format.parse(data_aniversario);
        } catch (ParseException e) {
            TextInputEditDataNascimento.setError(getString(R.string.CampoInválido));
            TextInputEditDataNascimento.requestFocus();
            return;
        }
        //---------------------------------------------------------------------------------------
        //------------------------------------Data Estado Atual----------------------------------
        TextInputEditText TextInputEditDataEstadoAtual = (TextInputEditText) findViewById(R.id.TextInputEditDataEstadoAtual);

        Date data2 = null;
        String data_estado_atual = new String(TextInputEditDataEstadoAtual.getText().toString());
        try {
            format.setLenient(false);
            data2 = format.parse(data_estado_atual);
        } catch (ParseException e) {
            TextInputEditDataEstadoAtual.setError(getString(R.string.CampoInválido));
            TextInputEditDataEstadoAtual.requestFocus();
            return;
        }
        //---------------------------------------------------------------------------------------
    }

}