package com.pahanabilling.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.*;

import com.pahanabilling.dao.impl.ItemDaoImpl;
import com.pahanabilling.model.Item;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ItemDaoTest {

    private ItemDao dao;

    @BeforeAll
    static void initOnce() { /* no-op, as requested */ }

    @BeforeEach
    void setUp() {
        dao = new ItemDaoImpl();
    }

    @Test
    @Order(1)
    void testCreateUpdateFindListAndDelete() {
        // create
        Item i = new Item();
        i.setSku("SKU-JUNIT-001");
        i.setName("Book A");
        i.setUnitPrice(new BigDecimal("500.00"));

        int id = dao.create(i);
        assertTrue(id > 0);

        // find by id
        Optional<Item> fetched = dao.findById(id);
        assertTrue(fetched.isPresent());
        assertEquals("SKU-JUNIT-001", fetched.get().getSku());
        assertEquals("Book A", fetched.get().getName());
        assertEquals(0, new BigDecimal("500.00").compareTo(fetched.get().getUnitPrice()));

        // find by sku
        Optional<Item> bySku = dao.findBySku("SKU-JUNIT-001");
        assertTrue(bySku.isPresent());
        assertEquals(id, bySku.get().getItemId());

        // update
        Item toUpdate = fetched.get();
        toUpdate.setName("Book A (Revised)");
        toUpdate.setUnitPrice(new BigDecimal("650.00"));
        dao.update(toUpdate);

        Optional<Item> after = dao.findById(id);
        assertTrue(after.isPresent());
        assertEquals("Book A (Revised)", after.get().getName());
        assertEquals(0, new BigDecimal("650.00").compareTo(after.get().getUnitPrice()));

        // list
        List<Item> all = dao.findAll();
        assertFalse(all.isEmpty());

        // delete
        dao.delete(id);
        assertFalse(dao.findById(id).isPresent(), "Deleted item must not be found");
    }
}
