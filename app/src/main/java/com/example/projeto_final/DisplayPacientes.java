package com.example.projeto_final;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.fragment.app.FragmentManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.CursorAdapter;


public class DisplayPacientes extends AppCompatActivity {

    public static final String ID_PACIENTE = "ID_PACIENTE";
    public static final int ID_CURSOR_LOADER_PACIENTE = 0;
    private AdaptadorPacientes adaptadorPacientes;
    private RecyclerView recyclerViewPacientes;

    private Paciente paciente = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_pacientes);
        Intent intentPacientes = getIntent();

        recyclerViewPacientes = (RecyclerView) findViewById(R.id.recyclerViewPacientes);
        adaptadorPacientes = new AdaptadorPacientes(this);
        recyclerViewPacientes.setAdapter(adaptadorPacientes);
        recyclerViewPacientes.setLayoutManager(new LinearLayoutManager(this));

        adaptadorPacientes.setCursor(null);

    }

    public void CriarDoente(View view){
        Intent intentcriar = new Intent(this, DisplayCreate.class);
        startActivity(intentcriar);
    }
}
