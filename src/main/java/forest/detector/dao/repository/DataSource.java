package forest.detector.dao.repository;

import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSource {
    //  Database credentials
    static final String DB_URL = "jdbc:postgresql://ec2-52-71-85-210.compute-1.amazonaws.com:5432/d7k83ecbfcmo7d";
    static final String USER = "fepmwlwrizhqqr";
    static final String PASS = "6f65b0109f7ec9ea7640167425a632598aae4aa0f326b4bc44071a76962ba52a";
    static Connection connection = null;

    public Connection getConnection() {
        return connection;
    }

    @SneakyThrows
    static void DBconnect() {
        System.out.println("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            e.printStackTrace();
        }

        System.out.println("PostgreSQL JDBC Driver successfully connected");

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
        }

        if (connection != null) {
            System.out.println("You successfully connected to database now");
        } else {
            System.out.println("Failed to make connection to database");
        }
    }

}
