# Sistema de Ensalamento de Alunos e Professores

Este projeto implementa um sistema de organização de turmas, onde alunos são agrupados em diferentes disciplinas com base em seus cursos. Professores também são alocados às turmas, conforme a matéria que lecionam.
O projeto foi desenvolvido utilizando dados fixos no código (hard-coded), conforme solicitado pelo professor.

## Funcionalidades

- Cadastro de alunos e professores com suas respectivas informações.
- Alocação automática de alunos em turmas baseadas no curso e na matéria.
- Distribuição de professores para cada turma com base na matéria que lecionam.
- Impressão de ensalamentos com detalhes sobre as turmas, incluindo alunos e professores associados.

## Regras de Ensalamento

As turmas são organizadas com base nas matérias e nos cursos correspondentes:
1. Alunos são alocados a turmas cuja matéria pertença ao curso que estão matriculados.
2. Professores são atribuídos às turmas com base na matéria que lecionam.
3. Cada matéria corresponde a um curso específico, garantindo que alunos e professores sejam agrupados corretamente.

| Curso             | Matéria           |
|-------------------|-------------------|
|TI                 |POO                |
|TI                 |Estrutura de Dados |
|ADM                |BI                 |

## Estrutura de Classes

### `Main`
- **Descrição**: Classe principal que contém o método `main` e gerencia a inicialização de dados e a execução do programa. Responsável por acionar a execução do `ensalamento`.
- **Métodos Principais**:
  - `main(String[] args):` Define alunos, professores e matérias. Executa o ensalamento e imprime os resultados.

### `Ensalamento`
- **Descrição**: Classe que gerencia a distribuição de alunos e professores nas turmas. Contém métodos para definir o curso de cada matéria e realizar o processo de ensalamento.
- **Atributos**:
  - `num_turmas`: Número de turmas a serem formadas.
  - `turmas`: Lista de objetos do tipo `Turma` que armazena as turmas criadas.
  - `materias`: Lista de matérias oferecidas.
- **Métodos**:
  - `definirCurso(String materia, String curso)`: Define a qual curso uma matéria pertence.
  - `ensalarAlunos(ArrayList<Aluno> alunos)`: Aloca os alunos nas turmas conforme o curso.
  - `ensalarProfessores(ArrayList<Professor> professores)`: Aloca os professores nas turmas conforme a matéria.
  - `ensalar(ArrayList<Aluno> alunos, ArrayList<Professor> professores)`: Executa a alocação de alunos e professores nas turmas.
  - `imprimirEnsalamentos()`: Imprime a lista de turmas, exibindo os alunos e professores em cada uma delas.

### `Aluno`
- **Descrição**: Representa um aluno com nome e curso.
- **Atributos**:
  - `curso`: Curso do aluno.
- **Métodos**:
  - `getCurso()`: Retorna o curso do aluno.
  - `setCurso(String curso)`: Define o curso do aluno.

### `Professor`
- **Descrição**: Representa um professor com nome e matéria que ensina.
- **Atributos**:
  - `materia`: Matéria que o professor leciona.
- **Métodos**:
  - `getMateria()`: Retorna a matéria do professor.
  - `setMateria(String materia)`: Define a matéria do professor.

### `Turma`
- **Descrição**: Representa uma turma composta por alunos e professores.
- **Atributos**:
  - `alunos`: Lista de nomes dos alunos alocados na turma.
  - `professores`: Lista de nomes dos professores alocados na turma.
  - `materia`: Matéria associada à turma.
  - `curso`: Curso associado à turma.
- **Métodos**:
  - `inserirAluno(String nome)`: Adiciona um aluno à lista de alunos da turma.
  - `inserirProfessor(String nome)`: Adiciona um professor à lista de professores da turma.
  - `listarAlunos()`: Lista os alunos da turma.
  - `listarProfessores()`: Lista os professores da turma.
  - `getCurso()`: Retorna o curso da turma.
  - `setCurso(String curso)`: Define o curso da turma.
  - `getMateria()`: Retorna a matéria da turma.

### `Pessoa`
- **Descrição**: Classe base que representa uma pessoa com nome.
- **Atributos**:
  - `nome`: Nome da pessoa.
- **Métodos**:
  - `getNome()`: Retorna o nome da pessoa.
  - `setNome(String nome)`: Define o nome da pessoa.

## Funcionamento

1. **Inicialização de Dados**: Alunos, professores e matérias são definidos diretamente no código (hard-coded).
2. **Alocação de Turmas**:
   - Cada aluno é alocado em uma turma conforme o curso.
   - Cada professor é alocado em uma turma conforme a matéria que ensina.
3. **Impressão das Turmas**:
   - O sistema imprime a lista de turmas formadas, exibindo os alunos e professores alocados.
