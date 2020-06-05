package com.example.projeto_final;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTabelaNoticias implements BaseColumns {
    public static final String NOME_TABELA_NOTICIAS = "noticias";
    public static final String PAIS_NOTICIA = "paisNoticia";
    public static final String TITULO_NOTICIA = "titulo";
    public static final String DATA_NOTICIA = "data";
    public static final String CONTEUDO_NOTICIA = "conteudo";

    private final SQLiteDatabase db;

    public BdTabelaNoticias(SQLiteDatabase db) {
        this.db = db;
    }

    public void criar() {
        db.execSQL(
                "CREATE TABLE " + NOME_TABELA_NOTICIAS + "(" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        TITULO_NOTICIA + " TEXT NOT NULL," +
                        DATA_NOTICIA + " TEXT NOT NULL," +
                        CONTEUDO_NOTICIA + " TEXT NOT NULL" +
                        ")");
        //todo: falta o id do pais correspondente desta noticia
    }

    public long insert(ContentValues values) {
        return db.insert(NOME_TABELA_NOTICIAS, null, values);
    }

    public Cursor query(String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy) {
        return db.query(NOME_TABELA_NOTICIAS, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    public int update(ContentValues values, String whereClause, String[] whereArgs) {
        return db.update(NOME_TABELA_NOTICIAS, values, whereClause, whereArgs);
    }

    public int delete(String whereClause, String[] whereArgs) {
        return db.delete(NOME_TABELA_NOTICIAS, whereClause, whereArgs);
    }
}
