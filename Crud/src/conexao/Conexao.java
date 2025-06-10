package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String DB_HOST = "bd-andrei-ba-8f3d.faivencloud.com";
    private static final String DB_PORT = "19535";
    private static final String DB_NAME = "deuses_entidades_divinas_Andrei_e_Claiton"; // Nome do banco de dados atualizado
    private static final String DB_USER = "avnadmin";
    private static final String DB_PASSWORD = System.getenv("DB_PASSWORD");

    // URL de conexão com SSL habilitado e verificação do certificado do servidor
    // Para Aiven, certifique-se de que o certificado CA esteja configurado no seu ambiente Java
    // ou use allowPublicKeyRetrieval=true para testes (não ideal para produção sem CA cert).
    private static final String DB_URL = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + DB_NAME +
            "?useSSL=true" +
            "&requireSSL=true" +
            "&verifyServerCertificate=true" +
            "&allowPublicKeyRetrieval=true"; // Necessário para MySQL 8+ se não estiver usando CA cert

    public static Connection getConnection() throws SQLException {
        try {
            // Carrega o driver JDBC (não estritamente necessário para JDBC 4.0+, mas boa prática)
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Driver JDBC não encontrado: " + e.getMessage());
            throw new SQLException("Driver JDBC não encontrado.", e);
        }
    }

    public static void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão: " + e.getMessage());
            }
        }
    }
}