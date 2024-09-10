package atividade_3;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);

        
        int fim = 1;
        do{
            //Pedir o peso e qual o veiculo
            int continuar = 1;
            double peso;
            
            Estacionamento veiculo = null;
            do{
                System.out.println("------------------------------------------------------------------------\n");
                System.out.println("Selecione o veículo:");
                System.out.println("1. Motocicleta");
                System.out.println("2. Carro e SUV");
                System.out.println("3. Caminhonete");
                System.out.println("4. Furgão");
                System.out.print("\nDigite um número de 1 a 4: ");
                
                switch(scanner.nextInt()){
                    case 1:
                        System.out.print("\nInsira o peso do veículo (em kilogramas): ");
                        peso = scanner.nextDouble();
                        veiculo = new Motocicleta(peso);
                        if(veiculo.VerificarPeso(peso, 400) == 1){
                            continuar = 0;
                        } else {
                            System.out.println("\n------------------------------------------------------------------------");
                            System.out.println("O veículo é muito pesado para o estacionamento. Insira outro veículo.");
                        }
                        break;

                    case 2:
                        System.out.print("\nInsira o peso do veículo (em kilogramas): ");
                        peso = scanner.nextDouble();
                        veiculo = new Carro(peso);
                        if(veiculo.VerificarPeso(peso, 2000) == 1){
                            continuar = 0;
                        } else {
                            veiculo = new Furgao(peso);
                            if(veiculo.VerificarPeso(peso, 6000) == 1){
                                continuar = 0;
                            } else {
                                System.out.println("\n------------------------------------------------------------------------");
                                System.out.println("O veículo é muito pesado para o estacionamento. Insira outro veículo.");
                            }
                        }
                        break;

                    case 3:
                    System.out.print("\nInsira o peso do veículo (em kilogramas): ");
                        peso = scanner.nextDouble();
                        veiculo = new Caminhonete(peso);
                        if(veiculo.VerificarPeso(peso, 6000) == 1){
                            continuar = 0;
                        } else {
                            System.out.println("\n------------------------------------------------------------------------");
                            System.out.println("O veículo é muito pesado para o estacionamento. Insira outro veículo.");
                        }
                        break;

                    case 4:
                        System.out.print("\nInsira o peso do veículo (em kilogramas): ");
                        peso = scanner.nextDouble();
                        veiculo = new Furgao(peso);
                        if(veiculo.VerificarPeso(peso, 6000) == 1){
                            continuar = 0;
                        } else {
                            System.out.println("\n------------------------------------------------------------------------");
                            System.out.println("O veículo é muito pesado para o estacionamento. Insira outro veículo.");
                        }
                        break;

                    default:
                        System.out.println("------------------------------------------------------------------------");
                        System.out.println("Número inválido. Por favor, insira um número entre 1 e 4.");
                        System.out.println("------------------------------------------------------------------------\n");
                        break;
                }
            } while (continuar == 1);
            
            int opt = 0;

            do { 
                System.out.println("------------------------------------------------------------------------\n");
                System.out.println("Escolha uma opção:");
                System.out.println("1. Mostrar informações do veículo");
                System.out.println("2. Mostrar preço por hora do veículo");
                System.out.println("3. Calcular preço a pagar do veículo");
                System.out.println("4. Inserir outro veículo");
                System.out.println("5. Sair");
                System.out.print("\nDigite um número de 1 a 5: "); 
                opt = scanner.nextInt();

                switch(opt){
                    case 1:
                        veiculo.InformarDados();
                        break;
                    case 2:
                        veiculo.ImprimirPrecoHora();
                        break;
                    case 3:
                        veiculo.SimularPagamento();
                        break;
                    case 4:
                        opt = 5;
                        break;
                    case 5:
                        opt = 5;
                        fim = 0;
                        break;
                    default:
                        System.out.println("------------------------------------------------------------------------");
                        System.out.println("Número inválido. Por favor, insira um número entre 1 e 5.");
                        System.out.println("------------------------------------------------------------------------\n");
                        break;
                }
            } while (opt != 5);
        } while (fim != 0);
        
        scanner.close();
    }
}
