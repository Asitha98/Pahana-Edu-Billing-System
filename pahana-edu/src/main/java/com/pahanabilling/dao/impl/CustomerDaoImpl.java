package com.pahanabilling.dao.impl;

import com.pahanabilling.dao.CustomerDao;
import com.pahanabilling.dao.DBConnection;
import com.pahanabilling.dao.DaoException;
import com.pahanabilling.model.Customer;

import java.sql.*;
import java.util.*;

public class CustomerDaoImpl implements CustomerDao {

    private Customer map(ResultSet rs) throws SQLException {
        Customer c = new Customer();
        c.setCustomerId(rs.getInt("customer_id"));
        c.setAccountNo(rs.getString("account_no"));
        c.setName(rs.getString("name"));
        c.setAddress(rs.getString("address"));
        c.setPhone(rs.getString("phone"));
        c.setUnits(rs.getInt("units"));
        return c;
    }

    @Override
    public Optional<Customer> findById(int customerId) {
        String sql = "SELECT customer_id, account_no, name, address, phone, units FROM customers WHERE customer_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(map(rs));
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("CustomerDao.findById failed", e);
        }
    }

    @Override
    public Optional<Customer> findByAccountNo(String accountNo) {
        String sql = "SELECT customer_id, account_no, name, address, phone, units FROM customers WHERE account_no=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, accountNo);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(map(rs));
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("CustomerDao.findByAccountNo failed", e);
        }
    }

    @Override
    public List<Customer> findAll() {
        List<Customer> list = new ArrayList<>();
        String sql = "SELECT customer_id, account_no, name, address, phone, units FROM customers ORDER BY customer_id DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
            return list;
        } catch (SQLException e) {
            throw new DaoException("CustomerDao.findAll failed", e);
        }
    }

    @Override
    public int create(Customer c) {
        String sql = "INSERT INTO customers(account_no, name, address, phone, units) VALUES (?,?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, c.getAccountNo());
            ps.setString(2, c.getName());
            ps.setString(3, c.getAddress());
            ps.setString(4, c.getPhone());
            ps.setInt(5, c.getUnits());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new DaoException("CustomerDao.create failed", e);
        }
    }

    @Override
    public void update(Customer c) {
        String sql = "UPDATE customers SET account_no=?, name=?, address=?, phone=?, units=? WHERE customer_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, c.getAccountNo());
            ps.setString(2, c.getName());
            ps.setString(3, c.getAddress());
            ps.setString(4, c.getPhone());
            ps.setInt(5, c.getUnits());
            ps.setInt(6, c.getCustomerId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("CustomerDao.update failed", e);
        }
    }

    @Override
    public void delete(int customerId) {
        String sql = "DELETE FROM customers WHERE customer_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("CustomerDao.delete failed", e);
        }
    }
}
