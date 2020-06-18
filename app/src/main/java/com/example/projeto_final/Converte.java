package com.example.projeto_final;

import android.content.ContentValues;

public class Converte {

    public static ContentValues paisToContentValues(Pais pais){
        ContentValues valores = new ContentValues();
            valores.put(BdTabelaPaises.NOME_PAIS, pais.getNome());
            valores.put(BdTabelaPaises.NUMERO_POPULACAO, pais.getNumeroPopulacao());
        return valores;
    }

    public static Pais contentValuesToPais(ContentValues valores){
        Pais pais = new Pais();
            pais.setId(valores.getAsLong(BdTabelaPaises._ID));
            pais.setNome(valores.getAsString(BdTabelaPaises.NOME_PAIS));
            pais.setNumeroPopulacao(valores.getAsInteger(BdTabelaPaises.NUMERO_POPULACAO));
        return pais;
    }

    public static ContentValues pacienteToContentValues(Paciente paciente){
        ContentValues valores = new ContentValues();
            valores.put(BdTabelaPacientes.NOME_PACIENTE,paciente.getNome());
            valores.put(BdTabelaPacientes.PAIS_PACIENTE,paciente.getPais());
            valores.put(BdTabelaPacientes.GENERO_PACIENTE,paciente.getGenero());
            valores.put(BdTabelaPacientes.DATA_NASCIMENTO_PACIENTE,paciente.getData_Aniversario());
            valores.put(BdTabelaPacientes.DOENCA_CRONICA_PACIENTE,paciente.getDoente_Cronico());
            valores.put(BdTabelaPacientes.ESTADO_ATUAL_PACIENTE,paciente.getEstado_Atual());
            valores.put(BdTabelaPacientes.DATA_ESTADO_ATUAL_PACIENTE,paciente.getData_Estado_Atual());
        return valores;
    }

    public static Paciente contentValuesToPacientes(ContentValues valores){
        Paciente paciente = new Paciente();
            paciente.setId(valores.getAsLong(BdTabelaPacientes._ID));
            paciente.setNome(valores.getAsString(BdTabelaPacientes.NOME_PACIENTE));
            paciente.setPais(valores.getAsString(BdTabelaPacientes.PAIS_PACIENTE));
            paciente.setGenero(valores.getAsString(BdTabelaPacientes.GENERO_PACIENTE));
            paciente.setData_Aniversario(valores.getAsString(BdTabelaPacientes.DATA_NASCIMENTO_PACIENTE));
            paciente.setDoente_Cronico(valores.getAsString(BdTabelaPacientes.DOENCA_CRONICA_PACIENTE));
            paciente.setEstado_Atual(valores.getAsString(BdTabelaPacientes.ESTADO_ATUAL_PACIENTE));
            paciente.setData_Estado_Atual(valores.getAsString(BdTabelaPacientes.DATA_ESTADO_ATUAL_PACIENTE));
        return paciente;
    }

    public static ContentValues noticiaToContentValues(Noticia noticia){
        ContentValues valores = new ContentValues();

        valores.put(BdTabelaNoticias.CAMPO_ID_PAIS, noticia.getId_Pais());
        valores.put(BdTabelaNoticias.TITULO_NOTICIA, noticia.getTitulo());
        valores.put(BdTabelaNoticias.DATA_NOTICIA, noticia.getData());
        valores.put(BdTabelaNoticias.CONTEUDO_NOTICIA, noticia.getConteudo());

        return valores;
    }

    public static Noticia contentValuesToNoticia(ContentValues valores){
        Noticia noticia = new Noticia();

        noticia.setId_Pais(valores.getAsLong(BdTabelaNoticias.CAMPO_ID_PAIS));
        noticia.setId(valores.getAsLong(BdTabelaNoticias._ID));
        noticia.setTitulo(valores.getAsString(BdTabelaNoticias.TITULO_NOTICIA));
        noticia.setData(valores.getAsString(BdTabelaNoticias.DATA_NOTICIA));
        noticia.setConteudo(valores.getAsString(BdTabelaNoticias.CONTEUDO_NOTICIA));

        return noticia;
    }

   /* public static Paciente cursorToPaciente(Cursor cursor){
        Paciente paciente = new Paciente();
            paciente.setId(cursor.getLong(cursor.getColumnIndex(BdTabelaPacientes._ID)));
            paciente.setNome(cursor.getString(cursor.getColumnIndex(BdTabelaPacientes.NOME_PACIENTE)));
            paciente.setPais(cursor.getString(cursor.getColumnIndex(BdTabelaPacientes.PAIS_PACIENTE)));
            paciente.setGenero(cursor.getString(cursor.getColumnIndex(BdTabelaPacientes.GENERO_PACIENTE)));
            paciente.setData_Aniversario(cursor.getString(cursor.getColumnIndex(BdTabelaPacientes.DATA_NASCIMENTO_PACIENTE)));
            paciente.setDoente_Cronico(cursor.getString(cursor.getColumnIndex(BdTabelaPacientes.DOENCA_CRONICA_PACIENTE)));
            paciente.setEstado_Atual(cursor.getString(cursor.getColumnIndex(BdTabelaPacientes.ESTADO_ATUAL_PACIENTE)));
            paciente.setData_Estado_Atual(cursor.getString(cursor.getColumnIndex(BdTabelaPacientes.DATA_ESTADO_ATUAL_PACIENTE)));
        return paciente;
    }
    */
}
