package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioRepository {

    public void adicionarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios (cpf, senha, email, empresa) VALUES (?, ?, ?, ?)";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, usuario.getCpf());
            stmt.setString(2, usuario.getSenha());
            stmt.setString(3, usuario.getEmail());
            stmt.setString(4, usuario.getEmpresa());

            stmt.executeUpdate();
            System.out.println("Usuário adicionado com sucesso!");
        } catch (SQLException e) {
            System.out.println("Erro ao adicionar usuário: " + e.getMessage());
        }
    }

    public List<Usuario> listarUsuarios() {
        List<Usuario> usuarios = new ArrayList<>();
        String sql = "SELECT id, cpf, senha, email, empresa FROM usuarios";

        try (Connection conexao = ConexaoBD.conectar();
             Statement stmt = conexao.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Usuario usuario = new Usuario(
                        rs.getString("cpf"),
                        rs.getString("senha"),
                        rs.getString("email"),
                        rs.getString("empresa")
                );
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao listar usuários: " + e.getMessage());
            e.printStackTrace();
        }

        return usuarios;
    }

    public void alterarUsuario(String cpf, String nova_senha, String novo_email, String nova_empresa) {
        String sql = "UPDATE usuarios SET senha = ?, email = ?, empresa = ? WHERE cpf = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, nova_senha);
            stmt.setString(2, novo_email);
            stmt.setString(3, nova_empresa);
            stmt.setString(4, cpf);

            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Usuário alterado com sucesso!");
            } else {
                System.out.println("Nenhum usuário encontrado com o CPF informado.");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao alterar usuário: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void excluirUsuario(String cpf) {
        String sqlUsuarioID = "SELECT id FROM usuarios WHERE cpf = ?";
        String sqlDeleteVisitadas = "DELETE FROM visitadas WHERE usuario_id = ?";
        String sqlDeleteUsuario = "DELETE FROM usuarios WHERE id = ?";

        try (Connection conexao = ConexaoBD.conectar()) {
            conexao.setAutoCommit(false);

            int id = -1;
            try (PreparedStatement stmtBuscarID = conexao.prepareStatement(sqlUsuarioID)) {
                stmtBuscarID.setString(1, cpf);

                try (ResultSet rs = stmtBuscarID.executeQuery()) {
                    if (rs.next()) {
                        id = rs.getInt("id");
                    } else {
                        System.out.println("Nenhum usuário encontrado com o CPF informado.");
                        return;
                    }
                }
            }

            try (PreparedStatement stmtVisitadas = conexao.prepareStatement(sqlDeleteVisitadas);
                 PreparedStatement stmtUsuario = conexao.prepareStatement(sqlDeleteUsuario)) {

                stmtVisitadas.setInt(1, id);
                int linhasVisitadas = stmtVisitadas.executeUpdate();

                stmtUsuario.setInt(1, id);
                int linhasUsuarios = stmtUsuario.executeUpdate();

                if (linhasUsuarios > 0) {
                    conexao.commit();
                    System.out.println("Usuário e entradas associadas excluídos com sucesso!");
                } else {
                    System.out.println("Nenhum usuário encontrado com o ID informado.");
                    conexao.rollback();
                }

            } catch (SQLException e) {
                conexao.rollback();
                System.out.println("Erro ao excluir usuário e entradas associadas: " + e.getMessage());
                e.printStackTrace();
            }

        } catch (SQLException e) {
            System.out.println("Erro na conexão com o banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public boolean autenticarUsuario(String cpf, String senha) {
        String sql = "SELECT id FROM usuarios WHERE cpf = ? and senha = ?";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql)) {

            stmt.setString(1, cpf);
            stmt.setString(2, senha);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    System.out.println("Usuário autenticado com sucesso!");
                    return true;
                } else {
                    System.out.println("CPF ou senha incorretos.");
                    return false;
                }
            }

        } catch (SQLException e) {
            System.out.println("Erro ao tentar autenticar usuário: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public void imprimirCidadesVisitadasPorUsuario() {
        String sql = "SELECT u.cpf, c.nome AS cidade, c.pais AS pais " +
                "FROM usuarios u " +
                "JOIN visitadas v ON u.id = v.usuario_id " +
                "JOIN cidades c ON v.cidade_id = c.id " +
                "ORDER BY u.cpf, c.nome";

        try (Connection conexao = ConexaoBD.conectar();
             PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            String cpfAtual = "";
            while (rs.next()) {
                String cpf = rs.getString("cpf");
                String cidade = rs.getString("cidade");
                String pais = rs.getString("pais");

                if (!cpf.equals(cpfAtual)) {
                    if (!cpfAtual.isEmpty()) {
                        System.out.println();
                    }
                    System.out.println("CPF: " + cpf);
                    cpfAtual = cpf;
                }
                System.out.println("  * " + cidade + " - " + pais);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar cidades visitadas por usuários: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
