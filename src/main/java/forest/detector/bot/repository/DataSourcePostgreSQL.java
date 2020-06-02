package forest.detector.bot.repository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourcePostgreSQL {
    private static Logger log = LoggerFactory.getLogger(DataSourcePostgreSQL.class);
    // Database credentials
    static final String DB_URL = "jdbc:postgresql://ec2-52-71-85-210.compute-1.amazonaws.com:5432/d7k83ecbfcmo7d";
    static final String USER = "fepmwlwrizhqqr";
    static final String PASS = "6f65b0109f7ec9ea7640167425a632598aae4aa0f326b4bc44071a76962ba52a";

    public Connection getConnect(){
        log.info("Testing connection to PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            log.info("PostgreSQL JDBC Driver is not found. Include it in your library path ");
            log.error(e.getMessage());
        }

        log.info("PostgreSQL JDBC Driver successfully connected");
        Connection connection = null;

        try {
            connection = DriverManager
                    .getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            log.info("Connection Failed");
            log.error(e.getMessage());
        }

        if (connection != null) {
            log.info("You successfully connected to database now");
        } else {
            log.info("Failed to make connection to database");
        }
        return connection;
    }
}
