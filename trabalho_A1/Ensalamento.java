package trabalho_A1;

import java.util.ArrayList;

public class Ensalamento {
    private int num_turmas;
    private ArrayList<Turma> turmas;
    private ArrayList<String> materias;

    //Construtor
    public Ensalamento(int num_turmas, ArrayList<String> materias){
        this.num_turmas = num_turmas;
        this.materias = materias;
        this.turmas = new ArrayList<>();
        for (int i = 0; i < this.num_turmas; i++) {
            Turma turma = new Turma(materias.get(i));
            this.turmas.add(turma);
        }

    }

    //Definir o Curso de Cada Turma de acordo com a Matéria
    public void definirCurso(String materia, String curso){
        for (Turma turma : turmas){
            if(turma.getMateria().equals(materia)){
                turma.setCurso(curso);
            }
        }
    }

    //Ensalar os Alunos e Professores em Objetos Turma
    public void ensalarAlunos(ArrayList<Aluno> alunos){
        for (Aluno aluno : alunos) {
            for (Turma turma : turmas) {
                if (aluno.getCurso().equals(turma.getCurso())) {
                    turma.inserirAluno(aluno.getNome());
                }
            }
        } 
    }

    public void ensalarProfessores(ArrayList<Professor> professores){
        for (Professor professor : professores) {
            for (Turma turma : turmas) {
                if (professor.getMateria().equals(turma.getMateria())) {
                    turma.inserirProfessor(professor.getNome());
                }
            }
        } 
    }

    public void ensalar(ArrayList<Aluno> alunos, ArrayList<Professor> professores){
        ensalarProfessores(professores);
        ensalarAlunos(alunos);
    }

    //Realizar a Impressão das Turmas já Ensaladas
    public void imprimirEnsalamentos(){
        for (Turma turma : turmas){
            System.out.println("-----------------------------------------------------");
            System.out.println("Curso: " + turma.getCurso());
            System.out.println("Matéria: " + turma.getMateria());
            System.out.println();
            turma.listarProfessores();
            System.out.println();
            turma.listarAlunos();
        }
        System.out.println("-----------------------------------------------------");
    }
}
