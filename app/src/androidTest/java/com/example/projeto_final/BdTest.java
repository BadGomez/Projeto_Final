package com.example.projeto_final;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static junit.framework.TestCase.assertTrue;

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
}
