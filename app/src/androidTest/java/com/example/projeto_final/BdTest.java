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

   private long inserePaciente(BdTabelaPacientes tabelaPacientes, Paciente pacientes){
      long id = tabelaPacientes.insert(Converte.pacienteToContentValues(pacientes));
      assertNotEquals(-1, id);
      return id;
   }

    private long inserePaciente(SQLiteDatabase tabelaPacientes, String nome, String genero, Integer pais, String data_aniversario, String doente_cronico, String estado_atual, String data_estado_atual){
      BdTabelaPaises tabelaPaises = new BdTabelaPaises(tabelaPacientes)

      inserePais(tabelaPaises, "Portugal", 9182233);

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

      Cursor cursor1 = tabelaPaises.query(new String[]{"_id"}, "nome_pais =?", new String[]{"Portugal"}, null,null,null);
      Integer id_pais = -1;
      if(cursor1 != null && cursor1.moveToFirst())
        id_pais = cursor1.getInt(cursor1.getColumnIndex("_id"));

      inserePaciente(tabelaPacientes, "Rodrigo Afonso Almeida", "Masculino", id_pais, "20/03/1999", "Não", "Infetado", "01/07/2020");
      cursor = tabelaPacientes.query(BdTabelaPacientes.TODOS_CAMPOS_PACIENTES, null,null,null,null,null);
      assertEquals(registos + 1, cursor.getCount());
      cursor.close();
      db.close();
    }

    @Test
    public void consegueAlterarDoente(){
       Context appContext = getTargetContext();

       BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
       SQLiteDatabase bdPaciente = openHelper.getWritableDatabase();

       BdTabelaPacientes tabelaPacientes = new BdTabelaPacientes(bdPaciente);
       BdTabelaPaises tabelaPaises = new BdTabelaPaises(bdPaciente);

        Paciente paciente = new Paciente();
        paciente.setNome("Antonio Marques");
        paciente.setGenero("Masculino");
        Integer id_pais = tabelaPaises.query(new String[]{"_id"}, "nome_pais = ?", new String[]{"Portugal"}, null,null,null).getColumnIndex("_id");
        paciente.setId_Pais(id_pais);
        paciente.setData_aniversario("15/02/2000");
        paciente.setDoente_cronico("Sim");
        paciente.setEstado_atual("Óbito");
        paciente.setData_estado_atual("29/07/2020");

        long id = inserePaciente(tabelaPacientes, paciente);

        paciente.setNome("Antonio Marques Saraiva");
        paciente.setGenero("Masculino");
        id_pais = tabelaPaises.query(new String[]{"_id"}, "nome_pais = ?", new String[]{"Portugal"}, null,null,null).getColumnIndex("_id");
        paciente.setId_Pais(id_pais);
        paciente.setData_aniversario("15/02/2000");
        paciente.setDoente_cronico("Sim");
        paciente.setEstado_atual("Óbito");
        paciente.setData_estado_atual("29/07/2020");

        int registosAlterados = tabelaPacientes.update(Converte.pacienteToContentValues(paciente), BdTabelaPacientes._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosAlterados);
        bdPaciente.close();
    }

    @Test
    public void consegueApagarPaciente(){
       Context appContext = getTargetContext();

       BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
       SQLiteDatabase db = openHelper.getWritableDatabase();

       BdTabelaPacientes tabelaPacientes = new BdTabelaPacientes(db);
       BdTabelaPaises tabelaPaises = new BdTabelaPaises(db);

       Integer id_pais = tabelaPaises.query(new String[]{"_id"}, "nome_pais = ?", new String[]{"Portugal"}, null,null,null).getColumnIndex("_id");
       long id = inserePaciente(tabelaPacientes, "Joana Marques", "Feminino", id_pais, "19/06/2000", "Não", "Infetada", "29/07/2020");
       int registosApagados = tabelaPacientes.delete(BdTabelaPacientes._ID + "=?", new String[]{String.valueOf(id)});
       assertEquals(1, registosApagados);
       db.close();
    }

    @Test
    public void consegueLerPaciente(){
       Context appContext = getTargetContext();

       BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
       SQLiteDatabase db = openHelper.getWritableDatabase();
       BdTabelaPacientes tabelaPacientes = new BdTabelaPacientes(db);
       BdTabelaPaises tabelaPaises = new BdTabelaPaises(db);

       Cursor cursor = tabelaPacientes.query(BdTabelaPacientes.TODOS_CAMPOS_PACIENTES, null,null,null,null,null);
       int registos = cursor.getCount();
       cursor.close();
       Integer id_pais = tabelaPaises.query(new String[]{"_id"}, "nome_pais = ?", new String[]{"Portugal"}, null, null, null).getColumnIndex("_id");

       inserePaciente(tabelaPacientes, "Ruben Almeida Gomes", "Masculino", id_pais, "12/12/2010", "Não", "Recuperado", "01/07/2020");
       cursor = tabelaPacientes.query(BdTabelaPacientes.TODOS_CAMPOS_PACIENTES, null, null, null, null, null);
       assertEquals(registos + 1, cursor.getCount());
       cursor.close();
       db.close();
    }

    private long insereNoticia(BdTabelaNoticias tabelaNoticias, Noticia noticia){
       long id = tabelaNoticias.insert(Converte.noticiaToContentValues(noticia));
       assertNotEquals(-1, id);
       return id;
    }

    private long insereNoticia(BdTabelaNoticias tabelaNoticias, String titulo, String data, String conteudo,Integer pais){
       Noticia noticia = new Noticia();
       noticia.setTitulo(titulo);
       noticia.setData(data);
       noticia.setConteudo(conteudo);
       noticia.setId_Pais(pais);

       return insereNoticia(tabelaNoticias, noticia);
    }

    @Test
    public void consegueInserirNoticia(){
       Context appcontext = getTargetContext();

       BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appcontext);
       SQLiteDatabase db = openHelper.getWritableDatabase();

       BdTabelaPaises tabelaPaises = new BdTabelaPaises(db);
       BdTabelaNoticias tabelaNoticias = new BdTabelaNoticias(db);
       getTableAsString(db, "noticias");

       Cursor cursor = tabelaNoticias.query(BdTabelaNoticias.TODOS_CAMPOS_NOTICIAS, null,null,null,null,null);
       int registos = cursor.getCount();
       cursor.close();

       Cursor cursor1 = tabelaPaises.query(new String[]{"_id"}, "nome_pais = ?", new String[]{"Portugal"}, null, null, null);
       Integer id_pais = cursor1.getColumnIndex("_id");

       insereNoticia(tabelaNoticias, "Novos Infetados Em Portugal", "01/07/2020", "Foram diagnosticados novos 300 casos de COVID-19 em Portugal nas ultimas 24 horas.", id_pais);
       cursor = tabelaNoticias.query(BdTabelaNoticias.TODOS_CAMPOS_NOTICIAS,null,null,null,null,null);
       assertEquals(registos + 1, cursor.getCount());
       cursor.close();
       db.close();
    }

    @Test
    public void consegueAlterarNoticia(){
       Context appContext = getTargetContext();

       BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
       SQLiteDatabase bdNoticia = openHelper.getWritableDatabase();

       BdTabelaNoticias tabelaNoticias = new BdTabelaNoticias(bdNoticia);
       BdTabelaPaises tabelaPaises = new BdTabelaPaises(bdNoticia);

       Noticia noticia = new Noticia();
       noticia.setTitulo("10 Mortos em Portugal");
       noticia.setData("18/05/2020");
       noticia.setConteudo("Faleceram 10 pessoas em Portugal no dia de hoje (18/05/2020) devido ao COVID-19");
       Integer id_pais = tabelaPaises.query(new String[]{"_id"}, "nome_pais = ?", new String[]{"Portugal"}, null,null,null).getColumnIndex("_id");
       noticia.setId_Pais(id_pais);

       long id = insereNoticia(tabelaNoticias, noticia);

       noticia.setTitulo("10 Óbitos em Portugal");
       noticia.setData("18/05/2020");
       noticia.setConteudo("Faleceram 10 pessoas em Portugal no dia de hoje (18/05/2020) devido ao COVID-19");
       id_pais = tabelaPaises.query(new String[]{"_id"}, "nome_pais = ?", new String[]{"Portugal"}, null,null,null).getColumnIndex("_id");
       noticia.setId_Pais(id_pais);

       int registosAlterados = tabelaNoticias.update(Converte.noticiaToContentValues(noticia), BdTabelaNoticias._ID + "=?", new String[]{String.valueOf(id)});
       assertEquals(1, registosAlterados);
       bdNoticia.close();
    }

    @Test
    public void consegueApagarNoticia(){
       Context appContext = getTargetContext();
       BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
       SQLiteDatabase db = openHelper.getWritableDatabase();

       BdTabelaNoticias tabelaNoticias = new BdTabelaNoticias(db);
       BdTabelaPaises tabelaPaises = new BdTabelaPaises(db);

       Integer id_pais = tabelaPaises.query(new String[]{"_id"}, "nome_pais = ?", new String[]{"Portugal"}, null,null,null).getColumnIndex("_id");
       long id  = insereNoticia(tabelaNoticias, "Diminuição de Casos de COVID-19", "10/02/2020", "Melhoria drástica no número de casos em Portugal", id_pais);
       int registosApagados = tabelaNoticias.delete(BdTabelaNoticias._ID + "=?", new String[]{String.valueOf(id)});
       assertEquals(1, registosApagados);
       db.close();
    }

    @Test
    public void consegueLerNoticia(){
       Context appContext = getTargetContext();

       BdPacientesOpenHelper openHelper = new BdPacientesOpenHelper(appContext);
       SQLiteDatabase db = openHelper.getWritableDatabase();
       BdTabelaNoticias tabelaNoticias = new BdTabelaNoticias(db);
       BdTabelaPaises tabelaPaises = new BdTabelaPaises(db);

       Cursor cursor = tabelaNoticias.query(BdTabelaNoticias.TODOS_CAMPOS_NOTICIAS,null,null,null,null,null);
       int registos = cursor.getCount();
       cursor.close();
       Integer id_pais = tabelaPaises.query(new String[]{"_id"}, "nome_pais = ?", new String[]{"Portugal"}, null,null,null).getColumnIndex("_id");

       insereNoticia(tabelaNoticias, "300 Recuperados", "01/07/2020", "300 novos recuperados em Portugal contando agora com 10000 recuperados no total", id_pais);
       cursor = tabelaNoticias.query(BdTabelaNoticias.TODOS_CAMPOS_NOTICIAS, null,null,null,null,null);
       assertEquals(registos + 1, cursor.getCount());
       cursor.close();
       db.close();
    }
}