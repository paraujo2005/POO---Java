package trabalho_A1;

import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        //Variáveis basicas para definição de dados hard-coded
        ArrayList<Aluno> alunos = new ArrayList<>();
        ArrayList<Professor> professores = new ArrayList<>();
        ArrayList<String> materias = new ArrayList<>(Arrays.asList("POO", "Estrutura de Dados", "BI"));

        //Dados hard-coded
        //Alunos
        Aluno aluno = new Aluno("Alfredo", "TI");
        alunos.add(aluno);
        aluno = new Aluno("Amélia", "TI");
        alunos.add(aluno);
        aluno = new Aluno("Ana", "ADM");
        alunos.add(aluno);
        aluno = new Aluno("Bruno", "TI");
        alunos.add(aluno);
        aluno = new Aluno("Bentinho", "ADM");
        alunos.add(aluno);
        aluno = new Aluno("Capitú", "TI");
        alunos.add(aluno);
        aluno = new Aluno("Debra", "TI");
        alunos.add(aluno);
        aluno = new Aluno("Ian", "ADM");
        alunos.add(aluno);
        aluno = new Aluno("Iracema", "TI");
        alunos.add(aluno);
        aluno = new Aluno("Joelmir", "ADM");
        alunos.add(aluno);
        aluno = new Aluno("Julieta", "TI");
        alunos.add(aluno);
        aluno = new Aluno("Luana", "ADM");
        alunos.add(aluno);
        aluno = new Aluno("Luciana", "TI");
        alunos.add(aluno);
        aluno = new Aluno("Majô", "ADM");
        alunos.add(aluno);
        aluno = new Aluno("Maria", "ADM");
        alunos.add(aluno);
        aluno = new Aluno("Norberto", "TI");
        alunos.add(aluno);
        aluno = new Aluno("Paulo", "ADM");
        alunos.add(aluno);
        aluno = new Aluno("Romeu", "ADM");
        alunos.add(aluno);
        aluno = new Aluno("Wendel", "TI");
        alunos.add(aluno);
        aluno = new Aluno("Zoey", "TI");
        alunos.add(aluno);

        //Professores
        Professor professor = new Professor("Mia", "POO");
        professores.add(professor);
        professor = new Professor("Saulo", "Estrutura de Dados");
        professores.add(professor);
        professor = new Professor("Paula", "BI");
        professores.add(professor);


        //Realizar ensalamento dos dados
        Ensalamento ensalamento = new Ensalamento(3, materias);

        //Definir de qual Curso é cada Matéria
        ensalamento.definirCurso("POO", "TI");
        ensalamento.definirCurso("Estrutura de Dados", "TI");
        ensalamento.definirCurso("BI", "ADM");

        ensalamento.ensalar(alunos, professores); //Comando para ensalar as listas de alunos e professores

        //Imprimir ensalamentos
        ensalamento.imprimirEnsalamentos();
    }
}
