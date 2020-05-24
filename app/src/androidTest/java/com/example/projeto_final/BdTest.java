package com.example.projeto_final;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class BdTest {
  @Before
  public void apagarBD(){
      getTargetContext().deleteDatabase(BdPacientesOpenHelper.NOME_BASE_DADOS);
  }

  @Test
  public void consegueAbrirBD(){
      Context appContext = getTargetContext();

      BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
      SQLiteDatabase bd = openHelper.getReadableDatabase();
      assertTrue(bd.isOpen());
      bd.getClass();
  }

    private Context getTargetContext() {
       return InstrumentationRegistry.getInstrumentation().getTargetContext();
   }

   public long inserePaciente(BdTabelaPacientes tabelaPacientes, Paciente paciente){
        long id = tabelaPacientes.insert(Converte.pacienteToContentValues(paciente));
        assertNotEquals (-1, id);

        return id;
   }

  private long inserePaciente(BdTabelaPacientes tabelaPacientes, String nome, String pais, String genero, String data_aniversario, String doente_cronico, String estado_atual, String data_estado_atual){
      Paciente paciente = new Paciente();
        paciente.setNome(nome);
        paciente.setPais(pais);
        paciente.setGenero(genero);
        paciente.setData_Aniversario(data_aniversario);
        paciente.setDoente_Cronico(doente_cronico);
        paciente.setEstado_Atual(estado_atual);
        paciente.setData_Estado_Atual(data_estado_atual);
      return inserePaciente(tabelaPacientes, nome, pais, genero, data_aniversario, doente_cronico, estado_atual, data_estado_atual);
  }

  @Test
  public void consegueInserirPaciente(){
      Context appContext = getTargetContext();
      BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
      SQLiteDatabase bd = openHelper.getWritableDatabase();

      BdTabelaPacientes tabelaPacientes = new BdTabelaPacientes(bd);
      inserePaciente(tabelaPacientes, "Valter Rodrigues Simões", "Portugal", "Masculino", "11/05/2000", "Não", "Recuperado", "24/05/2020");

      bd.close();
  }
}
