package me.money.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {

    public static void initializeDatabase() {
        try (Connection connection = DatabaseConnection.getConnection();
             Statement statement = connection.createStatement()) {
            
            String createTableSQL = "CREATE TABLE IF NOT EXISTS player_stats ("
                    + "uuid TEXT PRIMARY KEY, "
                    + "money FLOAT DEFAULT 0, "
                    + "divida FLOAT DEFAULT 0 "
                    + ");";
            
            statement.executeUpdate(createTableSQL);
            System.out.println("Tabela player_stats criada ou já existente.");

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Erro ao criar a estrutura do banco de dados.");
        }
    }
    
}
