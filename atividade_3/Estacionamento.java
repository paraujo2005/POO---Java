package atividade_3;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Estacionamento {
    double peso;
    String veiculo;
    String tipo;

    private static final DecimalFormat df = new DecimalFormat("0.00");

    //Constutor 
    public Estacionamento(double peso, String veiculo) {
        this.peso = peso;
        this.veiculo = veiculo;
        this.tipo = "Indefinido";
    }

    public String CalcularTipo(double peso){
        return "Indefinido";
    }

    public int VerificarPeso(double peso, double peso_max){
        if(peso > peso_max){
            return 0;
        } else {
            return 1;
        }
    }

    public void InformarDados(){
        System.out.println("\n------------------------------------------------------------------------");
        System.out.println("Modelo do veículo: " + veiculo);
        System.out.println("Tipo do veículo: " + tipo);
        System.out.println("Peso do veículo em Kilogramas: " + df.format(peso) + " Kg");
        System.out.println("Peso do veículo em Toneladas: " + df.format(peso/1000) + " Ton");
    }

    public double CalcularPrecoHora(){
       return 0;
    }

    public void ImprimirPrecoHora(){
        System.out.println("\n------------------------------------------------------------------------");
        System.out.println("Preço por hora do veículo: R$" + df.format(CalcularPrecoHora()));
    }

    public void SimularPagamento(){
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("\n------------------------------------------------------------------------");
        System.out.println("Insira a data e o horário de entrada");
        System.out.print("Data (formato AAAA-MM-DD): ");
        String dataEntradaStr = scanner.next();
        LocalDate dataEntrada = LocalDate.parse(dataEntradaStr);
        
        System.out.print("Hora (24h): ");
        int horaEntrada = scanner.nextInt();
        System.out.print("Minutos: ");
        int minutosEntrada = scanner.nextInt();
        LocalTime horaEntradaTime = LocalTime.of(horaEntrada, minutosEntrada);

        System.out.println("\nInsira a data e o horário de saída");
        System.out.print("Data (formato AAAA-MM-DD): ");
        String dataSaidaStr = scanner.next();
        LocalDate dataSaida = LocalDate.parse(dataSaidaStr);
        
        System.out.print("Hora (24h): ");
        int horaSaida = scanner.nextInt();
        System.out.print("Minutos: ");
        int minutosSaida = scanner.nextInt();
        LocalTime horaSaidaTime = LocalTime.of(horaSaida, minutosSaida);
        
        Duration duracao = Duration.between(horaEntradaTime, horaSaidaTime);

        if (dataSaida.isAfter(dataEntrada)) {
            duracao = duracao.plusHours(24 * (dataSaida.toEpochDay() - dataEntrada.toEpochDay()));
        }

        System.out.println("\nTempo total no estacionamento: " + duracao.toHours() + " horas e " + duracao.toMinutesPart() + " minutos.");

        double valorTotal = duracao.toHours() * CalcularPrecoHora();

        System.out.println("\nValor total a pagar: R$ " + df.format(valorTotal));
    }
}   
