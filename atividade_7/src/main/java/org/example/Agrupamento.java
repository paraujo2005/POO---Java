package org.example;

import java.sql.SQLOutput;
import java.util.*;

public class Agrupamento {
    private final List<Cidade> cidades;

    public Agrupamento(List<Cidade> cidades){
        this.cidades = cidades;
    }

    //Estrutura 1
    public void agruparPorUF(){
        Map<String, Set<String>> cidadesPorUf = new TreeMap<>();

        //Agrupando Cidades por UF
        for(Cidade cidade : cidades){
            cidadesPorUf.computeIfAbsent(cidade.getUf(), k -> new TreeSet<>()).add(cidade.getNome());
        }

        //Imprimindo Cidades por Ordem Alfabética
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Cidades Agrupadas por UF:");
        for(Map.Entry<String, Set<String>> entry : cidadesPorUf.entrySet()){
            System.out.println(entry.getKey() + ":");
            for(String cidade : entry.getValue()){
                System.out.println("  " + cidade);
            }
        }
    }

    //Estrutura 2
    public void agruparPorCapital(){
        Comparator<Cidade> comparadorCapital = new Comparator<Cidade>() {
            @Override
            public int compare(Cidade c1, Cidade c2) {
                //Primeiro, verifica se é capital ou não (capitais têm maior prioridade)
                if (c1.isCapital() && !c2.isCapital()) {
                    return -1; //c1 é capital, então vem antes
                } else if (!c1.isCapital() && c2.isCapital()) {
                    return 1; //c2 é capital, então vem antes
                } else {
                    //Se ambas são ou não capitais, ordena por nome da cidade
                    return c1.getNome().compareTo(c2.getNome());
                }
            }
        };

        //Criando a PriorityQueue com o comparador
        PriorityQueue<Cidade> pqCidades = new PriorityQueue<>(comparadorCapital);

        for(Cidade cidade : cidades){
            pqCidades.add(cidade);
        }

        //Imprimindo Cidades por Capital
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Cidades Agrupadas por Capital:");
        while (!pqCidades.isEmpty()) {
            Cidade cidade = pqCidades.poll();
            System.out.print(cidade.getNome() + " - " + cidade.getUf());
            if (cidade.isCapital()) {
                System.out.print(" (Capital)\n");
            } else {
                System.out.println();
            }
        }
    }

    //Estrutura 3
    public void agruparEmDecresente(){
        Set<Cidade> cidadesSet = new TreeSet<>(new Comparator<Cidade>() {
            @Override
            public int compare(Cidade c1, Cidade c2) {
                return c2.getNome().compareTo(c1.getNome());
            }
        });

        //Adicionando as cidades no TreeSet
        cidadesSet.addAll(cidades);

        //Imprimindo as cidades em ordem decrescente
        System.out.println("---------------------------------------------------------------------------------------------");
        System.out.println("Cidades Agrupadas em Ordem Alfabética Decrescente:");
        for (Cidade cidade : cidadesSet) {
            System.out.println(cidade.getNome() + " - " + cidade.getUf());
        }
    }
}

