package com.pahanabilling.dao.impl;

import com.pahanabilling.dao.UserDao;
import com.pahanabilling.dao.DBConnection;
import com.pahanabilling.dao.DaoException;
import com.pahanabilling.model.User;

import java.sql.*;
import java.util.Optional;

public class UserDaoImpl implements UserDao {

    private User map(ResultSet rs) throws SQLException {
        User u = new User();
        u.setUsername(rs.getString("username"));
        u.setPassword(rs.getString("password")); // or password_hash
        u.setRole(rs.getString("role"));
        return u;
    }

    @Override
    public Optional<User> findByUsername(String username) {
        String sql = "SELECT username, password, role FROM users WHERE username=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, username);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(map(rs));
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("UserDao.findByUsername failed", e);
        }
    }

    @Override
    public int create(User user) {
        String sql = "INSERT INTO users(username, password, role) VALUES (?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword()); // store hash if you hash
            ps.setString(3, user.getRole());
            return ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("UserDao.create failed", e);
        }
    }
}
