package com.example.projeto_final;

import android.database.Cursor;

public class Noticia {
    private long id = -1;
    private String titulo;
    private String data;
    private String conteudo;
    private long id_Pais = -1;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getConteudo() {
        return conteudo;
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public long getId_Pais() {
        return id_Pais;
    }

    public void setId_Pais(long id_Pais) {
        this.id_Pais = id_Pais;
    }

    public static Noticia fromCursor(Cursor cursor){
        long id = cursor.getInt(
                cursor.getColumnIndex(BdTabelaNoticias._ID)
        );

        String titulo = cursor.getString(
                cursor.getColumnIndex(BdTabelaNoticias.TITULO_NOTICIA)
        );

        String data = cursor.getString(
                cursor.getColumnIndex(BdTabelaNoticias.DATA_NOTICIA)
        );

        String conteudo = cursor.getString(
                cursor.getColumnIndex(BdTabelaNoticias.CONTEUDO_NOTICIA)
        );

        Long id_PAIS = cursor.getLong(
                cursor.getColumnIndex(BdTabelaNoticias.CAMPO_ID_PAIS)
        );

        Noticia noticia = new Noticia();

        noticia.setId(id);
        noticia.setTitulo(titulo);
        noticia.setData(data);
        noticia.setConteudo(conteudo);
        noticia.setId_Pais(id_PAIS);

        return noticia;
    }

}
