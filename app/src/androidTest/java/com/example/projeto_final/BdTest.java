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
}