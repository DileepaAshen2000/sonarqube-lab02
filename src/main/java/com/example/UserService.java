package main.java.com.example;
import java.util.logging.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    private static final Logger LOGGER =
            Logger.getLogger(UserService.class.getName());

    // Read config from environment variables (no secrets in code)
    private static final String DB_URL  =
            System.getenv().getOrDefault("DB_URL", "jdbc:mysql://localhost/db");
    private static final String DB_USER =
            System.getenv().getOrDefault("DB_USER", "root");
    private static final String DB_PASS =
            System.getenv().getOrDefault("DB_PASS", "");

    public void findUser(String username) throws SQLException {
        // ✅ Avoid SELECT *
        String sql = "SELECT id, name FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    int id = rs.getInt("id");
                    String name = rs.getString("name");
                    
                    // ✅ Use variables (no longer unused)
                    LOGGER.info(() -> String.format("User found: ID=%d, Name=%s", id, name));
                }
            }
        }
    }

    public void deleteUser(String username) throws SQLException {
        String sql = "DELETE FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.executeUpdate();
        }
    }
}
