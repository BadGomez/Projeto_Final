package com.example.projeto_final;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTabelaPacientes implements BaseColumns {
    public static final String NOME_TABELA = "Pacientes";
    public static final String NOME_PACIENTE = "Nome";
    public static final String PAIS_PACIENTE ="Pais";
    public static final String GENERO_PACIENTE ="Genero";
    public static final String DATA_NASCIMENTO_PACIENTE = "Data_Aniversario";
    public static final String DOENCA_CRONICA_PACIENTE = "Doente_Cronico";
    public static final String ESTADO_ATUAL_PACIENTE = "Estado_Atual";
    public static final String DATA_ESTADO_ATUAL_PACIENTE = "Data_Estado_Atual";

    private SQLiteDatabase db;
    public BdTabelaPacientes(SQLiteDatabase db) {
        this.db = db;
    }

    public void criar() {
        db.execSQL(
                "CREATE TABLE " + NOME_TABELA + "(" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        NOME_PACIENTE + "TEXT NOT NULL," +
                        PAIS_PACIENTE + "TEXT NOT NULL," +
                        GENERO_PACIENTE + "TEXT NOT NULL," +
                        DATA_NASCIMENTO_PACIENTE + "TEXT NOT NULL," +
                        DOENCA_CRONICA_PACIENTE + "TEXT NOT NULL," +
                        ESTADO_ATUAL_PACIENTE + "TEXT NOT NULL," +
                        DATA_ESTADO_ATUAL_PACIENTE + "TEXT NOT NULL" +
                ")"
        );
    }

    public long insert(ContentValues values){
        return db.insert(NOME_TABELA, null, values);
    }

    public Cursor query(String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy) {
        return db.query(NOME_TABELA, columns, selection, selectionArgs, groupBy, having, orderBy);
    }

    public int update(ContentValues values, String whereClause, String[] whereArgs) {
        return db.update(NOME_TABELA, values, whereClause, whereArgs);
    }

    public int delete(String whereClause, String[] whereArgs) {
        return db.delete(NOME_TABELA, whereClause, whereArgs);
    }
}
