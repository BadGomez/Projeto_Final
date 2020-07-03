package com.example.projeto_final;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTabelaPaises implements BaseColumns {
    public static final String NOME_TABELA_PAISES = "pais";
    public static final String NOME_PAIS = "nome_pais";
    public static final String NUMERO_POPULACAO = "numeroPopulacao";
    public static final String CAMPO_ID_COMPLETO = NOME_TABELA_PAISES + "." + _ID;
    public static final String[] TODOS_CAMPOS_PAIS = {CAMPO_ID_COMPLETO, NOME_PAIS, NUMERO_POPULACAO};

    private final SQLiteDatabase db;

    public BdTabelaPaises(SQLiteDatabase db) {
        this.db = db;
    }

    public void criar() {
        String sql = "CREATE TABLE " + NOME_TABELA_PAISES + "(" +
                _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                NOME_PAIS + " TEXT NOT NULL," +
                NUMERO_POPULACAO + " INTEGER NOT NULL " +
                ")";

        db.execSQL(sql);
    }

    public long insert(ContentValues values) {
        return db.insert(NOME_TABELA_PAISES, null, values);
    }

    public Cursor query(String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy) {
        return db.query(NOME_TABELA_PAISES, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    public int update(ContentValues values, String whereClause, String[] whereArgs) {
        return db.update(NOME_TABELA_PAISES, values, whereClause, whereArgs);
    }

    public int delete(String whereClause, String[] whereArgs) {
        return db.delete(NOME_TABELA_PAISES, whereClause, whereArgs);
    }
}
