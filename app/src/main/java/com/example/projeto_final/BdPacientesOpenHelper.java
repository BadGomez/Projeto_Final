package com.example.projeto_final;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BdPacientesOpenHelper extends SQLiteOpenHelper {
    public static final String NOME_BASE_DADOS = "TrabalhoFinal.db";
    public static final int VERSAO_BASE_DADOS =1;
    private final Context context;
    private static final boolean DESENVOLVIMENTO = true;

    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *  @param context to use for locating paths to the the database
     *
     */
    public BdPacientesOpenHelper(@Nullable Context context) {
       super(context, NOME_BASE_DADOS, null, VERSAO_BASE_DADOS);
       this.context = context;
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        BdTabelaPacientes tabelaPacientes = new BdTabelaPacientes(db);
        tabelaPacientes.criar();

        BdTabelaPaises tabelaPaises = new BdTabelaPaises(db);
        tabelaPaises.criar();

        BdTabelaNoticias tabelaNoticias = new BdTabelaNoticias(db);
        tabelaNoticias.criar();

        preencheTabelaPaises(tabelaPaises);

        if(DESENVOLVIMENTO){
            seedData(db);
        }
    }

    private void seedData(SQLiteDatabase db){
       BdTabelaPaises tabelaPaises = new BdTabelaPaises(db);

       Pais pais = new Pais();
       pais.setNome_pais("China");
       pais.setNumeroPopulacao(123321);
       long idPaisChina = tabelaPaises.insert(Converte.paisToContentValues(pais));

        pais = new Pais();
        pais.setNome_pais("Japão");
        pais.setNumeroPopulacao(21);
        long idPaisJapao = tabelaPaises.insert(Converte.paisToContentValues(pais));

        BdTabelaNoticias tabelaNoticias = new BdTabelaNoticias(db);

        Noticia noticia = new Noticia();
        noticia.setTitulo("Infetados em Xangai");
        noticia.setData("05/07/2020");
        noticia.setId_Pais(idPaisChina);
        noticia.setConteudo("Trezentos novos casos de Covid-19 na região de Xangai. Estes casos são provenientes de uma fábrica de calçado no centro da cidade. A mesma já se encontra fechada por motivos de segurança.");
        tabelaNoticias.insert(Converte.noticiaToContentValues(noticia));

        noticia = new Noticia();
        noticia.setTitulo("Dez óbitos nas Ultimas 24 horas");
        noticia.setData("01/02/2020");
        noticia.setId_Pais(idPaisJapao);
        noticia.setConteudo("Nas ultimas 24 horas foram confirmados 10 óbitos no Japão, diminuindo em 3% relativamente ao dia de ontem.");
        tabelaNoticias.insert(Converte.noticiaToContentValues(noticia));

        BdTabelaPacientes tabelaPacientes = new BdTabelaPacientes(db);

        Paciente paciente = new Paciente();
        paciente.setNome("Valter Simões");
        paciente.setData_aniversario("11/05/2000");
        paciente.setGenero("Masculino");
        paciente.setEstado_atual("Recuperado");
        paciente.setData_estado_atual("03/03/2020");
        paciente.setDoente_cronico("Não");
        paciente.setId_Pais(idPaisChina);
        tabelaPacientes.insert(Converte.pacienteToContentValues(paciente));

        paciente = new Paciente();
        paciente.setNome("Joana Santos");
        paciente.setData_aniversario("03/07/2001");
        paciente.setGenero("Feminino");
        paciente.setEstado_atual("Infetado");
        paciente.setData_estado_atual("07/07/2020");
        paciente.setDoente_cronico("Sim");
        paciente.setId_Pais(idPaisJapao);
        tabelaPacientes.insert(Converte.pacienteToContentValues(paciente));

        paciente = new Paciente();
        paciente.setNome("Pedro Antunes");
        paciente.setData_aniversario("03/07/1992");
        paciente.setGenero("Masculino");
        paciente.setEstado_atual("Recuperado");
        paciente.setData_estado_atual("07/07/2020");
        paciente.setDoente_cronico("Não");
        paciente.setId_Pais(idPaisJapao);
        tabelaPacientes.insert(Converte.pacienteToContentValues(paciente));
    }

    private void preencheTabelaPaises(BdTabelaPaises tabelaPaises) {

        Pais pais = new Pais();
        pais.setNome_pais(context.getString(R.string.País_Albânia));
        pais.setNumeroPopulacao(2877956);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Alemanha));
        pais.setNumeroPopulacao(83149300);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Andorra));
        pais.setNumeroPopulacao(77543);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Arménia));
        pais.setNumeroPopulacao(2957500);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Áustria));
        pais.setNumeroPopulacao(8902600);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Azerbaijão));
        pais.setNumeroPopulacao(10067108);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Bélgica));
        pais.setNumeroPopulacao(11524454);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Bielorrússia));
        pais.setNumeroPopulacao(9408400);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Bósnia));
        pais.setNumeroPopulacao(3301000);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Bulgária));
        pais.setNumeroPopulacao(6951482);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Cazaquistão));
        pais.setNumeroPopulacao(18694800);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Chipre));
        pais.setNumeroPopulacao(875900);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Croácia));
        pais.setNumeroPopulacao(4076246);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Dinamarca));
        pais.setNumeroPopulacao(5822763);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Eslováquia));
        pais.setNumeroPopulacao(5457873);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Eslovênia));
        pais.setNumeroPopulacao(2094060);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Estónia));
        pais.setNumeroPopulacao(1328360);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Finlândia));
        pais.setNumeroPopulacao(5528390);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Espanha));
        pais.setNumeroPopulacao(47100396);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_França));
        pais.setNumeroPopulacao(67075000);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Geórgia));
        pais.setNumeroPopulacao(3723464);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Gibraltar));
        pais.setNumeroPopulacao(33691);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Grécia));
        pais.setNumeroPopulacao(10724599);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Hungria));
        pais.setNumeroPopulacao(9772756);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Irlanda));
        pais.setNumeroPopulacao(4921500);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Islândia));
        pais.setNumeroPopulacao(366130);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Itália));
        pais.setNumeroPopulacao(60238522);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Kosovo));
        pais.setNumeroPopulacao(1795666);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Letônia));
        pais.setNumeroPopulacao(1906800);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Lituânia));
        pais.setNumeroPopulacao(2793471);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Luxemburgo));
        pais.setNumeroPopulacao(626108);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_MacedôniaDoNorte));
        pais.setNumeroPopulacao(2077132);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Malta));
        pais.setNumeroPopulacao(493559);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Moldávia));
        pais.setNumeroPopulacao(2681735);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Noruega));
        pais.setNumeroPopulacao(5367580);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Holanda));
        pais.setNumeroPopulacao(17462581);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Polónia));
        pais.setNumeroPopulacao(38379000);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Portugal));
        pais.setNumeroPopulacao(10276617);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_ReinoUnido));
        pais.setNumeroPopulacao(66435550);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Roménia));
        pais.setNumeroPopulacao(19405156);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Rússia));
        pais.setNumeroPopulacao(146745098);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_SanMarino));
        pais.setNumeroPopulacao(33533);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Sérvia));
        pais.setNumeroPopulacao(6933764);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Suécia));
        pais.setNumeroPopulacao(10338368);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Suiça));
        pais.setNumeroPopulacao(8603899);
        tabelaPaises.insert(Converte.paisToContentValues(pais));


        pais.setNome_pais(context.getString(R.string.País_Turquia));
        pais.setNumeroPopulacao(83154997);
        tabelaPaises.insert(Converte.paisToContentValues(pais));

        pais.setNome_pais(context.getString(R.string.País_Ucrânia));
        pais.setNumeroPopulacao(41858119);
        tabelaPaises.insert(Converte.paisToContentValues(pais));
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
