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

  // private long inserePaciente(SQLiteDatabase BdTabelaPacientes, String Nome, String Pais, String Genero, String Data_Aniversario, String Doente_Cronico, String Estado_Atual, String Data_Estado_Atual){
  //    BdTabelaPacientes tabelaPacientes = new BdTabelaPacientes(BdTabelaPacientes);
  // }
}
