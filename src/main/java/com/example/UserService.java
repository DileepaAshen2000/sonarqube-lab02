package main.java.com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserService {

    // NOTE: Keep credentials out of code in real projects (env vars / secrets).
    private final String password = "admin123";

    public void findUser(String username) throws SQLException {
        String sql = "SELECT * FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/db", "root", password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                // consume rs if needed
            }
        }
    }

    public void deleteUser(String username) throws SQLException {
        String sql = "DELETE FROM users WHERE name = ?";

        try (Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/db", "root", password);
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.executeUpdate();
        }
    }
}
