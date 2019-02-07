package com.vieira.vinny.cardview.model;

public class Torneio {
    private String titulo;
    private String cidade;
    private String taxa;
    private String local;
    private String inscritos;
    private String modalidade;
    private int imagemTorneio;

    public Torneio(String titulo, String cidade, String taxa, String local, String inscritos, int imagemTorneio,String modalidade) {
        this.titulo = titulo;
        this.cidade = cidade;
        this.taxa = taxa;
        this.local = local;
        this.inscritos = inscritos;
        this.modalidade = modalidade;
        this.imagemTorneio = imagemTorneio;
    }

    public String getModalidade() {
        return modalidade;
    }

    public void setModalidade(String modalidade) {
        this.modalidade = modalidade;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getTaxa() {
        return taxa;
    }

    public void setTaxa(String taxa) {
        this.taxa = taxa;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getInscritos() {
        return inscritos;
    }

    public void setInscritos(String inscritos) {
        this.inscritos = inscritos;
    }

    public int getImagemTorneio() {
        return imagemTorneio;
    }

    public void setImagemTorneio(int imagemTorneio) {
        this.imagemTorneio = imagemTorneio;
    }
}