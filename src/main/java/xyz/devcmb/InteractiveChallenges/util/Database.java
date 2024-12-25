package xyz.devcmb.InteractiveChallenges.util;

import org.bukkit.configuration.file.FileConfiguration;
import xyz.devcmb.InteractiveChallenges.InteractiveChallenges;
import xyz.devcmb.InteractiveChallenges.challenges.Challenge;

import java.sql.*;

/**
 * A utility class for interacting with the MySQL database
 */
public class Database {
    private static Connection connection;

    /**
     * Connect to the MySQL database
     */
    public static void connect() {
        FileConfiguration config = InteractiveChallenges.getPlugin().getConfig();
        String host = config.getString("database.host");
        int port = config.getInt("database.port");
        String username = config.getString("database.username");
        String password = config.getString("database.password");
        String database = config.getString("database.database");

        String url = "jdbc:mysql://" + host + ":" + port + "/" + database + "?useSSL=false";

        try {
            connection = DriverManager.getConnection(url, username, password);
            createChallengesTable();
            InteractiveChallenges.LOGGER.info("Successfully connected to the MySQL database.");
        } catch (SQLException e) {
            InteractiveChallenges.LOGGER.severe("Failed to connect to the MySQL database: " + e.getMessage());
        }
    }


    private static void createChallengesTable(){
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(
                "CREATE TABLE IF NOT EXISTS challenges (" +
                    "id VARCHAR(36) PRIMARY KEY, " +
                    "progress INT, " +
                    "active BOOLEAN)"
            );
            statement.close();
        } catch (SQLException e) {
            InteractiveChallenges.LOGGER.severe("Failed to create the challenges table: " + e.getMessage());
        }
    }

    public static void insertChallenge(Challenge challenge) {
        String query = "SELECT COUNT(*) FROM challenges WHERE id = ?";
        try (PreparedStatement checkStmt = connection.prepareStatement(query)) {
            checkStmt.setString(1, challenge.getId());
            ResultSet rs = checkStmt.executeQuery();
            if (rs.next() && rs.getInt(1) == 0) {
                String insertQuery = "INSERT INTO challenges (id, progress, active) VALUES (?, ?, ?, ?)";
                try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                    insertStmt.setString(1, challenge.getId());
                    insertStmt.setString(2, challenge.getName());
                    insertStmt.setInt(3, 0);
                    insertStmt.setBoolean(4, false);
                    insertStmt.executeUpdate();
                    InteractiveChallenges.LOGGER.info("Challenge " + challenge.getName() + " has been registered.");
                }
            } else {
                InteractiveChallenges.LOGGER.info("Challenge " + challenge.getName() + " is already registered.");
            }
        } catch (SQLException e) {
            InteractiveChallenges.LOGGER.severe("Failed to insert challenge: " + e.getMessage());
        }
    }

    /**
     * Disconnect from the mysql database
     */
    public static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
                InteractiveChallenges.LOGGER.info("Disconnected from the MySQL database.");
            } catch (SQLException e) {
                InteractiveChallenges.LOGGER.severe("Failed to disconnect from the MySQL database: " + e.getMessage());
            }
        }
    }
}
