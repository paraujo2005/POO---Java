package atividade_2;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        
        //Pedir para o Usuario Definir Valores Base
        String tipo_base;
        double valor_base, quantidade_base;

        System.out.println("------------------------------------------------------------------------");

        System.out.print("Insira o Tipo de Combustivel da Bomba: ");
        tipo_base = scanner.next();

        System.out.print("Insira o Valor do Litro na Bomba: ");
        valor_base = scanner.nextDouble();

        System.out.print("Insira a Quantidade, em Litros, disponivel na Bomba: ");
        quantidade_base = scanner.nextDouble();

        System.out.println("------------------------------------------------------------------------");

        //Instanciar Bomba
        BombaCombustivel bomba = new BombaCombustivel(tipo_base, valor_base, quantidade_base);

        //Menu de Uso

        int opt;
        do { 
            System.out.println("Escolha uma Operação:");
            System.out.println("1. Abastecer por um Valor");
            System.out.println("2. Abastecer por uma Quantidade de Litros");
            System.out.println("3. Alterar Valor de um Litro");
            System.out.println("4. Alterar o Tipo do Combustivel");
            System.out.println("5. Alterar a Quantidade de Combustivel Disponível na Bomba");
            System.out.println("6. Mostrar Informações da Bomba");
            System.out.println("7. Sair do Sistema");
            System.out.print("\nDigite um número de 1 a 7: ");
            opt = scanner.nextInt();
            System.out.print("\n");
            
            switch (opt) {
                case 1:
                    System.out.print("Insira o Valor Desejado de Combustivel: R$");
                    bomba.abastecerPorValor(scanner.nextDouble());
                    break;
                case 2:
                    System.out.print("Insira o Valor Desejado de Combustivel em Litros: ");
                    bomba.abastecerPorLitro(scanner.nextDouble());
                    break;
                case 3:
                    System.out.print("Insira o Novo Valor de um Litro de Combustivel: R$");
                    bomba.alterarValor(scanner.nextDouble());
                    break;
                case 4:
                    System.out.print("Insira o Novo Tipo de Combustivel: ");
                    bomba.alterarCombustivel(scanner.next());
                    break;
                case 5:
                    System.out.print("Insira a Nova Quantidade de Combustivel: ");
                    bomba.alterarQuantidadeCombustivel(scanner.nextDouble());
                    break;
                case 6:
                    bomba.infoBomba();
                    break;
                case 7:
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Encerrando Sistema");
                    System.out.println("------------------------------------------------------------------------\n");
                    break;
                default:
                    System.out.println("------------------------------------------------------------------------");
                    System.out.println("Número inválido. Por favor, insira um número entre 1 e 7.");
                    System.out.println("------------------------------------------------------------------------\n");
                    break;
            }
        } while (opt != 7);

        scanner.close();
    }
}
