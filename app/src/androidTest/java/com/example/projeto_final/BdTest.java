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

    private Context getTargetContext() {
       return InstrumentationRegistry.getInstrumentation().getTargetContext();
   }

   private long inserePais (BdTabelaPaises tabelaPaises, Pais pais){
      long id = tabelaPaises.insert(Converte.paisToContentValues(pais));
      assertNotEquals(-1, id);
      return id;
   }

   private long inserePais(BdTabelaPaises tabelaPaises, String nome, Integer numeroPopulacao){
      Pais pais = new Pais();
        pais.setNome(nome);
        pais.setNumeroPopulacao(numeroPopulacao);
    return inserePais(tabelaPaises, pais);
   }

   @Test
    public void consegueInserirPais(){
      Context appContext = getTargetContext();
      BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
      SQLiteDatabase bd = openHelper.getWritableDatabase();

      BdTabelaPaises tabelaPaises = new BdTabelaPaises(bd);
      inserePais(tabelaPaises, "Portugal", 9182233);

       bd.close();
   }

   @Test
    public void consegueLerPais(){
      Context appContext = getTargetContext();
      BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
      SQLiteDatabase bd = openHelper.getWritableDatabase();

      BdTabelaPaises tabelaPaises = new BdTabelaPaises(bd);

      Cursor cursor = tabelaPaises.query(BdTabelaPaises.TODOS_CAMPOS_PAIS, null, null, null, null, null);
      int numeroPaises = cursor.getCount();
      cursor.close();

      inserePais(tabelaPaises, "Espanha", 2319302);

      cursor = tabelaPaises.query(BdTabelaPaises.TODOS_CAMPOS_PAIS, null, null, null, null ,null);
      assertEquals(numeroPaises + 1, cursor.getCount());
      cursor.close();

      bd.close();
   }

   @Test
    public void consegueEditarPais(){
       Context appContext = getTargetContext();
       BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
       SQLiteDatabase bd = openHelper.getWritableDatabase();

       BdTabelaPaises tabelaPaises = new BdTabelaPaises(bd);

       Pais pais = new Pais();
       pais.setNome("Alemanha");
       pais.setNumeroPopulacao(91029239);

       long id = inserePais(tabelaPaises, pais);

       pais.setNome("Alemanha");
       pais.setNumeroPopulacao(90129239);

       int registosAlteradosPais = tabelaPaises.update(Converte.paisToContentValues(pais), BdTabelaPaises._ID + "=?", new String[]{String.valueOf(id)});
       assertEquals(1, registosAlteradosPais);

       bd.close();
   }

   @Test
    public void conseguesApagarPais(){
       Context appContext = getTargetContext();
       BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
       SQLiteDatabase bd = openHelper.getWritableDatabase();

       BdTabelaPaises tabelaPaises = new BdTabelaPaises(bd);
       long id = inserePais(tabelaPaises, "China", 20910202);

       int registoApagadoPais = tabelaPaises.delete(BdTabelaPaises._ID + "=?", new String[]{String.valueOf(id)});
       assertEquals(1, registoApagadoPais);

       bd.close();
   }

/*
  private long inserePaciente(SQLiteDatabase bd, String nome, String descpais , String genero, String data_aniversario, String doente_cronico, String estado_atual, String data_estado_atual){
      //todo: String pais tem que ser alterado (exprof tem descCategoria)
      BdTabelaPaises tabelaPaises = new BdTabelaPaises(bd);

      long idPais = inserePais(tabelaPaises, descpais);

        Paciente paciente = new Paciente()
        paciente.setNome(nome);
        paciente.setId(pais);
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

      Cursor cursor = tabelaPacientes.query(BdTabelaPacientes.TODOS_CAMPOS_PACIENTE, null, null, null, null, null);
      int numeroPacientes = cursor.getCount();
      cursor.close();

      inserePaciente(tabelaPacientes, "João António Faria", "Portugal" , "Masculino", "17/12/1987", "Sim", "Infetado", "02/06/2000");

      cursor = tabelaPacientes.query(BdTabelaPacientes.TODOS_CAMPOS_PACIENTE, null, null, null, null, null);
      assertEquals(numeroPacientes + 1, cursor.getCount());
      cursor.close();

      bd.close();
  }

  @Test
    public void consegueEditarPaciente(){
      Context appContext = getTargetContext();
      BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
      SQLiteDatabase bd = openHelper.getWritableDatabase();

      BdTabelaPacientes tabelaPacientes = new BdTabelaPacientes(bd);

      Paciente paciente = new Paciente();
      paciente.setNome("Francisca Miranda Alonso");
      paciente.setPais("Espanha");
      paciente.setGenero("Feminino");
      paciente.setData_Aniversario("09/08/1999");
      paciente.setDoente_Cronico("Não");
      paciente.setEstado_Atual("Infetado");
      paciente.setData_Estado_Atual("03/06/2020");

      long id = inserePaciente(tabelaPacientes, paciente);

      paciente.setNome("Francisca Miranda Alonso");
      paciente.setPais("Espanha");
      paciente.setGenero("Feminino");
      paciente.setData_Aniversario("09/08/1999");
      paciente.setDoente_Cronico("Sim");
      paciente.setEstado_Atual("Infetado");
      paciente.setData_Estado_Atual("03/06/2020");

      int registoAlterado = tabelaPacientes.update(Converte.pacienteToContentValues(paciente), BdTabelaPacientes._ID + "=?", new String[]{String.valueOf(id)});
      assertEquals(1, registoAlterado);

      bd.close();
  }

  @Test
    public void consegueApagarPaciente(){
      Context appContext = getTargetContext();

      BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
      SQLiteDatabase bd = openHelper.getWritableDatabase();

      BdTabelaPacientes tabelaPacientes = new BdTabelaPacientes(bd);

      long id = inserePaciente(tabelaPacientes, "Gonçalo Sebastião Saraiva","Portugal", "Masculino", "15/02/2000", "Não", "Recuperado", "25/05/2020");

      int registoApagado = tabelaPacientes.delete(BdTabelaPacientes._ID + "=?", new String[]{String.valueOf(id)});
      assertEquals(1, registoApagado);

      bd.close();
  } */
}