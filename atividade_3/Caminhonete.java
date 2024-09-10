package atividade_3;

public class Caminhonete extends Estacionamento {

    //Construtor Caminhonete
    public Caminhonete(double peso) {
        super(peso, "Caminhonete");
        this.tipo = CalcularTipo(peso);
    }

    @Override
    public String CalcularTipo(double peso){
        if (peso <= 3000) return "Não Carregado";
        return "Carregado";
    }

    @Override
    public double CalcularPrecoHora(){
        if(this.tipo.equals("Não Carregado")){
            return 25;
        } else if (this.tipo.equals("Carregado")) {
            return 50;
        } else {
            return 0;
        }
    }
}