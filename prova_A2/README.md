# Prova Final A2 - Sistema de Cadastramento de Usuários e Cidades Visitadas

Essa tarefa tem como objetivo realizar a implementação de um programa que permite:
- A manutenção de Usuários (cadastramento, visualização, edição e exclusão de registros)
- A autenticação de um Usuário por meio do CPF e Senha
- A manutenção de uma lista de Cidades visitadas por cada Usuário (cadastramento, visualização, edição e exclusão de registros)
- A possibilidade de listar todas as Cidades vinculadas a cada Usuário

Para a sua realização, a implementação deveria ser feita utilizando a interface API JDBC com o Banco de Dados MySQL. Além disso, para a demonstração do programa, 
foram implementados tanto uma interface de linha de comandos (utilizando `System.out.print*` e `Scanner`) quanto uma série de testes unitários validadores das
funcionalidades de cada uma das classes.

## Estrutura de Classes

### `Main`
- **Descrição**: Classe principal que contém o método `main` e gerencia a interação com o usuário. Permite a manutenção de usuários, cidades e a autenticação.
- **Métodos Principais**:
  - `main(String[] args)`: Gerencia o fluxo principal do programa, incluindo a entrada de dados do usuário e a execução das opções disponíveis.
 
### `ConexaoBD`
- **Descrição**: Classe utilitária responsável por gerenciar a conexão com o banco de dados MySQL.
- **Métodos Principais**:
  - `conectar()`: Estabelece e retorna uma conexão com o banco de dados utilizando os parâmetros de URL, usuário e senha configurados. Lança uma exceção
    `SQLException` caso não seja possível estabelecer a conexão.
 
### `GerenciadorTabelas`
- **Descrição**: Classe responsável por verificar e criar as tabelas necessárias no banco de dados, garantindo que a estrutura básica esteja pronta para operação.
- **Métodos Principais**:
  - `verificarOuCriarTabelas()`: Verifica se as tabelas `usuarios`, `cidades` e `visitadas` existem no banco de dados e as cria caso estejam ausentes.
- **Métodos Auxiliares**:
  - `tabelaExiste(Connection conexao, String nomeTabela)`: Verifica se uma tabela com o nome especificado já existe no banco de dados.
  - `criarTabelaUsuarios(Connection conexao)`: Cria a tabela `usuarios` com colunas para CPF, senha, email e empresa.
  - `criarTabelaCidades(Connection conexao)`: Cria a tabela `cidades` com colunas para o nome da cidade e o país.
  - `criarTabelaVisitadas(Connection conexao)`: Cria a tabela `visitadas` com colunas que vinculam usuários a cidades visitadas e o ano da visita, utilizando chaves estrangeiras para as tabelas `usuarios` e `cidades`.
  - `executarSQL(Connection conexao, String sql, String nomeTabela)`: Executa um comando SQL para criar uma tabela e exibe uma mensagem de sucesso no console.

### `Usuario`
- **Descrição**: Classe que representa um Usuário no sistema, contendo as informações básicas necessárias para autenticação e identificação.
- **Atributos**:
  - `cpf`: CPF do usuário, utilizado como identificador único.
  - `senha`: Senha do usuário, utilizada para autenticação.
  - `email`: Endereço de email do usuário.
  - `empresa`: Nome da empresa associada ao usuário.
- **Construtores**:
  - `Usuario(String cpf, String senha, String email, String empresa)`: Inicializa um novo objeto `Usuario` com os dados fornecidos.
- **Métodos Acessores (Getters e Setters)**:
  - `getCpf()`: Retorna o CPF do usuário.
  - `setCpf(String cpf)`: Define o CPF do usuário.
  - `getSenha()`: Retorna a senha do usuário.
  - `setSenha(String senha)`: Define a senha do usuário.
  - `getEmail()`: Retorna o email do usuário.
  - `setEmail(String email)`: Define o email do usuário.
  - `getEmpresa()`: Retorna a empresa associada ao usuário.
  - `setEmpresa(String empresa)`: Define a empresa associada ao usuário.

