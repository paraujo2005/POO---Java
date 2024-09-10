package atividade_3;

import java.util.Scanner;

public class Carro extends Estacionamento {
    //Construtor Carro
    public Carro(double peso) {
        super(peso, "Carro");
        this.tipo = CalcularTipo(peso);
    }

    @Override
    public String CalcularTipo(double peso){
        if(peso > 2000){
            System.out.println("\n------------------------------------------------------------------------");
            System.out.println("Excede peso máximo para carro. Veículo registrado como furgão.");
            return "Indefinido";
        }
        
        Scanner scanner = new Scanner(System.in);
        
        int opt = 0;
        int continuar = 1;
        String tipo_carro = null;
        
        do{
            System.out.println("\nSelecione o Tipo de Carro:");
            System.out.println("1.Carro de Passeio Hatchback");
            System.out.println("2.Carro de Passeio Sedan");
            System.out.println("3.SUV");
            System.out.print("\nInsira um valor de 1 a 3:");
            opt = scanner.nextInt();

            switch(opt){
                case 1:
                    tipo_carro = "Hatchback";
                    continuar = 0;
                    break;
                case 2:
                    tipo_carro = "Sedan";
                    continuar = 0;
                    break;
                case 3:
                    tipo_carro = "SUV";
                    continuar = 0;
                    break;
                default:
                    System.out.println("Número inválido. Por favor, insira um número entre 1 e 4.");
                    break;
            }
        } while (continuar == 1); 

        return tipo_carro;
    }

    @Override
    public double CalcularPrecoHora(){
        if(this.tipo.equals("Hatchback")){
            return 13;
        } else if (this.tipo.equals("Sedan")) {
            return 15;
        } else if ((this.tipo.equals("SUV"))) {
            return 20;
        } else {
            return 0;
        }
    }
}
