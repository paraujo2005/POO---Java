import org.example.CidadesVisitadas;
import org.example.CidadesVisitadasRepository;
import org.example.ConexaoBD;
import org.junit.jupiter.api.*;

import java.sql.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CidadesVisitadasRepositoryTest {

    private static Connection conexao;
    private static CidadesVisitadasRepository repository;

    @BeforeAll
    static void inicializar() throws SQLException {
        try {
            conexao = ConexaoBD.conectar();
            repository = new CidadesVisitadasRepository();
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
        limparTabelasParaTeste(conexao);
    }

    private static void limparTabelasParaTeste(Connection conexao) throws SQLException {
        try (Statement stmt = conexao.createStatement()) {
            stmt.execute("DELETE FROM visitadas");
            stmt.execute("DELETE FROM cidades");
            stmt.execute("DELETE FROM usuarios");
        } catch (SQLException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Test
    void testarBuscarUsuarioIdPorCpfQuandoUsuarioExiste() throws SQLException {
        // Preparar dados
        inserirUsuario("12345678901", "senha123", "teste@email.com", "Empresa Teste");

        // Executar
        Integer usuarioId = repository.buscarUsuarioIdPorCpf("12345678901");

        // Verificar
        assertNotNull(usuarioId, "O ID do usuário não deve ser nulo");
        assertTrue(usuarioId > 0, "O ID do usuário deve ser maior que zero");
    }

    @Test
    void testarBuscarUsuarioIdPorCpfQuandoUsuarioNaoExiste() {
        // Executar
        Integer usuarioId = repository.buscarUsuarioIdPorCpf("99999999999");

        // Verificar
        assertNull(usuarioId, "O ID do usuário deve ser nulo para CPF inexistente");
    }

    @Test
    void testarBuscarCidadeIdPorNomeEPaisQuandoCidadeExiste() throws SQLException {
        // Preparar dados
        inserirCidade("Brasília", "Brasil");

        // Executar
        Integer cidadeId = repository.buscarCidadeIdPorNomeEPais("Brasília", "Brasil");

        // Verificar
        assertNotNull(cidadeId, "O ID da cidade não deve ser nulo");
        assertTrue(cidadeId > 0, "O ID da cidade deve ser maior que zero");
    }

    @Test
    void testarBuscarCidadeIdPorNomeEPaisQuandoCidadeNaoExiste() {
        // Executar
        Integer cidadeId = repository.buscarCidadeIdPorNomeEPais("Cidade Inexistente", "País Imaginário");

        // Verificar
        assertNull(cidadeId, "O ID da cidade deve ser nulo para cidade inexistente");
    }

    @Test
    void testarAdicionarCidadeVisitadaComNovaCidade() throws SQLException {
        // Preparar dados
        inserirUsuario("98765432100", "senha123", "teste2@email.com", "Empresa X");

        CidadesVisitadas cidadeVisitada = new CidadesVisitadas("São Paulo", "Brasil", 2024);

        // Adicionar cidade visitada
        repository.adicionarCidadeVisitada(cidadeVisitada, "98765432100");

        // Verificar
        try (PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM visitadas v JOIN cidades c ON v.cidade_id = c.id WHERE c.nome = ? AND c.pais = ?")) {
            stmt.setString(1, "São Paulo");
            stmt.setString(2, "Brasil");

            try (ResultSet rs = stmt.executeQuery()) {
                assertTrue(rs.next(), "Registro de cidade visitada deve estar presente");
                assertEquals(2024, rs.getInt("ano_visitada"), "Ano da visita deve ser igual a 2024");
            }
        }
    }

    @Test
    void testarAdicionarCidadeVisitadaComCidadeExistente() throws SQLException {
        // Preparar dados
        inserirUsuario("98765432100", "senha123", "teste2@email.com", "Empresa X");
        inserirCidade("Rio de Janeiro", "Brasil");

        CidadesVisitadas cidadeVisitada = new CidadesVisitadas("Rio de Janeiro", "Brasil", 2023);

        // Adicionar cidade visitada
        repository.adicionarCidadeVisitada(cidadeVisitada, "98765432100");

        // Verificar
        try (PreparedStatement stmt = conexao.prepareStatement("SELECT * FROM visitadas v JOIN cidades c ON v.cidade_id = c.id WHERE c.nome = ? AND c.pais = ?")) {
            stmt.setString(1, "Rio de Janeiro");
            stmt.setString(2, "Brasil");

            try (ResultSet rs = stmt.executeQuery()) {
                assertTrue(rs.next(), "Registro de cidade visitada deve estar presente");
                assertEquals(2023, rs.getInt("ano_visitada"), "Ano da visita deve ser igual a 2023");
            }
        }
    }

    @Test
    void testarListarCidadesVisitadas() throws SQLException {
        // Preparar dados
        inserirUsuario("11111111111", "senha123", "teste3@email.com", "Empresa Y");
        inserirCidade("Paris", "França");
        inserirCidade("Londres", "Reino Unido");

        // Inserir cidades visitadas
        try (PreparedStatement stmt = conexao.prepareStatement(
                "INSERT INTO visitadas (usuario_id, cidade_id, ano_visitada) " +
                        "SELECT u.id, c.id, ? FROM usuarios u, cidades c " +
                        "WHERE u.cpf = ? AND c.nome = ?")) {

            int usuarioId = obterIdUsuarioPorCpf("11111111111");

            stmt.setInt(1, 2022);
            stmt.setString(2, "11111111111");
            stmt.setString(3, "Paris");
            stmt.executeUpdate();

            stmt.setInt(1, 2023);
            stmt.setString(2, "11111111111");
            stmt.setString(3, "Londres");
            stmt.executeUpdate();
        }

        // Executar
        List<CidadesVisitadas> cidadesVisitadas = repository.listarCidadesVisitadas("11111111111");

        // Verificar
        assertNotNull(cidadesVisitadas, "Lista de cidades visitadas não deve ser nula");
        assertEquals(2, cidadesVisitadas.size(), "Devem existir 2 cidades visitadas");

        assertTrue(cidadesVisitadas.stream().anyMatch(
                cidade -> cidade.getNome().equals("Paris") &&
                        cidade.getPais().equals("França") &&
                        cidade.getAno() == 2022
        ), "Paris deve estar na lista");

        assertTrue(cidadesVisitadas.stream().anyMatch(
                cidade -> cidade.getNome().equals("Londres") &&
                        cidade.getPais().equals("Reino Unido") &&
                        cidade.getAno() == 2023
        ), "Londres deve estar na lista");
    }

    @Test
    void testarAlterarAnoVisita() throws SQLException {
        // Preparar dados
        inserirUsuario("22222222222", "senha123", "teste4@email.com", "Empresa Z");
        inserirCidade("Tóquio", "Japão");

        // Inserir visita inicial
        try (PreparedStatement stmt = conexao.prepareStatement(
                "INSERT INTO visitadas (usuario_id, cidade_id, ano_visitada) " +
                        "SELECT u.id, c.id, ? FROM usuarios u, cidades c " +
                        "WHERE u.cpf = ? AND c.nome = ?")) {

            stmt.setInt(1, 2022);
            stmt.setString(2, "22222222222");
            stmt.setString(3, "Tóquio");
            stmt.executeUpdate();
        }

        // Alterar ano da visita
        repository.alterarAnoVisita("22222222222", "Tóquio", "Japão", 2023);

        // Verificar
        try (PreparedStatement stmt = conexao.prepareStatement(
                "SELECT ano_visitada FROM visitadas v " +
                        "JOIN usuarios u ON v.usuario_id = u.id " +
                        "JOIN cidades c ON v.cidade_id = c.id " +
                        "WHERE u.cpf = ? AND c.nome = ?")) {

            stmt.setString(1, "22222222222");
            stmt.setString(2, "Tóquio");

            try (ResultSet rs = stmt.executeQuery()) {
                assertTrue(rs.next(), "Registro de visita deve existir");
                assertEquals(2023, rs.getInt("ano_visitada"), "Ano da visita deve ser atualizado");
            }
        }
    }

    @Test
    void testarDeletarVisita() throws SQLException {
        // Preparar dados
        inserirUsuario("33333333333", "senha123", "teste5@email.com", "Empresa W");
        inserirCidade("Nova York", "EUA");

        // Inserir visita
        try (PreparedStatement stmt = conexao.prepareStatement(
                "INSERT INTO visitadas (usuario_id, cidade_id, ano_visitada) " +
                        "SELECT u.id, c.id, ? FROM usuarios u, cidades c " +
                        "WHERE u.cpf = ? AND c.nome = ?")) {

            stmt.setInt(1, 2022);
            stmt.setString(2, "33333333333");
            stmt.setString(3, "Nova York");
            stmt.executeUpdate();
        }

        // Deletar visita
        repository.deletarVisita("33333333333", "Nova York", "EUA", 2022);

        // Verificar
        try (PreparedStatement stmt = conexao.prepareStatement(
                "SELECT COUNT(*) as total FROM visitadas v " +
                        "JOIN usuarios u ON v.usuario_id = u.id " +
                        "JOIN cidades c ON v.cidade_id = c.id " +
                        "WHERE u.cpf = ? AND c.nome = ?")) {

            stmt.setString(1, "33333333333");
            stmt.setString(2, "Nova York");

            try (ResultSet rs = stmt.executeQuery()) {
                assertTrue(rs.next(), "Consulta deve retornar resultado");
                assertEquals(0, rs.getInt("total"), "Nenhuma visita deve existir após a deleção");
            }
        }
    }

    // Métodos auxiliares para inserção de dados
    private void inserirUsuario(String cpf, String senha, String email, String empresa) throws SQLException {
        try (PreparedStatement stmt = conexao.prepareStatement("INSERT INTO usuarios (cpf, senha, email, empresa) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, cpf);
            stmt.setString(2, senha);
            stmt.setString(3, email);
            stmt.setString(4, empresa);
            stmt.executeUpdate();
        }
    }

    private void inserirCidade(String nome, String pais) throws SQLException {
        try (PreparedStatement stmt = conexao.prepareStatement("INSERT INTO cidades (nome, pais) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, nome);
            stmt.setString(2, pais);
            stmt.executeUpdate();
        }
    }

    private int obterIdUsuarioPorCpf(String cpf) throws SQLException {
        try (PreparedStatement stmt = conexao.prepareStatement("SELECT id FROM usuarios WHERE cpf = ?")) {
            stmt.setString(1, cpf);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                }
                throw new SQLException("Usuário não encontrado");
            }
        }
    }
}