### `UsuarioRepository`
- **Descrição**: Classe responsável por realizar operações CRUD (Criar, Ler, Atualizar, Excluir) para a tabela `usuarios`, além de realizar a autenticação do usuário e manipulação das cidades visitadas.
- **Métodos Principais**:
  - `adicionarUsuario(Usuario usuario)`: Adiciona um novo usuário no banco de dados utilizando os dados fornecidos pelo objeto `Usuario`.
  - `listarUsuarios()`: Retorna uma lista de todos os usuários cadastrados no banco de dados.
  - `alterarUsuario(String cpf, String nova_senha, String novo_email, String nova_empresa)`: Atualiza os dados de um usuário, como senha, email e empresa, com base no CPF fornecido.
  - `excluirUsuario(String cpf)`: Exclui um usuário e as entradas associadas a ele na tabela `visitadas`. Utiliza transações para garantir que ambas as operações sejam realizadas com sucesso ou revertidas em caso de erro.
  - `autenticarUsuario(String cpf, String senha)`: Verifica as credenciais de login de um usuário, autenticando-o com base no CPF e senha fornecidos.
  - `imprimirCidadesVisitadasPorUsuario()`: Exibe a lista de cidades visitadas por cada usuário, organizando as informações por CPF.

### `CidadesVisitadas`
- **Descrição**: Classe que representa as informações sobre uma cidade visitada por um usuário, incluindo o nome da cidade, o país em que ela se localiza e o ano em que foi visitada.
- **Atributos**:
  - `nome`: Nome da cidade visitada.
  - `pais`: País onde a cidade está localizada.
  - `ano`: Ano em que a cidade foi visitada.
- **Construtores**:
  - `CidadesVisitadas(String nome, String pais, int ano)`: Inicializa um novo objeto `CidadesVisitadas` com os dados fornecidos.
- ***Métodos Acessores (Getters e Setters)**:
  - `getNome()`: Retorna o nome da cidade.
  - `setNome(String nome)`: Define o nome da cidade.
  - `getPais()`: Retorna o país da cidade.
  - `setPais(String pais)`: Define o país da cidade.
  - `getAno()`: Retorna o ano em que a cidade foi visitada.
  - `setAno(int ano)`: Define o ano em que a cidade foi visitada.
 
### `CidadesVisitadasRepository`
- **Descrição**: Classe responsável por realizar operações CRUD (Criar, Ler, Atualizar, Excluir) para as tabelas `cidades` e `visitadas`, além de realizar o controle das cidades visitadas por cada usuário.
- **Métodos Principais**:
  - `buscarUsuarioIdPorCpf(String cpf)`: Busca o `id` do usuário com base no CPF fornecido.
  - `buscarCidadeIdPorNomeEPais(String nomeCidade, String paisCidade)`: Busca o `id` de uma cidade com base no nome e país fornecidos.
  - `adicionarCidadeVisitada(CidadesVisitadas visitada, String cpf)`: Adiciona uma nova cidade visitada para um usuário, inserindo a cidade e a visita no banco de dados. Se a cidade não existir, ela será criada.
  - `listarCidadesVisitadas(String cpf)`: Lista todas as cidades visitadas por um usuário, com base no CPF fornecido.
  - `alterarAnoVisita(String cpf, String nomeCidade, String paisCidade, int novoAno)`: Altera o ano de visita de uma cidade para um usuário específico.
  - `deletarVisita(String cpf, String nomeCidade, String paisCidade, int ano)`: Deleta uma visita a uma cidade feita por um usuário específico.
  - `deletarCidade(String nomeCidade, String paisCidade)`: Deleta uma cidade e todas as visitas associadas a ela no banco de dados.


## Testes Implementados

### ConexaoBDTest
- **testarConexaoComSucesso**: Teste para verificar se a conexão é estabelecida com sucesso. Verifica se a conexão não é nula e está aberta.
- **testarConexaoFechada**: Teste para verificar se a conexão pode ser fechada corretamente. Verifica o estado da conexão após seu fechamento.
- **testarTempoDeConexao**: Teste para verificar o tempo de conexão. Verifica se a conexão é estabelecida dentro de um tempo razoável.
- **testarConexaoOperacaoBasica**: Teste para verificar se a conexão suporta operações básicas. Verifica se a conexão pode realizar uma consulta simples.

