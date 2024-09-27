package trabalho_A1;

import java.util.ArrayList;

public class Turma {
    private ArrayList<String> alunos;
    private ArrayList<String> professores;
    private String materia;
    private String curso;

    //Constutor
    public Turma(String materia){
        this.alunos = new ArrayList<>(); 
        this.professores = new ArrayList<>();
        this.materia = materia;
    }

    //Inserir Alunos e Professores no Objeto Turma
    public void inserirAluno(String nome){
        this.alunos.add(nome);
    }

    public void inserirProfessor(String nome){
        this.professores.add(nome);
    }

    //Listar Alunos e Professores presentes no Objeto Turma
    public void listarAlunos(){
        System.out.println("Alunos da turma:");
        int i = 1;
        for (String aluno : alunos) {
            System.out.println(i + ". " + aluno);
            i++;
        }
    }

    public void listarProfessores(){
        System.out.println("Professores da turma:");
        int i = 1;
        for (String professor : professores) {
            System.out.println(i + ". " + professor);
            i++;
        }
    }

    //Gets e Sets
    public ArrayList<String> getAlunos() {
        return this.alunos;
    }

    public ArrayList<String> getProfessores() {
        return this.professores;
    }

    public String getMateria() {
        return this.materia;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getCurso() {
        return this.curso;
    }
}
