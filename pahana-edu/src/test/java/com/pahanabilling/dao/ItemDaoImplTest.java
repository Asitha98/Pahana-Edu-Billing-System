package com.pahanabilling.dao;

import com.pahanabilling.dao.impl.ItemDaoImpl;
import com.pahanabilling.model.Item;
import org.junit.*;

import java.math.BigDecimal;
import java.sql.*;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;
import static org.junit.Assume.assumeNotNull;

public class ItemDaoImplTest {

    private final ItemDao dao = new ItemDaoImpl();

    @BeforeClass
    public static void initSchema() {
        try (Connection con = DBConnection.getConnection()) {
            assumeNotNull(con);
            try (Statement st = con.createStatement()) {
                st.execute("CREATE TABLE IF NOT EXISTS items (" +
                        "item_id    INT AUTO_INCREMENT PRIMARY KEY," +
                        "sku        VARCHAR(50)  NOT NULL," +
                        "name       VARCHAR(150) NOT NULL," +
                        "unit_price DECIMAL(10,2) NOT NULL," +
                        "UNIQUE KEY uq_items_sku (sku))" +
                        " ENGINE=InnoDB");
            }
        } catch (SQLException e) {
            assumeNoException("DB not reachable", e);
        }
    }

    @Before
    public void clean() throws Exception {
        try (Connection con = DBConnection.getConnection();
             Statement st = con.createStatement()) {
            st.execute("TRUNCATE TABLE items");
        }
    }

    @Test
    public void createFindUpdateDelete() {
        Item i = new Item();
        i.setSku("SKU-100");
        i.setName("Widget");
        i.setUnitPrice(new BigDecimal("123.45"));

        int id = dao.create(i);
        assertTrue(id > 0);

        Optional<Item> found = dao.findById(id);
        assertTrue(found.isPresent());
        assertEquals("SKU-100", found.get().getSku());

        Item upd = found.get();
        upd.setName("Widget-Plus");
        upd.setUnitPrice(new BigDecimal("150.00"));
        dao.update(upd);

        Optional<Item> after = dao.findById(id);
        assertTrue(after.isPresent());
        assertEquals("Widget-Plus", after.get().getName());
        assertEquals(new BigDecimal("150.00"), after.get().getUnitPrice());

        dao.delete(id);
        assertFalse(dao.findById(id).isPresent());
    }

    @Test
    public void findBySku() {
        Item i = new Item();
        i.setSku("SKU-ABC");
        i.setName("Thing");
        i.setUnitPrice(new BigDecimal("10.00"));
        int id = dao.create(i);

        Optional<Item> bySku = dao.findBySku("SKU-ABC");
        assertTrue(bySku.isPresent());
        assertEquals(id, bySku.get().getItemId());
    }

    @Test(expected = DaoException.class)
    public void duplicateSkuThrows() {
        Item i1 = new Item(); i1.setSku("SKU-DUP"); i1.setName("A"); i1.setUnitPrice(new BigDecimal("1.00"));
        Item i2 = new Item(); i2.setSku("SKU-DUP"); i2.setName("B"); i2.setUnitPrice(new BigDecimal("2.00"));
        dao.create(i1);
        dao.create(i2); // unique violation
    }

    @Test
    public void findAllReturnsList() {
        for (int k = 1; k <= 2; k++) {
            Item i = new Item();
            i.setSku("SKU-" + k);
            i.setName("Item " + k);
            i.setUnitPrice(new BigDecimal("9.99"));
            dao.create(i);
        }
        List<Item> all = dao.findAll();
        assertEquals(2, all.size());
    }
}
