package com.pahanabilling.dao;

import com.pahanabilling.model.User;
import java.sql.*;

public class UserDAO {
    public static User validateUser(String username, String password) {
        String sql = "SELECT username, role FROM `user` WHERE username = ? AND password = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, username);
            ps.setString(2, password);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    User u = new User();
                    u.setUsername(rs.getString("username"));
                    u.setRole(rs.getString("role"));
                    return u;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace(); // check server logs if it still fails
        }
        return null;
    }
}
