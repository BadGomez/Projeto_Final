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

    public String getTableAsString(SQLiteDatabase db, String tableName) {
        String tableString = String.format("Table %s:\n", tableName);
        Cursor allRows = db.rawQuery("SELECT * FROM " + tableName, null);
        if (allRows.moveToFirst()) {
            String[] columnNames = allRows.getColumnNames();
            do {
                for (String name : columnNames) {
                    tableString += String.format("%s: %s\n", name,
                            allRows.getString(allRows.getColumnIndex(name)));
                }
                tableString += "\n";

            } while (allRows.moveToNext());
        }
        return tableString;
    }

  @Test
  public void consegueAbrirBaseDados(){
      Context appContext = getTargetContext();

      BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
      SQLiteDatabase bd = openHelper.getReadableDatabase();
      getTableAsString(bd, "pais");
      assertTrue(bd.isOpen());
      bd.close();
  }

    private Context getTargetContext() {
       return InstrumentationRegistry.getInstrumentation().getTargetContext();
   }

   /*private long inserePais (BdTabelaPaises tabelaPaises, Pais pais){
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
   }*/

   private  long inserePaciente(BdTabelaPacientes tabelaPacientes, Paciente pacientes){
      long id = tabelaPacientes.insert(Converte.pacienteToContentValues(pacientes));
      assertNotEquals(-1, id);
      return id;
   }

    private long inserePaciente(BdTabelaPacientes tabelaPacientes, String nome, String genero, Integer pais, String data_aniversario, String doente_cronico, String estado_atual, String data_estado_atual){
      Paciente pacientes = new Paciente();
      pacientes.setNome(nome);
      pacientes.setGenero(genero);
      pacientes.setId_Pais(pais);
      pacientes.setData_aniversario(data_aniversario);
      pacientes.setDoente_cronico(doente_cronico);
      pacientes.setEstado_atual(estado_atual);
      pacientes.setData_estado_atual(data_estado_atual);

      return inserePaciente(tabelaPacientes, pacientes);
  }

    @Test
    public void consegueInserirPaciente(){
      Context appcontext = getTargetContext();

      BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appcontext);
      SQLiteDatabase db = openHelper.getWritableDatabase();
      BdTabelaPaises tabelaPaises = new BdTabelaPaises(db);
      BdTabelaPacientes tabelaPacientes = new BdTabelaPacientes(db);
      getTableAsString(db, "pais");
      Cursor cursor = tabelaPacientes.query(BdTabelaPacientes.TODOS_CAMPOS_PACIENTES, null,null,null,null,null);
      int registos = cursor.getCount();
      cursor.close();
      Cursor cursor1 = tabelaPaises.query(new String[]{"_id"}, "nome_pais = ?", new String[]{"Portugal"}, null,null,null);
      Integer id_pais = cursor1.getColumnIndex("_id");
      inserePaciente(tabelaPacientes, "Rodrigo Afonso Almeida", "Masculino", id_pais, "20/03/1999", "Não", "Infetado", "01/07/2020");
      cursor = tabelaPacientes.query(BdTabelaPacientes.TODOS_CAMPOS_PACIENTES, null,null,null,null,null);
      assertEquals(registos + 1, cursor.getCount());
      cursor.close();
      db.close();
    }
}