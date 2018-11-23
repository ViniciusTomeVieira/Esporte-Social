package com.vieira.vinny.cardview.model;

public class Postagem {
    private String email;
    private String nome;
    private  String local;
    private  String descricao;
    private  String quantidadeJogadores;
    private  String horario;
    private  int imagem;

    public Postagem() {
    }

    public Postagem(String local, String descricao, String quantidadeJogadores, String horario,String nome, String email, int imagem) {
        this.local = local;
        this.descricao = descricao;
        this.quantidadeJogadores = quantidadeJogadores;
        this.horario = horario;
        this.nome = nome;
        this.email = email;
        this.imagem = imagem;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getQuantidadeJogadores() {
        return quantidadeJogadores;
    }

    public void setQuantidadeJogadores(String quantidadeJogadores) {
        this.quantidadeJogadores = quantidadeJogadores;
    }

    public String getHorario() {
        return horario;
    }

    public void setHorario(String horario) {
        this.horario = horario;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
