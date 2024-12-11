package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CidadesVisitadasRepository {

    public Integer buscarUsuarioIdPorCpf(String cpf) {
        String sqlUsuarioID = "SELECT id FROM usuarios WHERE cpf = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmtUsuarioID = conexao.prepareStatement(sqlUsuarioID)) {

            stmtUsuarioID.setString(1, cpf);
            try (ResultSet rs = stmtUsuarioID.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    System.out.println("Usuário não encontrado com o CPF informado.");
                    return null;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Integer buscarCidadeIdPorNomeEPais(String nomeCidade, String paisCidade) {
        String sqlCidadeID = "SELECT id FROM cidades WHERE nome = ? AND pais = ?";
        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sqlCidadeID)) {
            stmt.setString(1, nomeCidade);
            stmt.setString(2, paisCidade);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("id");
                } else {
                    return null;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void adicionarCidadeVisitada(CidadesVisitadas visitada, String cpf) {
        String sqlInserirCidade = "INSERT INTO cidades (nome, pais) VALUES (?, ?)";
        String sqlInserirVisitada = "INSERT INTO visitadas (usuario_id, cidade_id, ano_visitada) VALUES (?, ?, ?)";

        Connection conexao = null;
        try {
            conexao = ConexaoBD.conectar();
            conexao.setAutoCommit(false);

            Integer usuarioId = buscarUsuarioIdPorCpf(cpf);
            if (usuarioId == null) {
                throw new SQLException("Usuário não encontrado com o CPF informado.");
            }

            Integer cidadeId = buscarCidadeIdPorNomeEPais(visitada.getNome(), visitada.getPais());
            if (cidadeId == null) {
                try (PreparedStatement stmtInserirCidade = conexao.prepareStatement(sqlInserirCidade, Statement.RETURN_GENERATED_KEYS)) {
                    stmtInserirCidade.setString(1, visitada.getNome());
                    stmtInserirCidade.setString(2, visitada.getPais());
                    stmtInserirCidade.executeUpdate();

                    try (ResultSet generatedKeys = stmtInserirCidade.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            cidadeId = generatedKeys.getInt(1);
                        } else {
                            throw new SQLException("Falha ao inserir cidade, ID não gerado.");
                        }
                    }
                }
            }

            try (PreparedStatement stmtInserirVisitada = conexao.prepareStatement(sqlInserirVisitada)) {
                stmtInserirVisitada.setInt(1, usuarioId);
                stmtInserirVisitada.setInt(2, cidadeId);
                stmtInserirVisitada.setInt(3, visitada.getAno());
                stmtInserirVisitada.executeUpdate();
            }

            conexao.commit();
            System.out.println("Cidade visitada adicionada com sucesso!");

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conexao != null && !conexao.isClosed()) {
                    conexao.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    public List<CidadesVisitadas> listarCidadesVisitadas(String cpf) {
        String sqlCidadesVisitadas = "SELECT c.nome, c.pais, v.ano_visitada " +
                "FROM visitadas v " +
                "JOIN cidades c ON v.cidade_id = c.id " +
                "WHERE v.usuario_id = ?";

        List<CidadesVisitadas> cidadesVisitadas = new ArrayList<>();
        Integer usuarioId = buscarUsuarioIdPorCpf(cpf);

        if (usuarioId == null) {
            return cidadesVisitadas;
        }

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmtCidadesVisitadas = conexao.prepareStatement(sqlCidadesVisitadas)) {

            stmtCidadesVisitadas.setInt(1, usuarioId);
            try (ResultSet rs = stmtCidadesVisitadas.executeQuery()) {
                while (rs.next()) {
                    String nome = rs.getString("nome");
                    String pais = rs.getString("pais");
                    int anoVisitada = rs.getInt("ano_visitada");

                    CidadesVisitadas cidade = new CidadesVisitadas(nome, pais, anoVisitada);
                    cidadesVisitadas.add(cidade);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cidadesVisitadas;
    }

    public void alterarAnoVisita(String cpf, String nomeCidade, String paisCidade, int novoAno) {
        String sqlAtualizarVisitada = "UPDATE visitadas SET ano_visitada = ? WHERE usuario_id = ? AND cidade_id = ?";

        Connection conexao = null;
        try {
            conexao = ConexaoBD.conectar();
            conexao.setAutoCommit(false);

            Integer usuarioId = buscarUsuarioIdPorCpf(cpf);
            if (usuarioId == null) {
                throw new SQLException("Usuário não encontrado com o CPF informado.");
            }

            Integer cidadeId = buscarCidadeIdPorNomeEPais(nomeCidade, paisCidade);
            if (cidadeId == null) {
                throw new SQLException("Cidade não encontrada com o nome e país informados.");
            }

            try (PreparedStatement stmtAtualizarVisitada = conexao.prepareStatement(sqlAtualizarVisitada)) {
                stmtAtualizarVisitada.setInt(1, novoAno);
                stmtAtualizarVisitada.setInt(2, usuarioId);
                stmtAtualizarVisitada.setInt(3, cidadeId);

                int linhasAfetadas = stmtAtualizarVisitada.executeUpdate();
                if (linhasAfetadas > 0) {
                    conexao.commit();
                    System.out.println("Ano de visita atualizado com sucesso!");
                } else {
                    System.out.println("Nenhuma entrada encontrada para o usuário e cidade especificados.");
                    conexao.rollback();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conexao != null && !conexao.isClosed()) {
                    conexao.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    public void deletarVisita(String cpf, String nomeCidade, String paisCidade, int ano) {
        String sqlDeletarVisita = "DELETE FROM visitadas WHERE usuario_id = ? AND cidade_id = ? AND ano_visitada = ?";

        Connection conexao = null;
        try {
            conexao = ConexaoBD.conectar();
            conexao.setAutoCommit(false);

            Integer usuarioId = buscarUsuarioIdPorCpf(cpf);
            if (usuarioId == null) {
                throw new SQLException("Usuário não encontrado com o CPF informado.");
            }

            Integer cidadeId = buscarCidadeIdPorNomeEPais(nomeCidade, paisCidade);
            if (cidadeId == null) {
                throw new SQLException("Cidade não encontrada com o nome e país informados.");
            }

            try (PreparedStatement stmtDeletarVisita = conexao.prepareStatement(sqlDeletarVisita)) {
                stmtDeletarVisita.setInt(1, usuarioId);
                stmtDeletarVisita.setInt(2, cidadeId);
                stmtDeletarVisita.setInt(3, ano);

                int linhasAfetadas = stmtDeletarVisita.executeUpdate();
                if (linhasAfetadas > 0) {
                    conexao.commit();
                    System.out.println("Visita deletada com sucesso!");
                } else {
                    System.out.println("Nenhuma visita encontrada com os critérios especificados.");
                    conexao.rollback();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conexao != null && !conexao.isClosed()) {
                    conexao.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    public void deletarCidade(String nomeCidade, String paisCidade) {
        String sqlDeletarVisitadas = "DELETE FROM visitadas WHERE cidade_id = ?";
        String sqlDeletarCidade = "DELETE FROM cidades WHERE id = ?";

        Connection conexao = null;
        try {
            conexao = ConexaoBD.conectar();
            conexao.setAutoCommit(false);

            Integer cidadeId = buscarCidadeIdPorNomeEPais(nomeCidade, paisCidade);
            if (cidadeId == null) {
                throw new SQLException("Cidade não encontrada com o nome e país informados.");
            }

            try (PreparedStatement stmtDeletarVisitadas = conexao.prepareStatement(sqlDeletarVisitadas)) {
                stmtDeletarVisitadas.setInt(1, cidadeId);
                stmtDeletarVisitadas.executeUpdate();
            }

            try (PreparedStatement stmtDeletarCidade = conexao.prepareStatement(sqlDeletarCidade)) {
                stmtDeletarCidade.setInt(1, cidadeId);
                int linhasAfetadas = stmtDeletarCidade.executeUpdate();
                if (linhasAfetadas > 0) {
                    conexao.commit();
                    System.out.println("Cidade e entradas relacionadas deletadas com sucesso!");
                } else {
                    System.out.println("Nenhuma cidade encontrada com os critérios especificados.");
                    conexao.rollback();
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
            try {
                if (conexao != null && !conexao.isClosed()) {
                    conexao.rollback();
                }
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }




}
