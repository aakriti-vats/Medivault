
package com.medivault.database;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    public static Connection getConnection() throws SQLException {

        Properties config = new Properties();

        try (InputStream input = Files.newInputStream(Path.of(".env"))) {
            config.load(input);
        } catch (IOException e) {
            throw new SQLException(
                "Unable to load database configuration from .env", e
            );
        }

        String url = config.getProperty("DB_URL");
        String user = config.getProperty("DB_USER");
        String password = config.getProperty("DB_PASSWORD");

        if (url == null || user == null || password == null) {
            throw new SQLException("Database configuration is incomplete.");
        }

        return DriverManager.getConnection(url, user, password);
    }
}
