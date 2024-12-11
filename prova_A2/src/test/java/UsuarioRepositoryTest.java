import org.example.ConexaoBD;
import org.example.Usuario;
import org.example.UsuarioRepository;
import org.junit.jupiter.api.*;
import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UsuarioRepositoryTest {

    private static UsuarioRepository repository;
    private static Connection conexao;

    @BeforeAll
    static void inicializar() throws SQLException {
        try {
            repository = new UsuarioRepository();
            conexao = ConexaoBD.conectar();

            // Limpar dados de teste antes de começar
            limparTabelasParaTeste(conexao);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @AfterAll
    static void finalizarConexao() throws SQLException {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @BeforeEach
    void prepararCadaTeste() throws SQLException {
        try {
            limparTabelasParaTeste(conexao);
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Limpa as tabelas antes dos testes para garantir um estado consistente
     */
    private static void limparTabelasParaTeste(Connection conexao) throws SQLException {
        try (Statement stmt = conexao.createStatement()) {
            stmt.execute("DELETE FROM visitadas");
            stmt.execute("DELETE FROM usuarios");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    void testarAdicionarUsuario() {
        try {
            // Preparar um usuário de teste
            Usuario usuario = new Usuario(
                    "12345678901",
                    "senhaSegura123",
                    "teste@email.com",
                    "Empresa Teste"
            );

            // Adicionar usuário
            repository.adicionarUsuario(usuario);

            // Verificar se o usuário foi adicionado
            try (PreparedStatement stmt = conexao.prepareStatement(
                    "SELECT * FROM usuarios WHERE cpf = ?")) {

                stmt.setString(1, usuario.getCpf());
                try (ResultSet rs = stmt.executeQuery()) {
                    assertTrue(rs.next(), "Usuário deve ser adicionado ao banco de dados");

                    // Verificar detalhes do usuário
                    assertEquals(usuario.getCpf(), rs.getString("cpf"), "CPF deve ser igual");
                    assertEquals(usuario.getEmail(), rs.getString("email"), "Email deve ser igual");
                    assertEquals(usuario.getEmpresa(), rs.getString("empresa"), "Empresa deve ser igual");
                }
            } catch (SQLException e) {
                fail("Erro ao verificar usuário adicionado: " + e.getMessage());
            }
        } catch (Exception e) {
            fail("Erro inesperado no teste: " + e.getMessage());
        }
    }

    @Test
    void testarListarUsuarios() {
        try {
            // Adicionar alguns usuários de teste
            Usuario usuario1 = new Usuario(
                    "11111111111",
                    "senha1",
                    "usuario1@email.com",
                    "Empresa A"
            );
            Usuario usuario2 = new Usuario(
                    "22222222222",
                    "senha2",
                    "usuario2@email.com",
                    "Empresa B"
            );

            repository.adicionarUsuario(usuario1);
            repository.adicionarUsuario(usuario2);

            // Listar usuários
            List<Usuario> usuarios = repository.listarUsuarios();

            // Verificações
            assertNotNull(usuarios, "Lista de usuários não deve ser nula");
            assertTrue(usuarios.size() >= 2, "Deve haver pelo menos 2 usuários na lista");

            // Verificar se os usuários adicionados estão na lista
            boolean usuario1Encontrado = usuarios.stream()
                    .anyMatch(u -> u.getCpf().equals(usuario1.getCpf()));
            boolean usuario2Encontrado = usuarios.stream()
                    .anyMatch(u -> u.getCpf().equals(usuario2.getCpf()));

            assertTrue(usuario1Encontrado, "Usuário 1 deve estar na lista");
            assertTrue(usuario2Encontrado, "Usuário 2 deve estar na lista");
        } catch (Exception e) {
            fail("Erro inesperado no teste: " + e.getMessage());
        }
    }

    @Test
    void testarAlterarUsuario() {
        try {
            // Adicionar usuário inicial
            Usuario usuarioOriginal = new Usuario(
                    "33333333333",
                    "senhaAntiga",
                    "original@email.com",
                    "Empresa Original"
            );
            repository.adicionarUsuario(usuarioOriginal);

            // Alterar usuário
            repository.alterarUsuario(
                    usuarioOriginal.getCpf(),
                    "senhaNova",
                    "novo@email.com",
                    "Empresa Nova"
            );

            // Verificar alterações
            try (PreparedStatement stmt = conexao.prepareStatement(
                    "SELECT * FROM usuarios WHERE cpf = ?")) {

                stmt.setString(1, usuarioOriginal.getCpf());
                try (ResultSet rs = stmt.executeQuery()) {
                    assertTrue(rs.next(), "Usuário deve existir após alteração");

                    assertEquals("senhaNova", rs.getString("senha"), "Senha deve ser atualizada");
                    assertEquals("novo@email.com", rs.getString("email"), "Email deve ser atualizado");
                    assertEquals("Empresa Nova", rs.getString("empresa"), "Empresa deve ser atualizada");
                }
            } catch (SQLException e) {
                fail("Erro ao verificar usuário alterado: " + e.getMessage());
            }
        } catch (Exception e) {
            fail("Erro inesperado no teste: " + e.getMessage());
        }
    }

    @Test
    void testarExcluirUsuario() {
        try {
            // Adicionar usuário para exclusão
            Usuario usuarioParaExcluir = new Usuario(
                    "44444444444",
                    "senhaExclusao",
                    "exclusao@email.com",
                    "Empresa Exclusão"
            );
            repository.adicionarUsuario(usuarioParaExcluir);

            // Excluir usuário
            repository.excluirUsuario(usuarioParaExcluir.getCpf());

            // Verificar exclusão
            try (PreparedStatement stmt = conexao.prepareStatement(
                    "SELECT * FROM usuarios WHERE cpf = ?")) {

                stmt.setString(1, usuarioParaExcluir.getCpf());
                try (ResultSet rs = stmt.executeQuery()) {
                    assertFalse(rs.next(), "Usuário deve ser excluído do banco de dados");
                }
            } catch (SQLException e) {
                fail("Erro ao verificar exclusão de usuário: " + e.getMessage());
            }
        } catch (Exception e) {
            fail("Erro inesperado no teste: " + e.getMessage());
        }
    }

    @Test
    void testarAutenticacaoDeUsuario() {
        try {
            // Adicionar usuário para autenticação
            Usuario usuarioParaAutenticar = new Usuario(
                    "55555555555",
                    "senhaCorreta",
                    "autenticacao@email.com",
                    "Empresa Autenticação"
            );
            repository.adicionarUsuario(usuarioParaAutenticar);

            // Testar autenticação com credenciais corretas
            boolean autenticacaoCorreta = repository.autenticarUsuario(
                    usuarioParaAutenticar.getCpf(),
                    usuarioParaAutenticar.getSenha()
            );
            assertTrue(autenticacaoCorreta, "Usuário deve ser autenticado com credenciais corretas");

            // Testar autenticação com credenciais incorretas
            boolean autenticacaoIncorreta = repository.autenticarUsuario(
                    usuarioParaAutenticar.getCpf(),
                    "senhaIncorreta"
            );
            assertFalse(autenticacaoIncorreta, "Usuário não deve ser autenticado com senha incorreta");
        } catch (Exception e) {
            fail("Erro inesperado no teste: " + e.getMessage());
        }
    }
}