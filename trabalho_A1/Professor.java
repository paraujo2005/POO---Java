package trabalho_A1;

public class Professor extends Pessoa {
    private String materia;

    //Construtor
    public Professor(String nome, String materia){
        super(nome);
        this.materia = materia;
    }

    //Gets e Sets
    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }
}
