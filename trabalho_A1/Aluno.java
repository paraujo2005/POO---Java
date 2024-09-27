package trabalho_A1;

public class Aluno extends Pessoa {
    private String curso;

    //Construtor
    public Aluno(String nome, String curso){
        super(nome);
        this.curso = curso;
    }

    //Gets e Sets
    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }
}
