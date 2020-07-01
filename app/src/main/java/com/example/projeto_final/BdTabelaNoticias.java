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
    public static final String CAMPO_ID_PAIS ="id_pais";

    public static final String CAMPO_ID_COMPLETO = NOME_TABELA_NOTICIAS + "." + _ID;
    public static final String PAIS_NOTICIA_COMPLETO = NOME_TABELA_NOTICIAS + "." + PAIS_NOTICIA;
    public static final String TITULO_NOTICIA_COMPLETO = NOME_TABELA_NOTICIAS + "." + TITULO_NOTICIA;
    public static final String DATA_NOTICIA_COMPLETO = NOME_TABELA_NOTICIAS + "." + DATA_NOTICIA;
    public static final String CONTEUDO_NOTICIA_COMPLETO = NOME_TABELA_NOTICIAS + "." + CONTEUDO_NOTICIA;
    //public static final String CAMPO_ID_PAIS_COMPLETO = NOME_TABELA_NOTICIAS + "." + CAMPO_ID_PAIS;
    public static final String CAMPO_PAIS_COMPLETO = BdTabelaPaises.CAMPO_ID_COMPLETO + " AS " +  PAIS_NOTICIA;

    public static final String[] TODOS_CAMPOS_NOTICIAS = {CAMPO_ID_COMPLETO, TITULO_NOTICIA_COMPLETO, DATA_NOTICIA_COMPLETO, CONTEUDO_NOTICIA_COMPLETO};

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
                        CONTEUDO_NOTICIA + " TEXT NOT NULL," +
                        CAMPO_ID_PAIS + " INTEGER NOT NULL," +
                        "FOREIGN KEY (" + CAMPO_ID_PAIS + ") REFERENCES " +
                        BdTabelaPaises.NOME_TABELA_PAISES + "(" + BdTabelaPaises._ID + ")" +
                        ")");
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
