package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String URL = "jdbc:mysql://localhost:3306/loja_produtos";
    private static final String USUARIO = "java_user"; // Seu usuário do MySQL
    private static final String SENHA = "AlunoIDP@1234"; // Sua senha do MySQL

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}

