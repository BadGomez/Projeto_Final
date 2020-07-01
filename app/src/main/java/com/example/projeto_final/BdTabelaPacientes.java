package com.example.projeto_final;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTabelaPacientes implements BaseColumns {
    public static final String NOME_TABELA = "pacientes";
    public static final String NOME_PACIENTE = "nome";
    public static final String PAIS_PACIENTE ="pais";
    public static final String GENERO_PACIENTE ="genero";
    public static final String DATA_NASCIMENTO_PACIENTE = "data_aniversario";
    public static final String DOENCA_CRONICA_PACIENTE = "doente_cronico";
    public static final String ESTADO_ATUAL_PACIENTE = "estado_atual";
    public static final String DATA_ESTADO_ATUAL_PACIENTE = "data_estado_atual";
    public static final String CAMPO_ID_PAIS = "id_pais";

    public static final String CAMPO_ID_COMPLETO = NOME_TABELA + "." + _ID;
    public static final String NOME_PACIENTE_COMPLETO = NOME_TABELA + "." + NOME_PACIENTE;
    public static final String PAIS_PACIENTE_COMPLETO = NOME_TABELA + "." + PAIS_PACIENTE;
    public static final String GENERO_PACIENTE_COMPLETO = NOME_TABELA + "." + GENERO_PACIENTE;
    public static final String DATA_NASCIMENTO_PACIENTE_COMPLETO = NOME_TABELA + "." + DATA_NASCIMENTO_PACIENTE;
    public static final String DOENCA_CRONICA_PACIENTE_COMPLETO = NOME_TABELA + "." + DOENCA_CRONICA_PACIENTE;
    public static final String ESTADO_ATUAL_PACIENTE_COMPLETO = NOME_TABELA + "." + ESTADO_ATUAL_PACIENTE;
    public static final String DATA_ESTADO_ATUAL_PACIENTE_COMPLETO = NOME_TABELA + "." + DATA_ESTADO_ATUAL_PACIENTE;
    public static final String CAMPO_ID_PAIS_COMPLETO = BdTabelaPaises.CAMPO_ID_COMPLETO + " AS " + PAIS_PACIENTE;

    public static final String[] TODOS_CAMPOS_PACIENTES = {NOME_PACIENTE_COMPLETO, GENERO_PACIENTE_COMPLETO, DATA_NASCIMENTO_PACIENTE_COMPLETO, DOENCA_CRONICA_PACIENTE_COMPLETO,ESTADO_ATUAL_PACIENTE_COMPLETO, DATA_ESTADO_ATUAL_PACIENTE_COMPLETO};


    private SQLiteDatabase db;
    public BdTabelaPacientes(SQLiteDatabase db) {
        this.db = db;
    }

    public void criar() {
        db.execSQL(
                "CREATE TABLE " + NOME_TABELA + "(" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                        NOME_PACIENTE + " TEXT NOT NULL," +
                        GENERO_PACIENTE + " TEXT NOT NULL," +
                        DATA_NASCIMENTO_PACIENTE + " TEXT NOT NULL," +
                        DOENCA_CRONICA_PACIENTE + " TEXT NOT NULL," +
                        ESTADO_ATUAL_PACIENTE + " TEXT NOT NULL," +
                        DATA_ESTADO_ATUAL_PACIENTE + " TEXT NOT NULL," +
                        CAMPO_ID_PAIS + " INTEGER NOT NULL," +
                        "FOREIGN KEY (" + CAMPO_ID_PAIS + ") REFERENCES " +
                        BdTabelaPaises.NOME_TABELA_PAISES + "(" + BdTabelaPaises._ID + ")" +
                ")");
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
