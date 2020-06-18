package com.example.projeto_final;

public class Noticia {
    private long id;
    private String titulo;
    private String data;
    private String conteudo;
    private long id_Pais;

    public long getId_Pais() {
        return id_Pais;
    }

    public void setId_Pais(long id_Pais) {
        this.id_Pais = id_Pais;
    }

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
}
