package com.pahanabilling.dao;

import com.pahanabilling.dao.impl.CustomerDaoImpl;
import com.pahanabilling.model.Customer;
import org.junit.*;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.*;
import static org.junit.Assume.assumeNoException;
import static org.junit.Assume.assumeNotNull;

public class CustomerDaoImplTest {

    private final CustomerDao dao = new CustomerDaoImpl();

    @BeforeClass
    public static void initSchema() {
        try (Connection con = DBConnection.getConnection()) {
            assumeNotNull(con);
            try (Statement st = con.createStatement()) {
                st.execute("CREATE TABLE IF NOT EXISTS customers (" +
                        "customer_id INT AUTO_INCREMENT PRIMARY KEY," +
                        "account_no  VARCHAR(50)  NOT NULL," +
                        "name        VARCHAR(120) NOT NULL," +
                        "address     VARCHAR(255)," +
                        "phone       VARCHAR(30)," +
                        "units       INT NOT NULL DEFAULT 0," +
                        "UNIQUE KEY uq_customers_account_no (account_no))" +
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
            st.execute("TRUNCATE TABLE customers");
        }
    }

    @Test
    public void createFindUpdateDelete() {
        Customer c = new Customer();
        c.setAccountNo("ACC-1001");
        c.setName("Alice");
        c.setAddress("Colombo");
        c.setPhone("0770000000");
        c.setUnits(5);

        int id = dao.create(c);
        assertTrue(id > 0);

        Optional<Customer> found = dao.findById(id);
        assertTrue(found.isPresent());
        assertEquals("ACC-1001", found.get().getAccountNo());

        Customer upd = found.get();
        upd.setName("Alice Updated");
        upd.setUnits(9);
        dao.update(upd);

        Optional<Customer> after = dao.findById(id);
        assertTrue(after.isPresent());
        assertEquals("Alice Updated", after.get().getName());
        assertEquals(9, after.get().getUnits());

        dao.delete(id);
        assertFalse(dao.findById(id).isPresent());
    }

    @Test
    public void findByAccountNo() {
        Customer c = new Customer();
        c.setAccountNo("ACC-2002");
        c.setName("Bob");
        c.setUnits(0);
        int id = dao.create(c);

        Optional<Customer> byAcc = dao.findByAccountNo("ACC-2002");
        assertTrue(byAcc.isPresent());
        assertEquals(id, byAcc.get().getCustomerId());
    }

    @Test(expected = DaoException.class)
    public void duplicateAccountNoThrows() {
        Customer c1 = new Customer(); c1.setAccountNo("ACC-DUP"); c1.setName("C1"); c1.setUnits(0);
        Customer c2 = new Customer(); c2.setAccountNo("ACC-DUP"); c2.setName("C2"); c2.setUnits(0);
        dao.create(c1);
        dao.create(c2); // unique violation
    }

    @Test
    public void findAllReturnsList() {
        for (int i = 1; i <= 3; i++) {
            Customer c = new Customer();
            c.setAccountNo("ACC-" + i);
            c.setName("N" + i);
            c.setUnits(i);
            dao.create(c);
        }
        List<Customer> all = dao.findAll();
        assertEquals(3, all.size());
    }
}
