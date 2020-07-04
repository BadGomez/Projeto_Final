package com.example.projeto_final;

class Paciente {
    private long id = -1;
    private String nome;
    private String genero;
    private String data_aniversario;
    private String doente_cronico;
    private String estado_atual;
    private String data_estado_atual;
    private long id_Pais = -1;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getData_aniversario() {
        return data_aniversario;
    }

    public void setData_aniversario(String data_aniversario) {
        this.data_aniversario = data_aniversario;
    }

    public String getDoente_cronico() {
        return doente_cronico;
    }

    public void setDoente_cronico(String doente_cronico) {
        this.doente_cronico = doente_cronico;
    }

    public String getEstado_atual() {
        return estado_atual;
    }

    public void setEstado_atual(String estado_atual) {
        this.estado_atual = estado_atual;
    }

    public String getData_estado_atual() {
        return data_estado_atual;
    }

    public void setData_estado_atual(String data_estado_atual) {
        this.data_estado_atual = data_estado_atual;
    }

    public long getId_Pais() {
        return id_Pais;
    }

    public void setId_Pais(long id_Pais) {
        this.id_Pais = id_Pais;
    }
}
