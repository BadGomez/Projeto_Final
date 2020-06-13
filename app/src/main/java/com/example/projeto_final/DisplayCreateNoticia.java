package com.example.projeto_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;
import java.util.List;

public class DisplayCreateNoticia extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_create_noticia);

        Intent intentcriarNoticia = getIntent();

        // ---------- Spinner de seleção do País da Noticia ---------------------

        Spinner dropdownPaises;
        dropdownPaises = (Spinner) findViewById(R.id.spinnerPaisNot);

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

        ArrayAdapter<String> adapter4 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, Paises);

        dropdownPaises.setAdapter(adapter4);

        dropdownPaises.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String paisNoticia = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        //------------------------------------------------------------
    }

        public void NovaNoticia(View view){
            TextInputEditText TextInputEditTextTitulo = (TextInputEditText) findViewById(R.id.TextInputEditTextTitulo);
            String Titulo = TextInputEditTextTitulo.getText().toString();

            if (Titulo.length() < 1) {
                TextInputEditTextTitulo.setError(getString(R.string.Campo_Obrigatorio));
                TextInputEditTextTitulo.requestFocus();
                return;
            }

            TextInputEditText TextInputEditTextDescricao = (TextInputEditText) findViewById(R.id.TextInputEditTextDescricao);
            String DescicaoNOT = TextInputEditTextDescricao.getText().toString();

            if (DescicaoNOT.length() < 10) {
                TextInputEditTextDescricao.setError(getString(R.string.Campo_Obrigatorio));
                TextInputEditTextDescricao.requestFocus();
                return;
            }

            //------------------------------------Data da Noticia-------------------------------
            CalendarView calendarViewDataNoticia = (CalendarView) findViewById(R.id.calendarViewDataNoticia);

            calendarViewDataNoticia.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                @Override
                public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                    String dataEstadoAtual = dayOfMonth+"/"+month+"/"+year;
                }
            });
            //---------------------------------------------------------------------------------------
        }
    }
