package com.example.projeto_final;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTabelaPaises implements BaseColumns {

    public static final String NOME_TABELA_PAISES = "pais";
    public static final String NUMERO_INFETADOS = "numeroInfetados";
    public static final String NUMERO_OBITOS = "numeroObitos";
    public static final String NUMERO_RECUPERADOS = "numeroRecuperados";
    public static final String NUMERO_CRITICO = "numeroCritico";
    public static final String NUMERO_DOENTES_CRONICOS = "numeroDoentesCronicos";
    public static final String NUMERO_POPULACAO = "numeroPopulacao";

    private final SQLiteDatabase db;

    public BdTabelaPaises(SQLiteDatabase db) {
        this.db = db;
    }

    public void criar() {
        db.execSQL(
                "CREATE TABLE " + NOME_TABELA_PAISES + "(" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        NUMERO_POPULACAO + " INTEGER," +
                        NUMERO_INFETADOS + " INTEGER," +
                        NUMERO_OBITOS + " INTEGER," +
                        NUMERO_RECUPERADOS + " INTEGER," +
                        NUMERO_CRITICO + " INTEGER," +
                        NUMERO_DOENTES_CRONICOS + " INTEGER" +
                        ")");
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
