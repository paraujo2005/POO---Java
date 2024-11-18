package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        //Lista de Cidades
        List<Cidade> cidades = Arrays.asList(
                new Cidade("GO", "Anápolis", false),
                new Cidade("GO", "Goiânia", true),
                new Cidade("SP", "Ribeirão Preto", false),
                new Cidade("SP", "Campinas", false),
                new Cidade("MG", "Belo Horizonte", true),
                new Cidade("MG", "Uberlândia", false),
                new Cidade("MG", "Montes Claros", false),
                new Cidade("MG", "Unaí", false),
                new Cidade("TO", "Palmas", true),
                new Cidade("TO", "Araguarí", false),
                new Cidade("MT", "Cuiabá", true),
                new Cidade("MS", "Dourados", false),
                new Cidade("MS", "Campo Grande", true),
                new Cidade("MS", "Corumbá", false),
                new Cidade("MT", "Barra do Garças", false),
                new Cidade("MT", "Araguaiana", false),
                new Cidade("RO", "Porto Velho", true),
                new Cidade("RO", "Costa Marques", false)
        );

        //Agrupador de Cidades
        Agrupamento agrupamento = new Agrupamento(cidades);

        //Agrupar por UF
        agrupamento.agruparPorUF();

        //Agrupar por Capital
        agrupamento.agruparPorCapital();

        //Agrupar Ordem Decrescente
        agrupamento.agruparEmDecresente();
    }
}