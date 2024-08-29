package atividade_1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("---------------------------\nInsira 1 para Produto\nInsira 2 para Serviço\n---------------------------");

        // Scan Tipo
        System.out.println("Insira o Tipo:");
        int tipo = scanner.nextInt();

        // Scan Valor
        System.out.println("Insira o Valor:");
        double valor = scanner.nextDouble();

        scanner.close();

        Calculo_ISS calculo_iss = new Calculo_ISS();
        Calculo_ICMS calculo_icms = new Calculo_ICMS();
        Calculo_IPI calculo_ipi = new Calculo_IPI();

        double valor_ISS = calculo_iss.calcularISS(tipo, valor);
        double valor_ICMS = calculo_icms.calcularICMS(valor);
        double valor_IPI = calculo_ipi.calcularIPI(tipo, valor);
        double valor_total = valor + valor_ISS + valor_ICMS + valor_IPI;

        System.out.println("\n\n---------------------------\nValor Total a Pagar: R$" + String.format("%.2f", valor_total) + "\n---------------------------");
        System.out.println("Valor Original: R$" + String.format("%.2f", valor) + "\n---------------------------");
        System.out.println("Taxas Aplicadas");
        System.out.println("ISS: R$" + String.format("%.2f", valor_ISS));
        System.out.println("ICMS: R$" + String.format("%.2f", valor_ICMS));
        System.out.println("IPI: R$" + String.format("%.2f", valor_IPI));
        System.out.println("---------------------------");
    }
}