### GerenciadorTabelasTest
- **testarCriacaoDeTodosOsTiposDeTabelas**: Teste para verificar se o método `verificarOuCriarTabelas()` cria as tabelas corretamente. Verifica a criação das tabelas `usuarios`, `cidades` e `visitadas` no banco de dados. 
- **testarCriacaoDeTabelasJaExistentes**: Teste para garantir que as tabelas não sejam recriadas se já existirem. O teste executa o método `verificarOuCriarTabelas()` duas vezes e garante que nenhuma exceção seja lançada.
- **testarEstruturaTabelaUsuarios**: Teste para verificar a estrutura da tabela `usuarios`. Verifica se a tabela contém as colunas esperadas (`id`, `cpf`, `senha`, `email`, `empresa`) e se o número de colunas é o correto.
- **testarChavesEstrangeirasVisitadas**: Teste para verificar as chaves estrangeiras na tabela `visitadas`. Verifica se as chaves estrangeiras para as tabelas `usuarios` e `cidades` estão presentes.

### UsuarioTest
- **deveCriarUsuarioComAtributosCorretos**: Teste para verificar a criação de um usuário com os atributos corretos. Verifica se o CPF, senha, email e empresa são atribuídos corretamente ao criar um novo usuário.
- **deveAlterarAtributosDoUsuario**: Teste para garantir que os atributos de um usuário possam ser alterados corretamente. Verifica se a senha, email e empresa são atualizados quando chamados os métodos de alteração.
- **deveCriarMultiplosUsuariosComDadosDiferentes**: Teste parametrizado para criar múltiplos usuários com diferentes dados de entrada. Verifica se o CPF, senha, email e empresa são definidos corretamente para cada usuário criado.
- **devePermitirCpfComTamanhoMaximo**: Teste para verificar se o usuário permite um CPF com tamanho máximo de 15 caracteres. Verifica se o CPF longo é aceito e armazenado corretamente.
- **naoDevePossuirMetodosGetInvalidos**: Teste para garantir que os métodos `get` do usuário não retornem valores nulos. Verifica se os métodos `getCpf()`, `getSenha()`, `getEmail()` e `getEmpresa()` retornam os valores corretamente.
- **devePermitirAlteracaoDeAtributosParaValoresVazios**: Teste para verificar se os atributos de um usuário podem ser alterados para valores vazios. Verifica se a senha, email e empresa podem ser definidos como strings vazias.

### UsuarioRepositoryTest
- **inicializar**: Configuração inicial dos testes. Cria o repositório de usuários e estabelece a conexão com o banco de dados. Também limpa as tabelas antes de iniciar os testes.
- **finalizarConexao**: Fechamento da conexão com o banco de dados após todos os testes.
- **prepararCadaTeste**: Limpeza das tabelas do banco de dados antes de cada teste para garantir que o estado do banco seja consistente.
- **limparTabelasParaTeste**: Método auxiliar para limpar as tabelas `visitadas` e `usuarios` no banco de dados, garantindo que os testes não sejam afetados por dados anteriores.
- **testarAdicionarUsuario**: Teste para garantir que um usuário seja adicionado corretamente ao banco de dados. Verifica se o usuário foi inserido corretamente com os dados de CPF, email e empresa.
- **testarListarUsuarios**: Teste para verificar se a listagem de usuários funciona corretamente. Adiciona usuários ao banco de dados e verifica se eles aparecem corretamente na lista recuperada.
- **testarAlterarUsuario**: Teste para garantir que um usuário existente seja alterado corretamente. Modifica o usuário no banco de dados e verifica se as alterações foram aplicadas corretamente.
- **testarExcluirUsuario**: Teste para verificar se a exclusão de um usuário funciona corretamente. Exclui um usuário do banco de dados e verifica se ele foi removido com sucesso.
- **testarAutenticacaoDeUsuario**: Teste para verificar a autenticação de um usuário com credenciais corretas e incorretas. Adiciona um usuário e testa se ele pode ser autenticado corretamente com a senha correta e se a autenticação falha com uma senha incorreta.

