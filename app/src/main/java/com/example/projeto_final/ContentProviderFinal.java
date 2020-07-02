package com.example.projeto_final;

import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ContentProviderFinal extends android.content.ContentProvider {
    private static final String AUTHORITY = "com.example.projeto_final";
    private static final String PAISES = "pais";
    private static final String PACIENTES = "pacientes";
    private static final String NOTICIAS = "noticias";

    private static final Uri ENDERECO_BASE = Uri.parse("content://" + AUTHORITY);
    public static final Uri ENDERECO_PAISES = Uri.withAppendedPath(ENDERECO_BASE, PAISES);
    public static final Uri ENDERECO_PACIENTES = Uri.withAppendedPath(ENDERECO_BASE, PACIENTES);
    public static final Uri ENDERECO_NOTICIAS = Uri.withAppendedPath(ENDERECO_BASE, NOTICIAS);

    private static final int URI_PAISES = 100;
    private static final int URI_ID_PAISES = 101;

    private static final int URI_PACIENTES = 200;
    private static final int URI_ID_PACIENTES = 201;

    private static final int URI_NOTICIAS = 300;
    private static final int URI_ID_NOTICIAS = 301;

    private static final String CURSOR_DIR = "vnd.android.cursor.dir/";
    private static final String CURSOR_ITEM = "vnd.android.cursor.item/";

    private BdPacientesOpenHelper openHelper;

    private UriMatcher getUriMatcher(){
        UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);

        uriMatcher.addURI(AUTHORITY, PAISES, URI_PAISES);
        uriMatcher.addURI(AUTHORITY, PAISES + "/#", URI_ID_PAISES);

        uriMatcher.addURI(AUTHORITY, PACIENTES, URI_PACIENTES);
        uriMatcher.addURI(AUTHORITY, PACIENTES + "/#", URI_ID_PACIENTES);

        uriMatcher.addURI(AUTHORITY, NOTICIAS, URI_NOTICIAS);
        uriMatcher.addURI(AUTHORITY, NOTICIAS + "/#", URI_ID_NOTICIAS);

        return uriMatcher;
    }

    @Override
    public boolean onCreate() {
        openHelper = new BdPacientesOpenHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {
        SQLiteDatabase bd = openHelper.getReadableDatabase();

        String id = uri.getLastPathSegment();

        switch (getUriMatcher().match(uri)){
            case URI_PAISES:
                return new BdTabelaPaises(bd).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_PAISES:
                return new BdTabelaPaises(bd).query(projection, BdTabelaPaises._ID + "=?", new String[]{ id }, null, null, sortOrder);

            case URI_PACIENTES:
                return new BdTabelaPacientes(bd).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_PACIENTES:
                return new BdTabelaPacientes(bd).query(projection, BdTabelaPacientes._ID + "=?", new String[]{ id }, null, null, sortOrder);

            case URI_NOTICIAS:
                return new BdTabelaNoticias(bd).query(projection, selection, selectionArgs, null, null, sortOrder);

            case URI_ID_NOTICIAS:
                return new BdTabelaNoticias(bd).query(projection, BdTabelaNoticias._ID + "=?", new String[] { id }, null, null, sortOrder);

            default:
                throw new UnsupportedOperationException("Endereço de query inválido: " + uri.getPath());
        }
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        int codigoUri = getUriMatcher().match(uri);

        switch (codigoUri){
            case URI_PAISES:
                return CURSOR_DIR + PAISES;
            case URI_ID_PAISES:
                return CURSOR_ITEM + PAISES;
            case URI_PACIENTES:
                return CURSOR_DIR + PACIENTES;
            case URI_ID_PACIENTES:
                return CURSOR_ITEM + PACIENTES;
            case URI_NOTICIAS:
                return CURSOR_DIR + NOTICIAS;
            case URI_ID_NOTICIAS:
                return CURSOR_ITEM + NOTICIAS;
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues values) {
        SQLiteDatabase bd = openHelper.getWritableDatabase();

        long id;

        switch (getUriMatcher().match(uri)){
            case URI_PAISES:
                id = (new BdTabelaPaises(bd).insert(values));
                break;

            case URI_PACIENTES:
                id = (new BdTabelaPacientes(bd).insert(values));
                break;

            case URI_NOTICIAS:
                id = (new BdTabelaNoticias(bd).insert(values));
                break;

            default:
                throw new UnsupportedOperationException("Endereço inser inválido: " + uri.getPath());
        }
        if (id == -1){
            throw new SQLException("Não foi possivel inserir o registo: " + uri.getPath());
        }
        return Uri.withAppendedPath(uri, String.valueOf(id));
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase bd = openHelper.getWritableDatabase();

        String id = uri.getLastPathSegment();

        switch (getUriMatcher().match(uri)){
            case URI_ID_PAISES:
                return new BdTabelaPaises(bd).delete(BdTabelaPaises._ID + "=?", new String[]{id});

            case URI_ID_PACIENTES:
                return new BdTabelaPacientes(bd).delete(BdTabelaPacientes._ID + "=?", new String[]{ id });

            case URI_ID_NOTICIAS:
                return new BdTabelaNoticias(bd).delete(BdTabelaNoticias._ID + "=?", new String[]{ id });

            default:
                throw new UnsupportedOperationException("Endereço delete inválido: " + uri.getPath());
        }
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues values, @Nullable String selection, @Nullable String[] selectionArgs) {
        SQLiteDatabase bd = openHelper.getWritableDatabase();

        String id = uri.getLastPathSegment();

        switch (getUriMatcher().match(uri)){
            case URI_ID_PAISES:
                return new BdTabelaPaises(bd).update(values, BdTabelaPaises._ID + "=?", new String[]{ id });

            case URI_ID_PACIENTES:
                return new BdTabelaPacientes(bd).update(values, BdTabelaPacientes._ID + "=?", new String[] { id });

            case URI_ID_NOTICIAS:
                return new BdTabelaNoticias(bd).update(values, BdTabelaNoticias._ID + "=?", new String[] { id });

            default:
                throw new UnsupportedOperationException("Endereço de update inválido: " + uri.getPath());
        }
    }
}
