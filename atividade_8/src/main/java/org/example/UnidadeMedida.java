package org.example;

public enum UnidadeMedida {
    KG("kg"),
    LITRO("litro"),
    METRO("metro"),
    METRO_QUADRADO("metro quadrado");

    private final String descricao;

    UnidadeMedida(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }
}