package atividade_1;

public class Calculo_IPI {
    public double calcularIPI(int tipo, double valor){
        double valor_IPI = 0;

        //Somente Produto
        if(tipo == 1){
            valor_IPI = valor * 0.219;
        }

        return valor_IPI;
    }
}
