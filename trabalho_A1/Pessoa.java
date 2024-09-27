package trabalho_A1;

public class Pessoa {
    private String nome;

    //Construtor
    public Pessoa(String nome){
        this.nome = nome;
    }

    //Gets e Sets
    public String getNome(){
        return this.nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }
}