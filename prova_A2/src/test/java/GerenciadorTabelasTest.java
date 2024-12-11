import static org.junit.jupiter.api.Assertions.*;
import org.example.ConexaoBD;
import org.example.GerenciadorTabelas;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Classe de testes para verificar o comportamento do GerenciadorTabelas.
 * Estes testes cobrem a criação e verificação de tabelas no banco de dados.
 */
public class GerenciadorTabelasTest {

    /**
     * Método de preparação que será executado antes de cada teste.
     * Garante que as tabelas sejam recriadas antes de cada teste.
     */
    @BeforeEach
    void limparEPrepararBancoDeDados() {
        try (Connection conexao = ConexaoBD.conectar()) {
            excluirTabelaSePresenteSeNecessario(conexao, "visitadas");
            excluirTabelaSePresenteSeNecessario(conexao, "usuarios");
            excluirTabelaSePresenteSeNecessario(conexao, "cidades");
        } catch (SQLException e) {
            fail("Erro ao preparar banco de dados: " + e.getMessage());
        }
    }

    /**
     * Teste para verificar se o método verificarOuCriarTabelas() cria as tabelas corretamente.
     */
    @Test
    void testarCriacaoDeTodosOsTiposDeTabelas() {
        // Executa o método de verificação/criação de tabelas
        GerenciadorTabelas.verificarOuCriarTabelas();

        try (Connection conexao = ConexaoBD.conectar()) {
            // Verifica a existência de cada tabela
            assertTrue(tabelaExiste(conexao, "usuarios"),
                    "A tabela 'usuarios' deve ser criada");
            assertTrue(tabelaExiste(conexao, "cidades"),
                    "A tabela 'cidades' deve ser criada");
            assertTrue(tabelaExiste(conexao, "visitadas"),
                    "A tabela 'visitadas' deve ser criada");
        } catch (SQLException e) {
            fail("Erro ao verificar tabelas: " + e.getMessage());
        }
    }

    /**
     * Teste para verificar se as tabelas não são recriadas se já existirem.
     */
    @Test
    void testarCriacaoDeTabelasJaExistentes() {
        // Primeira chamada para criar tabelas
        GerenciadorTabelas.verificarOuCriarTabelas();

        // Segunda chamada não deve causar erros
        assertDoesNotThrow(() -> {
            GerenciadorTabelas.verificarOuCriarTabelas();
        }, "Método não deve lançar exceção ao tentar criar tabelas existentes");
    }

    /**
     * Teste para verificar a estrutura da tabela de usuários.
     */
    @Test
    void testarEstruturaTabelaUsuarios() {
        GerenciadorTabelas.verificarOuCriarTabelas();

        try (Connection conexao = ConexaoBD.conectar()) {
            DatabaseMetaData metaData = conexao.getMetaData();

            // Verificar colunas da tabela usuarios
            try (ResultSet colunas = metaData.getColumns(null, null, "usuarios", null)) {
                Set<String> colunasEsperadas = new HashSet<>(Arrays.asList("id", "cpf", "senha", "email", "empresa"));
                Set<String> colunasEncontradas = new HashSet<>();

                // Depuração: imprimir catálogo e esquema
                String catalogo = conexao.getCatalog();
                String esquema = conexao.getSchema();
                System.out.println("Catálogo: " + catalogo);
                System.out.println("Esquema: " + esquema);

                while (colunas.next()) {
                    String nomeColuna = colunas.getString("COLUMN_NAME").toLowerCase();
                    String nomeTabelaBanco = colunas.getString("TABLE_NAME");
                    String catalogoBanco = colunas.getString("TABLE_CAT");

                    System.out.printf("Coluna encontrada: %s, Tabela: %s, Catálogo: %s%n",
                            nomeColuna, nomeTabelaBanco, catalogoBanco);

                    colunasEncontradas.add(nomeColuna);
                }

                // Verificação com mensagem detalhada
                for (String colunaEsperada : colunasEsperadas) {
                    assertTrue(colunasEncontradas.contains(colunaEsperada.toLowerCase()),
                            "Coluna '" + colunaEsperada + "' não encontrada. Colunas encontradas: " + colunasEncontradas);
                }

                // Verificar quantidade de colunas
                assertEquals(5, colunasEncontradas.size(),
                        "Número de colunas deve ser 5. Encontradas: " + colunasEncontradas);
            }
        } catch (SQLException e) {
            fail("Erro ao verificar estrutura da tabela: " + e.getMessage());
        }
    }

    /**
     * Teste para verificar as chaves estrangeiras da tabela de cidades visitadas.
     */
    @Test
    void testarChavesEstrangeirasVisitadas() {
        GerenciadorTabelas.verificarOuCriarTabelas();

        try (Connection conexao = ConexaoBD.conectar()) {
            DatabaseMetaData metaData = conexao.getMetaData();

            // Verificar chaves estrangeiras da tabela visitadas
            try (ResultSet chavesEstrangeiras = metaData.getImportedKeys(null, null, "visitadas")) {
                boolean temChaveUsuario = false;
                boolean temChaveCidade = false;

                while (chavesEstrangeiras.next()) {
                    String tabelaReferenciada = chavesEstrangeiras.getString("PKTABLE_NAME");

                    if ("usuarios".equals(tabelaReferenciada)) {
                        temChaveUsuario = true;
                    }
                    if ("cidades".equals(tabelaReferenciada)) {
                        temChaveCidade = true;
                    }
                }

                assertTrue(temChaveUsuario, "Deve ter chave estrangeira para usuarios");
                assertTrue(temChaveCidade, "Deve ter chave estrangeira para cidades");
            }
        } catch (SQLException e) {
            fail("Erro ao verificar chaves estrangeiras: " + e.getMessage());
        }
    }

    /**
     * Método auxiliar para excluir tabela se existir.
     */
    private void excluirTabelaSePresenteSeNecessario(Connection conexao, String nomeTabela) throws SQLException {
        try (var stmt = conexao.createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS " + nomeTabela);
        }
    }

    /**
     * Método auxiliar para verificar existência de tabela.
     */
    private boolean tabelaExiste(Connection conexao, String nomeTabela) throws SQLException {
        DatabaseMetaData metaData = conexao.getMetaData();
        try (var rs = metaData.getTables(null, null, nomeTabela, null)) {
            return rs.next();
        }
    }
}