### CidadesVisitadasTest
- **deveCriarCidadeVisitadaComAtributosCorretos**: Teste para verificar se a criação de uma cidade visitada inicializa corretamente os atributos (`nome`, `pais`, `ano`). O teste assegura que os valores atribuídos ao criar a instância da classe estejam corretos.
- **deveAlterarAtributosDaCidadeVisitada**: Teste para verificar se é possível alterar os atributos (`nome`, `pais`, `ano`) de uma cidade visitada depois de sua criação. O teste garante que os novos valores sejam atribuídos corretamente.
- **deveCriarMultiplasCidadesVisitadasComDadosDiferentes**: Teste parametrizado para criar várias cidades visitadas com dados diferentes (nome, país e ano). Verifica se os valores passados para a criação da cidade são corretamente atribuídos.
- **devePermitirNomesComTamanhoMaximo**: Teste para verificar se é possível criar uma cidade visitada com um nome longo, dentro dos limites do sistema. Garante que o nome longo seja aceito sem erros.
- **devePermitirPaisesComNomesLongos**: Teste para verificar se é possível criar uma cidade visitada com um nome de país longo, garantindo que o nome longo do país seja aceito corretamente.
- **devePermitirAnosAnterioresEPosteriores**: Teste para verificar se a cidade visitada pode ter anos tanto passados (anteriores a 2023) quanto futuros (posteriores a 2023). Verifica a aceitação de anos fora do intervalo normal.
- **naoDevePossuirMetodosGetInvalidos**: Teste para garantir que os métodos `get` da classe `CidadesVisitadas` não retornem valores inválidos, como nulos ou zero, para os atributos `nome`, `pais` e `ano`.
- **devePermitirAlteracaoDeAtributosParaValoresVazios**: Teste para verificar se é possível alterar os atributos `nome` e `pais` da cidade visitada para valores vazios (`""`), assegurando que a classe permita esses valores.

### CidadesVisitadasRepositoryTest
- **testarBuscarUsuarioIdPorCpfQuandoUsuarioExiste**: Teste para verificar a busca do ID do usuário pelo CPF, quando o usuário existe no banco de dados. Verifica se o ID retornado não é nulo e é maior que zero.
- **testarBuscarUsuarioIdPorCpfQuandoUsuarioNaoExiste**: Teste para verificar a busca do ID do usuário pelo CPF, quando o usuário não existe no banco de dados. Verifica se o ID retornado é nulo.
- **testarBuscarCidadeIdPorNomeEPaisQuandoCidadeExiste**: Teste para verificar a busca do ID da cidade por nome e país, quando a cidade existe no banco de dados. Verifica se o ID retornado não é nulo e é maior que zero.
- **testarBuscarCidadeIdPorNomeEPaisQuandoCidadeNaoExiste**: Teste para verificar a busca do ID da cidade por nome e país, quando a cidade não existe no banco de dados. Verifica se o ID retornado é nulo.
- **testarAdicionarCidadeVisitadaComNovaCidade**: Teste para verificar se a adição de uma nova cidade visitada funciona corretamente. Verifica se a cidade visitada foi registrada no banco de dados com o ano correto.
- **testarAdicionarCidadeVisitadaComCidadeExistente**: Teste para verificar se a adição de uma cidade visitada existente funciona corretamente. Verifica se a cidade visitada foi registrada no banco de dados com o ano correto.
- **testarListarCidadesVisitadas**: Teste para verificar a listagem de cidades visitadas por um usuário. Verifica se as cidades inseridas estão presentes na lista e se os anos de visita correspondem aos dados inseridos.
- **testarAlterarAnoVisita**: Teste para verificar a alteração do ano de uma visita. Verifica se o ano da visita foi atualizado corretamente no banco de dados.
- **testarDeletarVisita**: Teste para verificar a deleção de uma visita registrada. Verifica se a visita foi removida corretamente do banco de dados.
  
**Métodos auxiliares**:
- **inserirUsuario**: Método para inserir um usuário no banco de dados.
- **inserirCidade**: Método para inserir uma cidade no banco de dados.
- **obterIdUsuarioPorCpf**: Método para obter o ID de um usuário baseado no CPF.
