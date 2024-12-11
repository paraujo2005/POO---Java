package org.example;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class GerenciadorTabelas {

    public static void verificarOuCriarTabelas() {
        try (Connection conexao = ConexaoBD.conectar()) {
            if (!tabelaExiste(conexao, "usuarios")) {
                criarTabelaUsuarios(conexao);
            }

            if (!tabelaExiste(conexao, "cidades")) {
                criarTabelaCidades(conexao);
            }

            if (!tabelaExiste(conexao, "visitadas")) {
                criarTabelaVisitadas(conexao);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao verificar/criar tabelas: " + e.getMessage());
        }
    }

    private static boolean tabelaExiste(Connection conexao, String nomeTabela) throws SQLException {
        DatabaseMetaData metaData = conexao.getMetaData();
        try (var rs = metaData.getTables(null, null, nomeTabela, null)) {
            return rs.next();
        }
    }

    private static void criarTabelaUsuarios(Connection conexao) throws SQLException {
        String sql = """
                CREATE TABLE usuarios (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    cpf VARCHAR(11) UNIQUE NOT NULL,
                    senha VARCHAR(100) NOT NULL,
                    email VARCHAR(100) UNIQUE NOT NULL,
                    empresa VARCHAR(150) NOT NULL
                ) ENGINE=InnoDB
                CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
                """;
        executarSQL(conexao, sql, "usuarios");
    }

    private static void criarTabelaCidades(Connection conexao) throws SQLException {
        String sql = """
                CREATE TABLE cidades (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    nome VARCHAR(255) NOT NULL,
                    pais VARCHAR(100) NOT NULL
                ) ENGINE=InnoDB
                CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
                """;
        executarSQL(conexao, sql, "cidades");
    }

    private static void criarTabelaVisitadas(Connection conexao) throws SQLException {
        String sql = """
                CREATE TABLE visitadas (
                    id INT AUTO_INCREMENT PRIMARY KEY,
                    usuario_id INT NOT NULL,
                    cidade_id INT NOT NULL,
                    ano_visitada INT NOT NULL,
                    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
                    FOREIGN KEY (cidade_id) REFERENCES cidades(id)
                ) ENGINE=InnoDB
                CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
                """;
        executarSQL(conexao, sql, "visitadas");
    }

    private static void executarSQL(Connection conexao, String sql, String nomeTabela) throws SQLException {
        try (Statement stmt = conexao.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabela '" + nomeTabela + "' criada com sucesso.");
        }
    }
}
