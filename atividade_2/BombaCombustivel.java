package atividade_2;

import java.text.DecimalFormat;

public class BombaCombustivel {
    //Atributos
    public String tipoCombustivel;
    public double valorLitro;
    public double quantidadeCombustivel;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    //Metodos
    
    // Construtor
    public BombaCombustivel(String tipoCombustivel, double valorLitro, double quantidadeCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
        this.valorLitro = valorLitro;
        this.quantidadeCombustivel = quantidadeCombustivel;
    }

    public void abastecerPorValor(double Valor){
        if(Valor/valorLitro <= quantidadeCombustivel){
            System.out.println("------------------------------------------------------------------------");
            System.out.println("Você abasteceu " + df.format(Valor/valorLitro) + "L");
            System.out.println("------------------------------------------------------------------------\n");
            quantidadeCombustivel = quantidadeCombustivel - (Valor/valorLitro);
        } else {
            System.out.println("------------------------------------------------------------------------");
            System.out.println("A bomba não tem combustivel suficiente disponivel. Restam na bomba " + df.format(quantidadeCombustivel) + "L");
            System.out.println("------------------------------------------------------------------------\n");
        }
    }

    public void abastecerPorLitro(double Litro){
        if(Litro <= quantidadeCombustivel){
            System.out.println("------------------------------------------------------------------------");
            System.out.println("O valor para abastecer " + df.format(Litro) + "L foi de R$" +  df.format(Litro * valorLitro));
            System.out.println("------------------------------------------------------------------------\n");
            quantidadeCombustivel = quantidadeCombustivel - Litro;
        } else {
            System.out.println("------------------------------------------------------------------------");
            System.out.println("A bomba não tem combustivel suficiente disponivel. Restam na bomba " + df.format(quantidadeCombustivel) + "L");
            System.out.println("------------------------------------------------------------------------\n");
        }
    }

    public void alterarValor(double Valor_novo){
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Valor do combustivel alterado de R$" + df.format(valorLitro) + " para R$" + df.format(Valor_novo));
        System.out.println("------------------------------------------------------------------------\n");
        valorLitro = Valor_novo;
    }

    public void alterarCombustivel(String Tipo_novo){
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Tipo do combustivel alterado de " + tipoCombustivel + " para " + Tipo_novo);
        System.out.println("------------------------------------------------------------------------\n");
        tipoCombustivel = Tipo_novo;
    }

    public void alterarQuantidadeCombustivel(double Quantidade_nova){
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Quantidade de combustivel na bomba alterado de " + df.format(quantidadeCombustivel) + "L para " + df.format(Quantidade_nova + "L"));
        System.out.println("------------------------------------------------------------------------\n");
        quantidadeCombustivel = Quantidade_nova;
    }

    public void infoBomba(){
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Tipo de Combustivel da Bomba: " + tipoCombustivel);
        System.out.println("Valor do Litro na Bomba: " + df.format(valorLitro) + "L");
        System.out.println("Quantidade, e Litros, disponivel na Bomba: " + df.format(quantidadeCombustivel) + "L");
        System.out.println("------------------------------------------------------------------------");
    }

}