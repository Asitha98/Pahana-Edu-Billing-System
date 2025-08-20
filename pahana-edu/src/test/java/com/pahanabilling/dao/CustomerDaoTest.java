package com.pahanabilling.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.*;

import com.pahanabilling.dao.impl.CustomerDaoImpl;
import com.pahanabilling.model.Customer;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CustomerDaoTest {

    private CustomerDao dao;

    @BeforeAll
    static void initOnce() { /* no-op */ }

    @BeforeEach
    void setUp() {
        dao = new CustomerDaoImpl();
    }

    @Test
    @Order(1)
    void testCreateUpdateFindListAndDelete() {
        // create
        Customer c = new Customer();
        c.setAccountNo("ACC-JUNIT-1001");
        c.setName("JUnit Customer");
        c.setAddress("Colombo");
        c.setPhone("0770000000");
        c.setUnits(10);

        int id = dao.create(c);
        assertTrue(id > 0);

        // find by id
        Optional<Customer> byId = dao.findById(id);
        assertTrue(byId.isPresent());
        assertEquals("ACC-JUNIT-1001", byId.get().getAccountNo());
        assertEquals("JUnit Customer", byId.get().getName());

        // find by account no
        Optional<Customer> byAcc = dao.findByAccountNo("ACC-JUNIT-1001");
        assertTrue(byAcc.isPresent());
        assertEquals(id, byAcc.get().getCustomerId());

        // update
        Customer toUpdate = byId.get();
        toUpdate.setUnits(25);
        toUpdate.setPhone("0771111111");
        toUpdate.setAddress("Kandy");
        dao.update(toUpdate);

        Optional<Customer> after = dao.findById(id);
        assertTrue(after.isPresent());
        assertEquals(25, after.get().getUnits());
        assertEquals("0771111111", after.get().getPhone());
        assertEquals("Kandy", after.get().getAddress());

        // list
        List<Customer> all = dao.findAll();
        assertFalse(all.isEmpty());

        // delete
        dao.delete(id);
        assertFalse(dao.findById(id).isPresent(), "Deleted customer must not be found");
    }
}
