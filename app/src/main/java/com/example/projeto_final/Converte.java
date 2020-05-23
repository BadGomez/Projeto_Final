package com.example.projeto_final;

import android.content.ContentValues;

public class Converte {

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

}
