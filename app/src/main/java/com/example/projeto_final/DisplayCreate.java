package com.example.projeto_final;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.loader.content.CursorLoader;

import android.content.Context;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;



import com.google.android.material.textfield.TextInputEditText;
import java.util.ArrayList;
import java.util.List;

public class DisplayCreate extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private Spinner spinnerPaises;
    public static final int ID_CURSOR_LOADER_PAISES = 0;
    private Spinner dropdowngenero;
    private CalendarView calendarViewDataNascimento;
    private Spinner dropdownDoencaCronica;
    private Spinner dropdownEstadoAtual;
    private CalendarView calendarViewDataEstadoAtual;
    private EditText TextInputEditNome;

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

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, genero);

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

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, DoencaCronica);

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

        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, EstadoAtual);

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
        //-----------------------------------------------------------------------

        // ---------- Spinner de seleção do País ---------------------

        spinnerPaises = (Spinner) findViewById(R.id.spinnerPaises);
        mostrarDadosSpinnerPaises(null);

        LoaderManager.getInstance(this).initLoader(ID_CURSOR_LOADER_PAISES, null , this);

        //------------------------------------------------------------------------------------

        TextInputEditText TextInputEditNome = (TextInputEditText) findViewById(R.id.TextInputEditNome);




        //------------------------------------Data de Aniversário------------------------------- https://www.youtube.com/watch?v=j_-dmsRWL3g
        CalendarView calendarViewDataNascimento = (CalendarView) findViewById(R.id.calendarViewDataNascimento);

        calendarViewDataNascimento.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String data_aniversario = dayOfMonth + "/" + month + "/" + year;
            }
        });
        //---------------------------------------------------------------------------------------
        //------------------------------------Data Estado Atual----------------------------------
        CalendarView calendarViewDataEstadoAtual = (CalendarView) findViewById(R.id.calendarViewDataEstadoAtual);

        calendarViewDataEstadoAtual.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String data_estado_atual = dayOfMonth + "/" + month + "/" + year;
            }
        });

        //---------------------------------------------------------------------------------------
    }

    private void mostrarDadosSpinnerPaises(Cursor data) {
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_spinner_item,
                data,
                new String[]{BdTabelaPaises.NOME_PAIS},
                new int[]{android.R.id.text1}
        );
        spinnerPaises.setAdapter(adapter);
    }

        //saving data
    public void NovoPaciente(View view) {

        long paisEscolhido = spinnerPaises.getSelectedItemId();
        String Genero = dropdowngenero.getSelectedItem().toString();
        String DataAniv = calendarViewDataNascimento.toString();
        String Doenca_Cronica = dropdownDoencaCronica.getSelectedItem().toString();
        String Estado_Atual = dropdownEstadoAtual.getSelectedItem().toString();
        String DataEstadoAtual = calendarViewDataEstadoAtual.toString();
        String nome = TextInputEditNome.getText().toString();

            if (nome.length() < 1) {
                TextInputEditNome.setError(getString(R.string.Campo_Obrigatorio));
                TextInputEditNome.requestFocus();
                return;
            }

        Paciente paciente = new Paciente();
        paciente.setNome(nome);
        paciente.setId_Pais(paisEscolhido);
        paciente.setGenero(Genero);
        paciente.setData_aniversario(DataAniv);
        paciente.setDoente_cronico(Doenca_Cronica);
        paciente.setEstado_atual(Estado_Atual);
        paciente.setData_estado_atual(DataEstadoAtual);


        try {
            this.getContentResolver().insert(ContentProviderFinal.ENDERECO_PACIENTES, Converte.pacienteToContentValues(paciente));
            Toast.makeText(this, "SUCESSO", Toast.LENGTH_SHORT).show();
        } catch (Exception e){
            Toast.makeText(this, "FALHOU", Toast.LENGTH_SHORT).show();
        }
        Intent intentPacientes = new Intent(this, DisplayPacientes.class);
        startActivity(intentPacientes);
    }

    /**
     * Instantiate and return a new Loader for the given ID.
     *
     * <p>This will always be called from the process's main thread.
     *
     * @param id   The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this, ContentProviderFinal.ENDERECO_PAISES, BdTabelaPaises.TODOS_CAMPOS_PAIS, null, null, null);
    }

    /**
     * Called when a previously created loader has finished its load.  Note
     * that normally an application is <em>not</em> allowed to commit fragment
     * transactions while in this call, since it can happen after an
     * activity's state is saved.  See {@link FragmentManager#beginTransaction()
     * FragmentManager.openTransaction()} for further discussion on this.
     *
     * <p>This function is guaranteed to be called prior to the release of
     * the last data that was supplied for this Loader.  At this point
     * you should remove all use of the old data (since it will be released
     * soon), but should not do your own release of the data since its Loader
     * owns it and will take care of that.  The Loader will take care of
     * management of its data so you don't have to.  In particular:
     *
     * <ul>
     * <li> <p>The Loader will monitor for changes to the data, and report
     * them to you through new calls here.  You should not monitor the
     * data yourself.  For example, if the data is a {@link Cursor}
     * and you place it in a {@link CursorAdapter}, use
     * the {@link CursorAdapter#CursorAdapter(Context,
     * Cursor, int)} constructor <em>without</em> passing
     * in either {@link CursorAdapter#FLAG_AUTO_REQUERY}
     * or {@link CursorAdapter#FLAG_REGISTER_CONTENT_OBSERVER}
     * (that is, use 0 for the flags argument).  This prevents the CursorAdapter
     * from doing its own observing of the Cursor, which is not needed since
     * when a change happens you will get a new Cursor throw another call
     * here.
     * <li> The Loader will release the data once it knows the application
     * is no longer using it.  For example, if the data is
     * a {@link Cursor} from a {@link CursorLoader},
     * you should not call close() on it yourself.  If the Cursor is being placed in a
     * {@link CursorAdapter}, you should use the
     * {@link CursorAdapter#swapCursor(Cursor)}
     * method so that the old Cursor is not closed.
     * </ul>
     *
     * <p>This will always be called from the process's main thread.
     *  @param loader The Loader that has finished.
     *
     * @param data The data generated by the Loader.
     */
    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        mostrarDadosSpinnerPaises(data);
    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     *
     * <p>This will always be called from the process's main thread.
     *
     * @param loader The Loader that is being reset.
     */
    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        mostrarDadosSpinnerPaises(null);
    }
}