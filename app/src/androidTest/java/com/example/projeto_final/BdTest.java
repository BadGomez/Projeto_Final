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

    public Context getTargetContext() {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    private long inserePais(BdTabelaPaises tabelaPaises, Pais pais){
      long id = tabelaPaises.insert(Converte.paisToContentValues(pais));
      assertNotEquals(-1, id);

      return id;
    }

    private long inserePais(BdTabelaPaises tabelaPaises, String nome_pais, int numeroPopulacao){
      Pais pais = new Pais();
      pais.setNome_pais(nome_pais);
      pais.setNumeroPopulacao(numeroPopulacao);

      return inserePais(tabelaPaises, pais);
    }

    @Test
    public void consegueInterirPaises(){
      Context appContext = getTargetContext();

      BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
      SQLiteDatabase bd = openHelper.getWritableDatabase();

      BdTabelaPaises tabelaPaises = new BdTabelaPaises(bd);

      inserePais(tabelaPaises, "Portugal", 1839123);

      bd.close();
    }

    @Test
    public void consegueLerPaises(){
      Context appContext = getTargetContext();
      BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
      SQLiteDatabase bd = openHelper.getWritableDatabase();

      BdTabelaPaises tabelaPaises = new BdTabelaPaises(bd);

      Cursor cursor = tabelaPaises.query(BdTabelaPaises.TODOS_CAMPOS_PAIS, null,null,null,null,null);
      int registos = cursor.getCount();
      cursor.close();

      inserePais(tabelaPaises, "Espanha", 123123321);

      cursor = tabelaPaises.query(BdTabelaPaises.TODOS_CAMPOS_PAIS, null,null,null,null,null);
      assertEquals(registos + 1, cursor.getCount());
      cursor.close();

      bd.close();
    }

    @Test
    public void consegueAlterarPaises(){
      Context appContext = getTargetContext();

      BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
      SQLiteDatabase bd = openHelper.getWritableDatabase();

      BdTabelaPaises tabelaPaises = new BdTabelaPaises(bd);

      Pais pais = new Pais();
      pais.setNome_pais("Franc");
      pais.setNumeroPopulacao(3219301);

      long id = inserePais(tabelaPaises, pais);

      pais.setNome_pais("França");
      pais.setNumeroPopulacao(3219301);
      int registosAfetados = tabelaPaises.update(Converte.paisToContentValues(pais), BdTabelaPaises._ID + "=?", new String[]{String.valueOf(id)});
      assertEquals(1, registosAfetados);

      bd.close();
    }

    @Test
  public void consegueEliminarPaises(){
    Context appContext = getTargetContext();

    BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
    SQLiteDatabase bd = openHelper.getWritableDatabase();

    BdTabelaPaises tabelaPaises = new BdTabelaPaises(bd);

    long id = inserePais(tabelaPaises, "Teste", 12312123);

    int registosEliminados = tabelaPaises.delete(BdTabelaPaises._ID + "=?", new String[]{String.valueOf(id)});
    assertEquals(1, registosEliminados);

    bd.close();
    }

    private long inserePaciente(SQLiteDatabase bd, String nome, String genero, String data_aniversario, String doente_cronico, String estado_atual, String data_estado_atual, String NomeNome_pais, int  Pop_populacao){
      BdTabelaPaises tabelaPaises = new BdTabelaPaises(bd);

      long idPais = inserePais(tabelaPaises, NomeNome_pais, Pop_populacao);

      Paciente paciente = new Paciente();
      paciente.setNome(nome);
      paciente.setId_Pais(idPais);
      paciente.setGenero(genero);
      paciente.setData_aniversario(data_aniversario);
      paciente.setDoente_cronico(doente_cronico);
      paciente.setEstado_atual(estado_atual);
      paciente.setData_estado_atual(data_estado_atual);

      BdTabelaPacientes tabelaPacientes = new BdTabelaPacientes(bd);
      long id = tabelaPacientes.insert(Converte.pacienteToContentValues(paciente));
      assertNotEquals(-1, id);

      return id;
    }

    @Test
    public void consegueInserirPacientes(){
      Context appContext = getTargetContext();

      BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
      SQLiteDatabase bd = openHelper.getWritableDatabase();

      inserePaciente(bd, "Valter Simões", "Masculino", "11/05/2000", "Não", "Recuperado", "20/05/2020", "Portugal", 10231230);

      bd.close();
    }

    @Test
    public void consegueLerPacientes(){
      Context appContext = getTargetContext();
      BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
      SQLiteDatabase bd = openHelper.getWritableDatabase();

      BdTabelaPacientes tabelaPacientes = new BdTabelaPacientes(bd);

      Cursor cursor = tabelaPacientes.query(BdTabelaPacientes.TODOS_CAMPOS_PACIENTES, null,null,null,null,null);
      int registos = cursor.getCount();
      cursor.close();

      inserePaciente(bd, "Miguel Durães", "Masculino", "31/07/1999", "Não", "Recuperado", "04/07/2020", "Espanha", 9103821);

      cursor = tabelaPacientes.query(BdTabelaPacientes.TODOS_CAMPOS_PACIENTES, null,null,null,null,null);
      assertEquals(registos + 1, cursor.getCount());
      cursor.close();

      bd.close();
    }

    @Test
    public void consegueAlterarPaciente(){
      Context appContext = getTargetContext();

      BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
      SQLiteDatabase bd = openHelper.getWritableDatabase();

      long idPaciente = inserePaciente(bd, "Gonçalo Saraiva", "Masculino", "18/03/2000", "Sim", "Infetado", "04/07/2020", "França", 123123123);

      BdTabelaPacientes tabelaPacientes = new BdTabelaPacientes(bd);

      Cursor cursor = tabelaPacientes.query(BdTabelaPacientes.TODOS_CAMPOS_PACIENTES, BdTabelaPacientes.CAMPO_ID_COMPLETO + "=?", new String[]{ String.valueOf(idPaciente)}, null,null,null);
      assertEquals(1, cursor.getCount());

      assertTrue(cursor.moveToNext());
      Paciente paciente = Converte.cursorToPaciente(cursor);
      cursor.close();

      assertEquals("Gonçalo Saraiva", paciente.getNome());

      paciente.setNome("Gonçalo Sebastião Saraiva");
      int registosAfetados = tabelaPacientes.update(Converte.pacienteToContentValues(paciente), BdTabelaPacientes.CAMPO_ID_COMPLETO + "=?", new String[]{String.valueOf(paciente.getId())});
      assertEquals(1, registosAfetados);

      bd.close();
    }

    @Test
    public void consegueEliminarPacientes(){
      Context appContext = getTargetContext();

      BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
      SQLiteDatabase bd = openHelper.getWritableDatabase();

      long id = inserePaciente(bd, "Maria Almeida", "Feminino", "01/04/2000", "Sim", "Infetado", "04/07/2020", "Portugal", 3103123);

      BdTabelaPacientes tabelaPacientes = new BdTabelaPacientes(bd);
      int registosEliminados = tabelaPacientes.delete(BdTabelaPacientes._ID + "=?", new String[]{String.valueOf(id)});
      assertEquals(1, registosEliminados);

      bd.close();
  }
}