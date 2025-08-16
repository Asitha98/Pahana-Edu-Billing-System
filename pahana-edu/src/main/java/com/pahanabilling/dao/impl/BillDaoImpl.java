package com.pahanabilling.dao.impl;

import com.pahanabilling.dao.BillDao;
import com.pahanabilling.dao.DBConnection;
import com.pahanabilling.dao.DaoException;
import com.pahanabilling.model.Bill;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.*;

public class BillDaoImpl implements BillDao {

    private Bill map(ResultSet rs) throws SQLException {
        Bill b = new Bill();
        b.setBillId(rs.getInt("bill_id"));
        b.setCustomerId(rs.getInt("customer_id"));
        b.setUnits(rs.getInt("units"));
        b.setAmount(rs.getBigDecimal("amount"));
        Timestamp ts = rs.getTimestamp("created_at");
        if (ts != null) b.setCreatedAt(ts.toLocalDateTime());
        return b;
    }

    @Override
    public Optional<Bill> findById(int billId) {
        String sql = "SELECT bill_id, customer_id, units, amount, created_at FROM bills WHERE bill_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, billId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(map(rs));
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("BillDao.findById failed", e);
        }
    }

    @Override
    public List<Bill> findAll() {
        List<Bill> list = new ArrayList<>();
        String sql = "SELECT bill_id, customer_id, units, amount, created_at FROM bills ORDER BY bill_id DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
            return list;
        } catch (SQLException e) {
            throw new DaoException("BillDao.findAll failed", e);
        }
    }

    @Override
    public List<Bill> findByCustomerId(int customerId) {
        List<Bill> list = new ArrayList<>();
        String sql = "SELECT bill_id, customer_id, units, amount, created_at FROM bills WHERE customer_id=? ORDER BY bill_id DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, customerId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) list.add(map(rs));
                return list;
            }
        } catch (SQLException e) {
            throw new DaoException("BillDao.findByCustomerId failed", e);
        }
    }

    @Override
    public int create(Bill bill) {
        String sql = "INSERT INTO bills(customer_id, units, amount, created_at) VALUES (?,?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setInt(1, bill.getCustomerId());
            ps.setInt(2, bill.getUnits());
            ps.setBigDecimal(3, bill.getAmount());
            LocalDateTime created = bill.getCreatedAt() != null ? bill.getCreatedAt() : LocalDateTime.now();
            ps.setTimestamp(4, Timestamp.valueOf(created));
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new DaoException("BillDao.create failed", e);
        }
    }
}
