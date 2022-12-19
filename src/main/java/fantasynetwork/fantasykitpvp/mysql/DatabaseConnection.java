package fantasynetwork.fantasykitpvp.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseConnection {

    private DatabaseConnectionFactory factory;

    public DatabaseConnection(DatabaseConnectionFactory factory) {
        this.factory = factory;
    }

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        try {
            return factory.build();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("--------------------------------------------");
            System.out.println("    Conexão com MySQL falhou.       ");
            System.out.println("--------------------------------------------");
        }
        return null;
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException, ClassNotFoundException {
        return getConnection().prepareStatement(sql);
    }

    public static class DatabaseConnectionFactory {

        public static DatabaseConnectionFactory builder() {
            return new DatabaseConnectionFactory();
        }

        private String host, databaseName, username, password = "";
        private int port = 3306;

        private DatabaseConnectionFactory() {

        }

        public DatabaseConnectionFactory withHost(String host) {
            this.host = host;
            return this;
        }

        public DatabaseConnectionFactory withDatabase(String database) {
            this.databaseName = database;
            return this;
        }

        public DatabaseConnectionFactory withUsername(String username) {
            this.username = username;
            return this;
        }

        public DatabaseConnectionFactory withPassword(String password) {
            this.password = password;
            return this;
        }

        public DatabaseConnectionFactory withPort(int port) {
            this.port = port;
            return this;
        }

        public Connection build() throws Exception {
            if (this.host == null || this.host.isEmpty()) {
                throw new RuntimeException("ConnectionFactory 'host' is null or empty");
            }
            if (this.databaseName == null || this.databaseName.isEmpty()) {
                throw new RuntimeException("ConnectionFactory 'dbName' is null or empty");
            }
            if (this.username == null || this.username.isEmpty()) {
                throw new RuntimeException("ConnectionFactory 'username' is null or empty");
            }
            if (this.password == null) {
                throw new RuntimeException("ConnectionFactory 'password' is null or empty");
            }

            Class.forName("com.mysql.jdbc.Driver");
            return DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.databaseName, this.username, this.password);
        }
    }
}
