import static org.junit.jupiter.api.Assertions.*;
import org.example.ConexaoBD;
import org.junit.jupiter.api.*;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Classe de testes para verificar o comportamento da classe ConexaoBD.
 * Estes testes cobrem diferentes cenários de conexão com o banco de dados.
 */
public class ConexaoBDTest {

    /**
     * Teste para verificar se a conexão é estabelecida com sucesso.
     * Verifica se a conexão não é nula e está aberta.
     */
    @Test
    void testarConexaoComSucesso() {
        try (Connection conexao = ConexaoBD.conectar()) {
            assertNotNull(conexao, "A conexão deve ser estabelecida.");
            assertFalse(conexao.isClosed(), "A conexão deve estar aberta.");
        } catch (SQLException e) {
            fail("Não deveria lançar exceção: " + e.getMessage());
        }
    }

    /**
     * Teste para verificar se a conexão pode ser fechada corretamente.
     * Verifica o estado da conexão após seu fechamento.
     */
    @Test
    void testarConexaoFechada() {
        Connection conexao = null;
        try {
            conexao = ConexaoBD.conectar();
            assertNotNull(conexao, "A conexão deve ser estabelecida.");
            conexao.close();
            assertTrue(conexao.isClosed(), "A conexão deve estar fechada.");
        } catch (SQLException e) {
            fail("Não deveria lançar exceção: " + e.getMessage());
        }
    }

    /**
     * Teste para verificar o comportamento de múltiplas conexões.
     * Garante que cada chamada do método conectar() retorna uma nova conexão.
     */
    @Test
    void testarMultiplasConexoes() {
        try {
            Connection conexao1 = ConexaoBD.conectar();
            Connection conexao2 = ConexaoBD.conectar();

            assertNotNull(conexao1, "A primeira conexão deve ser estabelecida.");
            assertNotNull(conexao2, "A segunda conexão deve ser estabelecida.");
            assertNotSame(conexao1, conexao2, "Devem ser conexões diferentes.");

            conexao1.close();
            conexao2.close();
        } catch (SQLException e) {
            fail("Não deveria lançar exceção: " + e.getMessage());
        }
    }

    /**
     * Teste para verificar o tempo de conexão.
     * Verifica se a conexão é estabelecida dentro de um tempo razoável.
     */
    @Test
    void testarTempoDeConexao() {
        long tempoInicio = System.currentTimeMillis();
        try (Connection conexao = ConexaoBD.conectar()) {
            long tempoFim = System.currentTimeMillis();
            long tempoConexao = tempoFim - tempoInicio;

            assertTrue(tempoConexao < 5000,
                    "A conexão deve ser estabelecida em menos de 5 segundos. Tempo atual: " + tempoConexao + "ms");
        } catch (SQLException e) {
            fail("Não deveria lançar exceção: " + e.getMessage());
        }
    }

    /**
     * Teste para verificar se a conexão suporta operações básicas.
     * Verifica se a conexão pode realizar uma consulta simples.
     */
    @Test
    void testarConexaoOperacaoBasica() {
        try (Connection conexao = ConexaoBD.conectar()) {
            assertNotNull(conexao.createStatement(),
                    "Deve ser possível criar um statement a partir da conexão.");
        } catch (SQLException e) {
            fail("Não deveria lançar exceção ao criar statement: " + e.getMessage());
        }
    }
}