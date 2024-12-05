package me.money.database;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import me.money.Main;

public class DatabaseConnection {
	
    private static final String URL = "jdbc:sqlite:" + Main.getInstance().getDataFolder() + File.separator + "database.db";
    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(URL);
        }
        return connection;
    }
    
}