package com.example.projeto_final;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

//import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class BdTest {
  @Before
  @After
  public void apagarBaseDados(){
      getTargetContext().deleteDatabase(BdPacientesOpenHelper.NOME_BASE_DADOS);
  }

  @Test
  public void consegueAbrirBaseDados(){
      Context appContext = getTargetContext();

      BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
      SQLiteDatabase bd = openHelper.getReadableDatabase();
      assertTrue(bd.isOpen());
      bd.close();
  }

    private Context getTargetContext() {
       return InstrumentationRegistry.getInstrumentation().getTargetContext();
   }

   private long inserePaciente(BdTabelaPacientes tabelaPacientes, Paciente paciente) {
        long id = tabelaPacientes.insert(Converte.pacienteToContentValues(paciente));
        assertNotEquals(-1, id);

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
      return inserePaciente(tabelaPacientes, paciente);
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

  @Test
    public void consegueLerPaciente(){
      Context appContext = getTargetContext();
      BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
      SQLiteDatabase bd = openHelper.getWritableDatabase();

      BdTabelaPacientes tabelaPacientes = new BdTabelaPacientes(bd);

      Cursor cursor = tabelaPacientes.query(BdTabelaPacientes.TODOS_CAMPOS, null, null, null, null, null);
      int numeroPacientes = cursor.getCount();
      cursor.close();

      inserePaciente(tabelaPacientes, "João António Faria", "Portugal" , "Masculino", "17/12/1987", "Sim", "Infetado", "02/06/2000");

      cursor = tabelaPacientes.query(BdTabelaPacientes.TODOS_CAMPOS, null, null, null, null, null);
      assertEquals(numeroPacientes + 1, cursor.getCount());
      cursor.close();

      bd.close();
  }
}