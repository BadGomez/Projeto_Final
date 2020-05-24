package com.example.projeto_final;

class Paciente {
    private long id;
    private String nome;
    private String pais;
    private String genero;
    private String data_aniversario;
    private String doente_cronico;
    private String estado_atual;
    private String data_estado_atual;

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

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getData_Aniversario() {
        return data_aniversario;
    }

    public void setData_Aniversario(String data_aniversario) {
        this.data_aniversario = data_aniversario;
    }

    public String getDoente_Cronico() {
        return doente_cronico;
    }

    public void setDoente_Cronico(String doente_cronico) {
        this.doente_cronico = doente_cronico;
    }

    public String getEstado_Atual() {
        return estado_atual;
    }

    public void setEstado_Atual(String estado_atual) {
        this.estado_atual = estado_atual;
    }

    public String getData_Estado_Atual() {
        return data_estado_atual;
    }

    public void setData_Estado_Atual(String data_estado_atual) {
        this.data_estado_atual = data_estado_atual;
    }
}
