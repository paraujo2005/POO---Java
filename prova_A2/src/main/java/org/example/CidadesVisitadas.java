package org.example;

public class CidadesVisitadas {
    private String nome;
    private String pais;
    private int ano;

    public CidadesVisitadas(String nome, String pais, int ano) {
        this.nome = nome;
        this.pais = pais;
        this.ano = ano;
    }

    //Sets e Gets
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

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}
