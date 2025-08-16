package com.pahanabilling.dao.impl;

import com.pahanabilling.dao.ItemDao;
import com.pahanabilling.dao.DBConnection;
import com.pahanabilling.dao.DaoException;
import com.pahanabilling.model.Item;

import java.sql.*;
import java.util.*;

public class ItemDaoImpl implements ItemDao {

    private Item map(ResultSet rs) throws SQLException {
        Item i = new Item();
        i.setItemId(rs.getInt("item_id"));
        i.setSku(rs.getString("sku"));
        i.setName(rs.getString("name"));
        i.setUnitPrice(rs.getBigDecimal("unit_price"));
        return i;
    }

    @Override
    public Optional<Item> findById(int itemId) {
        String sql = "SELECT item_id, sku, name, unit_price FROM items WHERE item_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, itemId);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(map(rs));
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("ItemDao.findById failed", e);
        }
    }

    @Override
    public Optional<Item> findBySku(String sku) {
        String sql = "SELECT item_id, sku, name, unit_price FROM items WHERE sku=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, sku);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) return Optional.of(map(rs));
                return Optional.empty();
            }
        } catch (SQLException e) {
            throw new DaoException("ItemDao.findBySku failed", e);
        }
    }

    @Override
    public List<Item> findAll() {
        List<Item> list = new ArrayList<>();
        String sql = "SELECT item_id, sku, name, unit_price FROM items ORDER BY item_id DESC";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) list.add(map(rs));
            return list;
        } catch (SQLException e) {
            throw new DaoException("ItemDao.findAll failed", e);
        }
    }

    @Override
    public int create(Item i) {
        String sql = "INSERT INTO items(sku, name, unit_price) VALUES (?,?,?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, i.getSku());
            ps.setString(2, i.getName());
            ps.setBigDecimal(3, i.getUnitPrice());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) return rs.getInt(1);
            }
            return 0;
        } catch (SQLException e) {
            throw new DaoException("ItemDao.create failed", e);
        }
    }

    @Override
    public void update(Item i) {
        String sql = "UPDATE items SET sku=?, name=?, unit_price=? WHERE item_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, i.getSku());
            ps.setString(2, i.getName());
            ps.setBigDecimal(3, i.getUnitPrice());
            ps.setInt(4, i.getItemId());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("ItemDao.update failed", e);
        }
    }

    @Override
    public void delete(int itemId) {
        String sql = "DELETE FROM items WHERE item_id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, itemId);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("ItemDao.delete failed", e);
        }
    }
}
