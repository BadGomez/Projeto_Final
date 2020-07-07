package com.example.projeto_final;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import androidx.loader.content.CursorLoader;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class MenuEditarNoticia extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {

    private static final int ID_CURSOR_LOADER_NOTICIA = 0;
    private TextView textViewTituloNoticia;
    private TextView textViewDataNoticia;
    private TextView textViewDescricaoNoticia;
    private Uri enderecoNoticiaEditar;
    private Noticia noticia;
    private Spinner spinnerPaisNoticia;

    private boolean noticiaCarregadas = false;
    private boolean noticiaAtualizada = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_editar_noticia);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        spinnerPaisNoticia = (Spinner) findViewById(R.id.spinnerPaisNoticia);
        textViewTituloNoticia = (TextView) findViewById(R.id.textViewTituloNoticia);
        textViewDataNoticia = (TextView) findViewById(R.id.textViewDataNoticia);
        textViewDescricaoNoticia = (TextView) findViewById(R.id.textViewDescricaoNoticia);

        mostrarDadosSpinnerPais(null);

        getSupportLoaderManager().initLoader(ID_CURSOR_LOADER_NOTICIA, null, this);

        Intent intentAlterarNoticia = getIntent();

        long idNoticia = intentAlterarNoticia.getLongExtra(DisplayNoticias.ID_NOTICIA,-1);

        if(idNoticia == -1){
            Toast.makeText(this, "MISTAKE WAS MADE", Toast.LENGTH_LONG ).show();
            finish();
            return;
        }

        enderecoNoticiaEditar = Uri.withAppendedPath(ContentProviderFinal.ENDERECO_NOTICIAS, String.valueOf(idNoticia));

        Cursor cursor = getContentResolver().query(enderecoNoticiaEditar, BdTabelaNoticias.TODOS_CAMPOS_NOTICIAS, null, null, null);

        if(!cursor.moveToNext()){
            Toast.makeText(this,"probs", Toast.LENGTH_LONG).show();
            finish();
            return;
        }

        noticia = (Noticia) intentAlterarNoticia.getSerializableExtra("Noticia");
        textViewTituloNoticia.setText(noticia.getTitulo());
        textViewDataNoticia.setText(noticia.getData());
        textViewDescricaoNoticia.setText(noticia.getConteudo());

        LoaderManager.getInstance(this).initLoader(ID_CURSOR_LOADER_NOTICIA, null,this);

        actualizarNoticiaSelecionada();
    }

    private void actualizarNoticiaSelecionada() {
        if (!noticiaCarregadas)return;
        if(noticiaAtualizada) return;

        long idNoticia = noticia.getId();

        for (int i=0; i< spinnerPaisNoticia.getCount(); i++){
            if (spinnerPaisNoticia.getItemIdAtPosition(i) == idNoticia) {
                spinnerPaisNoticia.setSelection(i, true);
                break;
            }
        }
        noticiaAtualizada = true;
    }

    private void mostrarDadosSpinnerPais(Cursor data) {
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(
                this,
                android.R.layout.simple_list_item_1,
                data,
                new String[]{BdTabelaNoticias.TITULO_NOTICIA},
                new int[]{android.R.id.text1}
        );
        spinnerPaisNoticia.setAdapter(adapter);
    }

    public void EditarNoticia(View view){

        String conteudoTitulo = textViewTituloNoticia.getText().toString();
        String conteudoData = textViewDataNoticia.getText().toString();
        String conteudoDescricao = textViewDescricaoNoticia.getText().toString();

        long idNoticia = spinnerPaisNoticia.getSelectedItemId();

        if(conteudoTitulo.trim().isEmpty()){

            textViewTituloNoticia.setError(getString(R.string.Campo_Obrigatorio));
            textViewTituloNoticia.requestFocus();
            return;

        }else if(conteudoData.trim().isEmpty()){

            textViewDataNoticia.setError(getString(R.string.Campo_Obrigatorio));
            textViewDataNoticia.requestFocus();
            return;

        }else if(conteudoDescricao.trim().isEmpty()){

            textViewDescricaoNoticia.setError(getString(R.string.Campo_Obrigatorio));
            textViewDescricaoNoticia.requestFocus();
            return;

        }

        noticia.setTitulo(conteudoTitulo);
        noticia.setData(conteudoData);
        noticia.setConteudo(conteudoDescricao);
        noticia.setId_Pais(idNoticia);

        try {
            Uri endereçoNoticia = Uri.withAppendedPath(ContentProviderFinal.ENDERECO_NOTICIAS, String.valueOf(noticia.getId()));

            int registos = this.getContentResolver().update(endereçoNoticia, Converte.noticiaToContentValues(noticia), null,null);

            if(registos == 1){
                Toast.makeText(this, "Alterado com sucesso", Toast.LENGTH_SHORT).show();
                finish();
                return;
            }
        }catch (Exception e){

        }
    }

    public  void CancelarEditarNoticia(View view){
        Toast.makeText(this, "came back", Toast.LENGTH_SHORT).show();
        finish();
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
        return new CursorLoader(this, ContentProviderFinal.ENDERECO_PAISES, BdTabelaPaises.TODOS_CAMPOS_PAIS, null,null,null);
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
        mostrarDadosSpinnerPais(data);
        noticiaCarregadas = true;
        actualizarNoticiaSelecionada();
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
        mostrarDadosSpinnerPais(null);
    }
}