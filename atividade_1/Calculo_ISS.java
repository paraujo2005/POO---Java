package atividade_1;

public class Calculo_ISS {
    
    public double calcularISS(int tipo, double valor){
        double valor_ISS = 0;

        //Somente Serviço
        if(tipo == 2){
            valor_ISS = valor * 0.073;
        }

        return valor_ISS;
    }
}
