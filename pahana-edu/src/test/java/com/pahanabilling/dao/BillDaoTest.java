package com.pahanabilling.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.*;

import com.pahanabilling.dao.impl.BillDaoImpl;
import com.pahanabilling.dao.impl.CustomerDaoImpl;
import com.pahanabilling.model.Bill;
import com.pahanabilling.model.Customer;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BillDaoTest {

    private BillDao billDao;
    private CustomerDao customerDao;

    @BeforeAll
    static void initOnce() { /* no-op */ }

    @BeforeEach
    void setUp() {
        billDao = new BillDaoImpl();
        customerDao = new CustomerDaoImpl();
    }

    @Test
    @Order(1)
    void testCreateFindListAndFindByCustomer() {
        // create a real customer (FK friendly)
        Customer c = new Customer();
        c.setAccountNo("ACC-BILL-JUNIT-001");
        c.setName("Billing Customer");
        c.setUnits(0);
        int custId = customerDao.create(c);
        assertTrue(custId > 0);

        // create bill
        Bill b = new Bill();
        b.setCustomerId(custId);
        b.setUnits(30);
        b.setAmount(new BigDecimal("1500.00"));
        b.setCreatedAt(LocalDateTime.now());

        int billId = billDao.create(b);
        assertTrue(billId > 0);

        // find by id
        Optional<Bill> byId = billDao.findById(billId);
        assertTrue(byId.isPresent());
        assertEquals(custId, byId.get().getCustomerId());
        assertEquals(30, byId.get().getUnits());
        assertEquals(0, new BigDecimal("1500.00").compareTo(byId.get().getAmount()));

        // find by customer
        List<Bill> byCustomer = billDao.findByCustomerId(custId);
        assertTrue(byCustomer.stream().anyMatch(x -> x.getBillId() == billId));

        // list all
        List<Bill> all = billDao.findAll();
        assertFalse(all.isEmpty());

        // (No delete in BillDao, so we don't clean it here)
    }
}
