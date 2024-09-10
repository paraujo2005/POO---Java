package atividade_3;

public class Motocicleta extends Estacionamento {

    //Construtor Motocicleta
    public Motocicleta(double peso) {
        super(peso, "Motocicleta");
        this.tipo = CalcularTipo(peso);
    }

    @Override
    public String CalcularTipo(double peso){
        if(peso <= 100) return "Leve";
        if(peso > 300) return "Pesada";
        return "Padrão";
    }

    @Override
    public double CalcularPrecoHora(){
        if(this.tipo.equals("Leve")){
            return 2;
        } else if (this.tipo.equals("Padrão")) {
            return 4;
        } else if ((this.tipo.equals("Pesada"))) {
            return 10;
        } else {
            return 0;
        }
    }
}