package com.vieira.vinny.cardview.enumerados;

public enum ModalidadesSuportadas {
    FUTEBOL_CAMPO ("Futebol Campo"),
    FUTEBOL_SOCIETY ("Futebol Society"),
    FUTSAL("Futsal"),
    VOLEI("Volêi"),
    BASQUETE("Basquete"),
    FUTEVOLE("Futevolei");

    private final String valor;

    ModalidadesSuportadas(String valor) {
        this.valor = valor;
    }

    public String getValor() {
        return valor;
    }

}
