package com.example.projeto_final;

public class Pais {

    private long id;
    private String nome_pais;
    private Integer numeroPopulacao = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome_pais() {
        return nome_pais;
    }

    public void setNome_pais(String nome_pais) {
        this.nome_pais = nome_pais;
    }

    public Integer getNumeroPopulacao() {
        return numeroPopulacao;
    }

    public void setNumeroPopulacao(Integer numeroPopulacao) {
        this.numeroPopulacao = numeroPopulacao;
    }

}
