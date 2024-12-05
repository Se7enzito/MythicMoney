package me.money.APIs.money;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import me.money.database.DatabaseConnection;

public class MoneyAPI {

    public static double verMoney(String uuid) {
        String query = "SELECT money FROM player_stats WHERE uuid = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, uuid);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getDouble("money");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return -1;
    }

    public static boolean adicionarMoney(String uuid, double amount) {
        String query = "UPDATE player_stats SET money = money + ? WHERE uuid = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, uuid);
            
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    public static boolean removerMoney(String uuid, double amount) {
        String query = "UPDATE player_stats SET money = money - ? WHERE uuid = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, uuid);
            
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    public static boolean setarMoney(String uuid, double amount) {
        String query = "UPDATE player_stats SET money = ? WHERE uuid = ?";
        
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setDouble(1, amount);
            preparedStatement.setString(2, uuid);
            
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return false;
    }

    public static boolean transferirMoney(String fromUuid, String toUuid, double amount) {
        Connection connection = null;
        
        try {
            connection = DatabaseConnection.getConnection();
            connection.setAutoCommit(false);

            if (!removerMoney(fromUuid, amount)) {
                connection.rollback();
                
                return false;
            }
            if (!adicionarMoney(toUuid, amount)) {
                connection.rollback();
                
                return false;
            }
            
            connection.commit();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackException) {
                    rollbackException.printStackTrace();
                }
            }
        } finally {
            if (connection != null) {
                try {
                    connection.setAutoCommit(true);
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }
	
}
